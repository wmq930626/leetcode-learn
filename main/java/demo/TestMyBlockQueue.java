package demo;

import java.util.concurrent.*;

public class TestMyBlockQueue {
    private static final int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();
    public static void main(String[] args) {
        MyBlockQueue<String> stringMyBlockQueue = new MyBlockQueue<>(5);
        // 直接创建一个线程池 推荐用法
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(AVAILABLE_PROCESSORS*4,AVAILABLE_PROCESSORS*4,
                30, TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(100));
        // 借助Executors创建线程池 不推荐使用
        ExecutorService threadPool = Executors.newFixedThreadPool(20);
        for (int i = 0;i<10;i++) {
            // 10个线程不put数据
            threadPoolExecutor.execute(()-> {
                try {
                    while (true) {
                        stringMyBlockQueue.put("DSADSAD");
                        TimeUnit.SECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        // 10个线程不take数据
        for (int i = 0;i<2;i++) {
            threadPoolExecutor.execute(()-> {
                try {
                    while (true) {
                        stringMyBlockQueue.take();
                        TimeUnit.SECONDS.sleep(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
