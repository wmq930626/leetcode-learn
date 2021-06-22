package guanchazhe;

/**
 * @author: MingShi
 * @date: 2021/6/3
 * @description:
 */
public class ObserverA implements Observer<String>{
    @Override
    public void observer(String event) {
        System.out.println("observer A " + event);
    }
}
