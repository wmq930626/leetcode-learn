package rxjava;


import rx.Observable;
import rx.Subscriber;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author: MingShi
 * @date: 2021/6/3
 * @description:
 */
public class RxJavaFirst {
    public static void main(String[] args) throws InterruptedException {
        /**
         * 在这里我们创建一个 Observable 并使其带有一个回调，该回调将在订阅者出现时
         * 立即被触发(1)。此时，Observer 将产生一个字符串值(2)，并将流的结束信号发送给订阅者(3)。
         */
        Observable<String> observable = Observable.create(
                sub -> { // (1)
                    sub.onNext("Hello, reactive world!"); // (2)
                    sub.onCompleted(); // (3)
                }
        );

        /**
         * Subscriber 必须实现 Observer 方法并定义对新事件(1)、流完成(2)和错误(3)
         * 的响应。
         */
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) { // (1)
                System.out.println(s);
            }
            @Override
            public void onCompleted() { // (2)
                System.out.println("Done!");
            }
            @Override
            public void onError(Throwable e) { // (3)
                System.err.println(e);
            }
        };

        /**
         * 将 observable 和 subscriber 实例串接在一起
         */
        observable.subscribe(subscriber);

        /**
         * 以使用 lambda重写示例
         */
        Observable.create(
                sub -> {
                    sub.onNext("Hello, reactive world!");
                    sub.onCompleted();
                }
        ).subscribe(
                System.out::println,
                System.err::println,
                () -> System.out.println("Done!")
        );

        /**
         * RxJava 库为创建 Observable 实例和 Subscriber 实例提供了很大的灵活性。我们可以使
         * 用 just 来引用元素、使用旧式数组，或者使用 from 通过 Iterable 集合来创建 Observable
         * 实例，代码如下所示
         */
        Observable<String> just = Observable.just("1", "2", "3", "4");
        just.map(i->i+"1").forEach(System.out::println);
        Observable<String> from = Observable.from(new String[]{"A", "B", "C"});
        Observable.zip(just,from,(x,y)->x+y).forEach(System.out::println);
        /*Observable<Object> from1 = Observable.from(Collections.emptyList());

        Observable<String> hello = Observable.fromCallable(() ->"Hello "); // (1)
        hello.subscribe(subscriber);
        Future<String> future = Executors.newCachedThreadPool().submit(() -> "World");
        Observable<String> world = Observable.from(future); // (2)
        world.subscribe(subscriber);
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(e -> System.out.println("Received: " + e));
        Thread.sleep(5000);*/
    }
}
