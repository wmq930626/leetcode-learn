package demo;

/**
 * @author WMQ
 * @date 2020/11/17
 * @description
 */
public class TestRecursion {
    public static void main(String[] args) {
        String x = "x";
        String y = "y";
        String z = "z";
        hanio(3, x, y, z);

    }

    /**
     *
     * 使用递归解决汉诺塔问题
     *
     * 1.把n-1个盘子从x->y
     * 2.把x上最后一个盘子从x->z
     * 3.把y上n-1个盘子从y->z
     * @param n
     * @param x
     * @param y
     * @param z
     */
    public static void hanio(int n, String x, String y, String z) {
        if (n < 1) {
            System.out.println("汉诺塔的层数不能小于1");
        } else if (n == 1) {
            System.out.println("移动: " + x + " -> " + z);
            return;
        } else {
            hanio(n - 1, x, z, y);
            System.out.println("移动: " + x + " -> " + z);
            hanio(n - 1, y, x, z);
        }

    }

}
