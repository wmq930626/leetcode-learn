package learn;

/**
 * @author WMQ
 * @date 2020/12/11
 * @description
 */
public class TestClass {
    public static void main(String[] args) {

        Job1Class job1Class = new Job1Class(new Job2Class());
        job1Class.handler(true);
        job1Class.handler(false);
    }
}
