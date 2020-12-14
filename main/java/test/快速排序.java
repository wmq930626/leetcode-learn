package test;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Random;

public class 快速排序 {

    private ThreadLocal<SimpleDateFormat> threadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    public static void main(String[] args) {
        Random random = new Random();;
        int[] arr = new int[10000000];
        for (int i = 0; i < 10000000; i++) {
            arr[i] = random.nextInt(10000000);
        }
        long start = System.currentTimeMillis();
        //System.out.println("原始数据: " + Arrays.toString(arr));
        //customQuickSort(arr, 0, arr.length - 1);
        searchK(arr, 0, arr.length - 1,9999);
        /*Arrays.sort(arr);
        int i = arr[998];
        System.out.println(i);*/
        long end = System.currentTimeMillis();
        System.out.println("快速排序查找size为1000000的数组中第K大的元素一共耗时：" + (end - start));
        //System.out.println("快速排序: " + Arrays.toString(arr));
    }

    public static void customQuickSort(int[] arr, int low, int high) {
        if (low >= high){
            return;
        }
        int temp = arr[low];
        int left = low;
        int right = high;
        while (left < right){
            while (temp <= arr[right] && left < right){
                right--;
            }
            while (temp >= arr[left] && left < right){
                left++;
            }
            int t = arr[right];
            arr[right] = arr[left];
            arr[left] = t;
        }

        arr[low] = arr[left];
        arr[left] = temp;
        customQuickSort(arr,low,left - 1);
        customQuickSort(arr, left + 1, high);

    }

    /**
     * 使用快速排序查找第K大的元素
     * @param arr
     * @param low
     * @param high
     * @param k
     */
    public static void searchK(int[] arr, int low, int high,int k) {
        int temp = arr[low];
        int left = low;
        int right = high;
        while (left < right){
            while (temp <= arr[right] && left < right){
                right--;
            }
            while (temp >= arr[left] && left < right){
                left++;
            }
            int t = arr[right];
            arr[right] = arr[left];
            arr[left] = t;
        }
        arr[low] = arr[left];
        arr[left] = temp;
        if (left == k - 1){
            System.out.println("数组中第K大的元素是" + arr[left]);
            return;
        }else if (left > k - 1) {
            searchK(arr,low,left - 1 ,k);
        }else {
            searchK(arr, left + 1, high,k);
        }
    }


}
