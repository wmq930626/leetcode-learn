package demo;

/**
 * @author WMQ
 * @date 2020/11/30
 * @description
 */
public class TestInteger {
    public static void main(String[] args) {
        Integer a = new Integer(10);
        Integer b = new Integer(10);
        Integer c = 10;
        Integer d = 10;

        Integer e = 128;
        Integer f = 128;

        int g = 10;

        System.out.println(a == b);// false
        System.out.println(a == c);// false
        System.out.println(c == d);// true
        System.out.println(e == f);// false
        System.out.println(c == g);

        System.out.println(square(11,0.000000001));
        System.out.println(Math.sqrt(11));
    }

    /**
     * 二分法开平方
     * @param number
     * @param precision 精度
     * @return
     */
    public static double square(double number,double precision){
        double min = 0;
        double max = number;
        double mid = min + (max - min) / 2;
        double res = mid * mid;
        while (Math.abs(res - number) > precision){
            if (mid * mid > number){
                max = mid;
            }else {
                min = mid;
            }
            mid = min + (max - min) / 2;
            res = mid * mid;
        }
        return mid;
    }
}
