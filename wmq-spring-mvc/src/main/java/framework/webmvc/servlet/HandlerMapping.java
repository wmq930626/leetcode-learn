package framework.webmvc.servlet;

import java.lang.reflect.Method;

/**
 * @author wangmengqi
 * @date 2020/7/7 20:21
 */
public class HandlerMapping {
    public static void main(String[] args) {
        long aLong = Long.valueOf("694273290637807616");
        System.out.println(aLong);
    }
    // url
    private String url;
    // 调用方法
    private Method method;
    // 调用类
    private Object controller;

    public HandlerMapping(String url, Method method, Object controller) {
        this.url = url;
        this.method = method;
        this.controller = controller;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getController() {
        return controller;
    }

    public void setController(Object controller) {
        this.controller = controller;
    }
}
