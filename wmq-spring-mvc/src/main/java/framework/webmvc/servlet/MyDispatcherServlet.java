package framework.webmvc.servlet;

import framework.annotation.MyAutowired;
import framework.annotation.MyController;
import framework.annotation.MyRequestMapping;
import framework.annotation.MyService;
import framework.context.ApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 *
 * 使用委派模式
 *
 * @author wangmengqi
 * @Title: MyDispacherServerlet
 * @ProjectName maniuk-parent-hsm
 * @Description:
 * @date 2020/6/30 19:26
 */
public class MyDispatcherServlet extends HttpServlet {

    public static void main(String[] args) {
        int length = "{\\\"appointmentTime\\\":1594276518410,\\\"contactWay\\\":\\\"通话\\\",\\\"mobile\\\":\\\"18600290626\\\",\\\"reservationTime\\\":\\\"2020-07-09 14:45\\\",\\\"serviceType\\\":\\\"电话心理咨询服务\\\",\\\"sourceChannel\\\":\\\"retailer0009\\\",\\\"userName\\\":\\\"汪梦奇\\\"}\"\"".length();
        System.out.println(length);
        System.out.println("manniu-h5oss-test.manniuhealth.com".length());
    }

    private ApplicationContext applicationContext;

    private Properties contextConfig = new Properties();

    private List<String> classNameList = new ArrayList();

    private Map<String, Object> context = new HashMap<String, Object>();

    private List<HandlerMapping> handlerMappingList = new ArrayList<>();

    private Map<String, Method> handlerMapping = new HashMap<String, Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");

        if (!handlerMapping.containsKey(url)) {
            resp.getWriter().write("404 Not Found");
            return;
        }
        Map<String, String[]> parameterMap = req.getParameterMap();
        Method method = handlerMapping.get(url);

        String simpleName = method.getDeclaringClass().getSimpleName();
        Object instance = context.get(toLowerFirstCase(simpleName));

        method.invoke(instance, new Object[]{req, resp, parameterMap.get("name")[0]});
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化context
        applicationContext = new ApplicationContext(config.getInitParameter("contextConfigLocation"));

        // 1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        // 2.扫描相关的类
        doScanner(contextConfig.getProperty("package"));

        // ======================IOC=====================
        // 3.初始化ioc容器，将扫描到的相关的类实例化，保存到ioc容器
        doInstance();

        // ========注意：AOP应该在DI之前==========

        // ======================DI======================
        // 4.完成依赖注入
        doAutowired();

        // ======================MVC======================
        // 5.初始化handlerMapping
        doInitHandlerMapping();

        System.out.println("My Spring Framework Init Over.");

    }

    private void doInitHandlerMapping() {
        if (context.isEmpty()) {
            return;
        }
        for (Map.Entry<Object, Object> entry : contextConfig.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }
            String baseUrl = "";
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                String value = clazz.getAnnotation(MyRequestMapping.class).value();
                baseUrl = value;
            }
            for (Method method : clazz.getMethods()) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                    continue;
                }
                String url = method.getAnnotation(MyRequestMapping.class).value();
                String realUrl = "/" + baseUrl + "/" + url;
                realUrl.replaceAll("/+", "/");
                handlerMapping.put(realUrl, method);
                System.out.println("Mapped:" + url + "," + method);
            }
        }
    }

    private void doAutowired() {
        if (context.isEmpty()) {
            return;
        }
        for (Map.Entry<Object, Object> entry : contextConfig.entrySet()) {
            for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(MyAutowired.class)) {
                    MyAutowired myAutowired = field.getAnnotation(MyAutowired.class);
                    String beanName = myAutowired.value();
                    if ("".endsWith(beanName)) {
                        beanName = field.getName();
                    }
                    field.setAccessible(true);
                    try {
                        field.set(entry.getValue(), context.get(beanName));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void doInstance() {
        if (classNameList.isEmpty()) {
            return;
        }
        for (String className : classNameList) {
            try {
                Class<?> clazz = Class.forName(className);
                if (isInstance(clazz)) {
                    Object instance = clazz.newInstance();
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    context.put(beanName, instance);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isInstance(Class<?> clazz) {
        return clazz.isAnnotationPresent(MyController.class) || clazz.isAnnotationPresent(MyService.class);
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars = simpleName.toCharArray();
        // if (chars[0] >=111 && chars[0] <= 111)
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doScanner(String location) {
        URL url = this.getClass().getClassLoader().getResource("/" + location.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        File[] files = classPath.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                doScanner(location + "." + file.getName());
            } else {
                if (file.getName().endsWith(".class")) {
                    String className = location + "." + file.getName().replace(".class", "");
                    classNameList.add(className);
                }
            }

        }
    }

    private void doLoadConfig(String contextConfigLocation) {
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
}
