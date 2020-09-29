package framework.context;


import framework.annotation.MyAutowired;
import framework.annotation.MyController;
import framework.annotation.MyService;
import framework.beans.BeanWrapper;
import framework.beans.config.BeanDefinition;
import framework.beans.support.BeanDefinitionReader;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 完成bean的创建和DI
 * @author wangmengqi
 * @date 2020/7/1 21:24
 */
public class ApplicationContext {

    private String[] configLocations;

    private BeanDefinitionReader beanDefinitionReader;

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap();

    private Map<String,BeanWrapper> factoryBeanInstanceCache = new HashMap<>();

    private Map<String,Object> factoryBeanObjectCache = new HashMap<>();


    public ApplicationContext(String... configLocations){
        this.configLocations = configLocations;
        // 读取配置文件
        beanDefinitionReader = new BeanDefinitionReader(this.configLocations);
        // 解析配置文件封装成beanDefinition
        List<BeanDefinition> beanDefinitionList = beanDefinitionReader.loadBeanDefinition();
        // 保存beanDefinition
        doRegisterBeanDefinition(beanDefinitionList);
        //
        doAutowired();
    }

    private void doRegisterBeanDefinition(List<BeanDefinition> beanDefinitionList) {
        for (BeanDefinition beanDefinition : beanDefinitionList) {
            if (this.beanDefinitionMap.containsKey(beanDefinition.getFactoryBeanName())){
                throw new RuntimeException("The" + beanDefinition.getBeanClassName() + "is exists");
            }
            beanDefinitionMap.put(beanDefinition.getFactoryBeanName(),beanDefinition);
            beanDefinitionMap.put(beanDefinition.getBeanClassName(),beanDefinition);
        }
    }

    private void doAutowired() {
        // 调用getBean
        // 所有的bean并没有真正的实例化
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : beanDefinitionMap.entrySet()) {
            String beanName = beanDefinitionEntry.getKey();
            getBean(beanName);
        }
    }




    public Object getBean(String beanName){
        // 1.先拿到配置信息
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        // 2.反射实例化
        Object instance = instantiateBean(beanName,beanDefinition);
        // 3.封装成BeanWrapper
        BeanWrapper beanWrapper = new BeanWrapper(instance);
        // 4.保存到ioc容器
        factoryBeanInstanceCache.put(beanName,beanWrapper);
        // 5.执行依赖注入
        populateBean(beanName,beanDefinition,beanWrapper);
        return beanWrapper.getWrapperInstance();
    }

    private void populateBean(String beanName, BeanDefinition beanDefinition, BeanWrapper beanWrapper) {
        // 可能会涉及到循环依赖 用两个缓存把第一次读取结果为null的BeanDefinition存到第一个缓存
        Object instance = beanWrapper.getWrapperInstance();

        Class<?> beanClass = beanWrapper.getWrapperClass();
        // 在spring中都是@Compent
        if (!beanClass.isAnnotationPresent(MyController.class) && !beanClass.isAnnotationPresent(MyService.class)){
            return;
        }
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(MyAutowired.class)){
                continue;
            }
            field.setAccessible(true);
            MyAutowired autowired = field.getAnnotation(MyAutowired.class);
            String autoName = autowired.value().trim();
            try{
                field.set(instance,this.factoryBeanInstanceCache.get(autoName).getWrapperInstance());
            }catch (Exception e){

            }
        }
    }

    private Object instantiateBean(String beanName, BeanDefinition beanDefinition) {
        String className = beanDefinition.getBeanClassName();

        Object instance = null;
        try {
            if (this.factoryBeanObjectCache.containsKey(beanName)){
                instance = this.factoryBeanInstanceCache.get(beanName);
                return instance;
            }
            Class<?> clazz = Class.forName(className);
            instance = clazz.newInstance();
            this.factoryBeanObjectCache.put(beanName,instance);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }


    public Object getBean(Class type){
        return getBean(type.getName());
    }
}
