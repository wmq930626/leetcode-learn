package demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;

public class TestThreadLocal {

    private ThreadLocal<SimpleDateFormat> threadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

    public static void main(String[] args) {
        int[] arr = { 6, 1, 2, 7, 9, 11, 4, 5, 10, 8 };
        System.out.println("原始数据: " + Arrays.toString(arr));
        customQuickSort(arr, 0, arr.length - 1);
        System.out.println("快速排序: " + Arrays.toString(arr));
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


}
