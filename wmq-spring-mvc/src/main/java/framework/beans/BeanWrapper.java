package framework.beans;

/**
 * @author wangmengqi
 * @date 2020/7/1 21:19
 */
public class BeanWrapper {

    private Object wrapperInstance;

    private Class<?> wrapperClass;

    public Object getWrapperInstance() {
        return wrapperInstance;
    }

    public void setWrapperInstance(Object wrapperInstance) {
        this.wrapperInstance = wrapperInstance;
    }

    public Class<?> getWrapperClass() {
        return wrapperClass;
    }

    public void setWrapperClass(Class<?> wrapperClass) {
        this.wrapperClass = wrapperClass;
    }

    public BeanWrapper(Object instance) {
        this.wrapperInstance = instance;
        this.wrapperClass = instance.getClass();
    }
}
