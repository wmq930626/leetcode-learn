import java.util.HashMap;

/**
 * @author WMQ
 * @date 2020/12/9
 * @description
 */
public class Test {
    public static void main(String[] args) {
        HashMap<String, AbstractTest> maps = new HashMap<>();
        maps.put("a",new AbstractTestA());
        maps.put("b",new AbstractTestB());
        AbstractTest a = maps.get("a");
        a.methodA();
        AbstractTest b = maps.get("b");
        b.methodA();
    }
}
