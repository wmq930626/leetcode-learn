package learn;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author WMQ
 * @date 2020/12/11
 * @description
 */
public class TestThread {

    private static int count = 1;

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) {

        new Thread(()->{
            while (true) {
                if (count <= 100){
                    lock.lock();
                    if (count % 2 == 1){
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println(Thread.currentThread() + ":\t" + count);
                        count++;
                        condition.signal();
                    }
                    lock.unlock();
                }else {
                    break;
                }
            }
        }).start();

        new Thread(()->{
            while (true) {
                if (count <= 100){
                    lock.lock();
                    if (count % 2 == 0){
                        try {
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else {
                        System.out.println(Thread.currentThread().getName() + ":\t" + count);
                        count++;
                        condition.signal();
                    }
                    lock.unlock();
                }else {
                    break;
                }
            }
        }).start();

    }
}
