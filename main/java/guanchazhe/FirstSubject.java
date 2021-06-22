package guanchazhe;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author: MingShi
 * @date: 2021/6/3
 * @description:
 */
public class FirstSubject implements Subject<String>{

    private final Set<Observer<String>> observers = new CopyOnWriteArraySet<>();

    @Override
    public void registerObserver(Observer<String> observer) {
        this.observers.add(observer);
    }

    @Override
    public void unRegisterObserver(Observer<String> observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String event) {
        this.observers.forEach(observer->observer.observer(event));
    }
}
