package demo;

import java.util.BitSet;

public class BloomFileter {

    //使用加法hash算法，所以定义了一个8个元素的质数数组
    private static final int[] primes = new int[]{2, 3, 5, 7, 11, 13, 17, 19};
    //用八个不同的质数，相当于构建8个不同算法
    private Hash[] hashList = new Hash[primes.length];
    //创建一个长度为10亿的比特位
    private BitSet bits = new BitSet(256 << 22);

    public BloomFileter() {
        for (int i = 0; i < primes.length; i++) {
            //使用8个质数，创建八种算法
            hashList[i] = new Hash(primes[i]);
        }
    }

    //添加元素
    public void add(String value) {
        for (Hash f : hashList) {
            //算出8个信息指纹，对应到2的32次方个比特位上
            bits.set(f.hash(value), true);
        }
    }

    //判断是否在布隆过滤器中
    public boolean contains(String value) {
        if (value == null) {
            return false;
        }
        for (Hash f : hashList) {
            //查看8个比特位上的值
            if (!bits.get(f.hash(value))){
                return false;
            }
        }
        return true;
    }

    //加法hash算法
    public static class Hash {

        private int prime;

        public Hash(int prime) {
            this.prime = prime;
        }

        public int hash(String key) {
            int hash, i;
            for (hash = key.length(), i = 0; i < key.length(); i++) {
                hash += key.charAt(i);
            }
            return (hash % prime);
        }
    }

    public static void main(String[] args) {
        BloomFileter bloomFileter = new BloomFileter();
        long begin = System.currentTimeMillis();
        System.out.println("99999999是否存在:" + bloomFileter.contains("999999999"));
        long end = System.currentTimeMillis();
        System.out.println("判断99999999是否在线使用了:" + (begin - end));

        long begin1 = System.currentTimeMillis();
        bloomFileter.add("999999999");
        long end1 = System.currentTimeMillis();
        System.out.println("设置99999999是否在线使用了:" + (begin1 - end1));

        System.out.println("99999999是否存在:" + bloomFileter.contains("999999999"));
    }
}
