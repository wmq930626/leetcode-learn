package demo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockQueue<E> {
    /**
     * 锁 保证并发安全
     */
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 队列元素
     */
    private final Object[] items;

    /**
     * 取元素的下标
     */
    private int takeIndex;

    /**
     * 放元素的下标
     */
    private int putIndex;

    /**
     * 队列中元素的个数
     */
    private int count;

    /**
     * 用于队列空的时候阻塞
     */
    private Condition isEmpty = lock.newCondition();

    /**
     * 用于队列满的时候阻塞
     */
    private Condition isFull = lock.newCondition();

    public MyBlockQueue(){
        this(16);
    }

    public MyBlockQueue(int len) {
        // 队列长度不能小于0
        if (len < 0)
            throw new IllegalArgumentException("param is illegal");
        this.items = new Object[len];
    }

    public void put(E e) throws InterruptedException {
        // 加锁
        lock.lock();
        try{
            // 当队列满的时候线程阻塞
            while (count == items.length)
                isFull.await();
            items[putIndex] = e;
            // 判断队列是否插完，是的话就开始从第一个开始插
            if (++putIndex == items.length)
                putIndex = 0;
            // 元素个数加1
            count++;
            // 每插入一个元素就要唤醒阻塞在isEmpty上的线程
            isEmpty.signal();
            System.out.println(Thread.currentThread().getName() + ":放入元素，当前队列中有元素：" + count + "个");
        }finally {
           lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try{
            // 队列为空的时候阻塞
            while (count == 0)
                isEmpty.await();
            E item = (E)items[takeIndex];
            items[takeIndex] = null;
            // 元素个数减1
            count--;
            // 当takeIndex和数组长度相等的时候证明数组中元素已经取完
            if (++takeIndex == items.length)
                takeIndex = 0;
            // 保证下次取的时候数组不越界
            if (takeIndex > 0) takeIndex--;
            // 每获取一个线程就要唤醒阻塞在isFull上的线程
            isFull.signal();
            System.out.println(Thread.currentThread().getName() + ":取出元素，当前队列中有元素：" + count + "个");
            return item;
        }finally {
            lock.unlock();
        }
    }

    /**
     * 获取队列中元素个数
     * @return
     */
    public int getCount() {
        return count;
    }

}
