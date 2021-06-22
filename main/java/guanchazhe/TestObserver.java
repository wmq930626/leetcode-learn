package guanchazhe;

/**
 * @author: MingShi
 * @date: 2021/6/3
 * @description:
 */
public class TestObserver {
    public static void main(String[] args) {
        ObserverA observerA = new ObserverA();
        ObserverB observerB = new ObserverB();
        FirstSubject firstSubject = new FirstSubject();
        firstSubject.registerObserver(observerA);
        firstSubject.registerObserver(observerB);
        firstSubject.notifyObservers("===");
    }
}
