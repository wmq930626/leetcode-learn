package test;

/**
 * -Xms100M
 * -Xmx100M
 * -Xmn50M
 * -Xss1M
 * -XX:SurvivorRatio=8
 * -XX:MaxTenuringThreshold=5
 * -XX:PretenureSizeThreshold=10M
 * -XX:+UseParNewGC
 * -XX:+UseConcMarkSweepGC
 * -XX:+PrintGC
 *
 * 老年代 50M
 * Eden 40M
 * S1 5M
 * S2 5M
 */
public class TestYGC {
    public static void main(String[] args) {
        byte[] array1 = new byte[1024*1024*20];// 20
        byte[] array2 = new byte[1024*1024*20];// 20
        //array1 = null;
        //array2 = null;
        byte[] array3 = new byte[1024*1024*1];// 10
        byte[] array4 = new byte[1024*1024*8];// 18
        //array3 = null;
        //array4 = null;
        byte[] array5 = new byte[1024*1024*1];// 19
        byte[] array6 = new byte[1024*1024*8];// 27
        array5 = null;
        array6 = null;
        byte[] array7 = new byte[1024*1024*1];
        byte[] array8 = new byte[1024*1024*8];
        array7 = null;
        array8 = null;
        byte[] array9 = new byte[1024*1024*1];
        byte[] array10 = new byte[1024*1024*8];
        array9 = null;
        array10 = null;
        byte[] array11 = new byte[1024*1024*1];
        byte[] array12 = new byte[1024*1024*8];
        array11 = null;
        array12 = null;
        byte[] array13 = new byte[1024*1024*1];
        byte[] array14 = new byte[1024*1024*8];
        array13 = null;
        array14 = null;
        byte[] array15 = new byte[1024*1024*1];
        byte[] array16 = new byte[1024*1024*8];
        array15 = null;
        array16 = null;
        byte[] array17 = new byte[1024*1024*1];
        byte[] array18 = new byte[1024*1024*8];
        array17 = null;
        array18 = null;
        byte[] array19 = new byte[1024*1024*1];
        byte[] array20 = new byte[1024*1024*8];
        array19 = null;
        array20 = null;


    }

    /*private static void get(){
        get();
    }*/
}
