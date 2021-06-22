package guanchazhe;

/**
 * @author: MingShi
 * @date: 2021/6/3
 * @description:
 */
public interface Subject<T> {
    void registerObserver(Observer<T> observer);
    void unRegisterObserver(Observer<T> observer);
    void notifyObservers(T event);
}
