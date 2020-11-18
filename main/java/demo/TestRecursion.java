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
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,11};
        erFen(arr,11);

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

    /**
     * 二分法求解n在数组中是否出现的问题
     * @param arr
     * @param n
     */
    public static void erFen(int[] arr, int n){
        int low = 0;
        int high = arr.length - 1;
        while (low <= high){
            int middle = (low + high)/2;
            if (arr[middle] == n){
                System.out.println("数据" + n + "在数组中出现过,下标值为：" + middle);
                return;
            }
            if (arr[middle] > n){
                high = middle-1;
            }

            if (arr[middle] < n){
                low = middle + 1;
            }
        }
        System.out.println("数据" + n + "在数组中没有出现过");
    }

    /**
     * 在一个有序数组中，查找出第一个大于 9 的数字，假设一定存在。
     * 例如，arr = { -1, 3, 3, 7, 10, 14, 14 }; 则返回 10
     * @param arr
     * @param n
     */
    public static void erFen2(int[] arr, int n){
        int low = 0;
        int high = arr.length - 1;
        while (low <= high){
            int middle = (low + high)/2;
            if (arr[middle] > n && (middle == 0 || arr[middle - 1] <= n)) {
                System.out.println("第一个比" + n + "大的数字是" + arr[middle]);
                break;
            }
            if (arr[middle] > n){
                high = middle-1;
            }
            if (arr[middle] < n){
                low = middle + 1;
            }
        }
        System.out.println("数据" + n + "在数组中没有出现过");
    }


}
