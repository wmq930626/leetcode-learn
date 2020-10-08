package demo;

import java.text.SimpleDateFormat;

public class TestThreadLocal {

    private ThreadLocal<SimpleDateFormat> threadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    public static void main(String[] args) {

    }

}
