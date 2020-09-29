/**
//给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。 
//
// 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。 
//
// 你可以假设 nums1 和 nums2 不会同时为空。 
//
// 
//
// 示例 1: 
//
// nums1 = [1, 3]
//nums2 = [2]
//
//则中位数是 2.0
// 
//
// 示例 2: 
//
// nums1 = [1, 2]
//nums2 = [3, 4]
//
//则中位数是 (2 + 3)/2 = 2.5
// 
// Related Topics 数组 二分查找 分治算法 
// 👍 3216 👎 0

*/
package leetcode.editor.cn;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        int[] nu1 = {1,2,3,4};
        int[] nu2 = {4,5,6,7};
        double medianSortedArrays = solution.findMedianSortedArrays(nu1, nu2);
        System.out.println(medianSortedArrays);

    }
}

class Solution4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        int len1 = nums1.length, len2 = nums2.length;
        double res;
        boolean isOushu = false;
        if ((len1 + len2) % 2 == 0){
            isOushu = true;
        }
        List<Integer> integerList = new ArrayList();
        while (i < len1 || j < len2){
            boolean big = false;
            if (i == len1-1 && j == len2-1){
                break;
            }
            if (i == len1-1){
                integerList.add(nums2[j]);
                j++;
                continue;
            }
            if (j == len2-1){
                integerList.add(nums1[i]);
                i++;
                continue;
            }
            if (nums1[i] <= nums2[j]){
                integerList.add(nums1[i]);
            }else{
                integerList.add(nums2[j]);
                big = true;
            }
            if (big && j < len2){
                j++;
            }
            if (!big && i < len1){
                i++;
            }
        }
        if (isOushu){
            res =  (double) ((integerList.get((len1 + len2)/2 - 1) + integerList.get((len1 + len2)/2)))/2;
        }else {
            res = integerList.get((len1 + len2)/2);
        }
        return res;
    }
}
