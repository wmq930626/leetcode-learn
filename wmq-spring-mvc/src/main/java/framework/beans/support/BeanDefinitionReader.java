package framework.beans.support;

import framework.beans.config.BeanDefinition;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author wangmengqi
 * @date 2020/7/1 21:34
 */
public class BeanDefinitionReader {

    private Properties contextConfig = new Properties();

    private List<String> registryBeanClasses = new ArrayList();

    public BeanDefinitionReader(String... configLocations) {
        // 加载配置文件
        doLoadConfig(configLocations[0]);
        // 扫描bean定义文件
        doScanner(contextConfig.getProperty("package"));

    }

    private void doScanner(String location) {
        // 解析url
        URL url = this.getClass().getClassLoader().getResource("/" + location.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        File[] files = classPath.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                doScanner(location + "." + file.getName());
            } else {
                if (file.getName().endsWith(".class")) {
                    String className = location + "." + file.getName().replace(".class", "");
                    registryBeanClasses.add(className);
                }
            }

        }
    }

    private void doLoadConfig(String contextConfigLocation) {
        // 获取文件流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<BeanDefinition> loadBeanDefinition() {
        List<BeanDefinition> beanDefinitionList = new ArrayList<>();
        for (String className : registryBeanClasses) {
            try {
                Class<?> clazz = Class.forName(className);
                // 保存对象的全类名 和 BeanName 默认类名首字母小写
                String beanName = toLowerFirstCase(clazz.getSimpleName());
                String beanClassName = clazz.getName();
                beanDefinitionList.add(doCreateBeanDefinition(beanName,beanClassName));
                // todo 自定义
                // todo 接口注入

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private BeanDefinition doCreateBeanDefinition(String beanName, String beanClassName) {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(beanName);
        return beanDefinition;
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        // if (chars[0] >=111 && chars[0] <= 111)
        chars[0] += 32;
        return String.valueOf(chars);
    }
}
