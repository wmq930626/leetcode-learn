/**
//给定一个可包含重复数字的序列，返回所有不重复的全排列。 
//
// 示例: 
//
// 输入: [1,1,2]
//输出:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//] 
// Related Topics 回溯算法 
// 👍 426 👎 0

*/
package leetcode.editor.cn;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PermutationsIi {
    public static void main(String[] args) {
        Solution5 solution = new Solution5();
    }
}


class Solution5 {
    List<List<Integer>> permutations = new LinkedList<List<Integer>>();  // 结果
    List<Integer> path = new LinkedList<Integer>();                // 路径
    boolean[] mark;     // 标记是否用过
    int length;         // 数组长度
    public List<List<Integer>> permuteUnique(int[] nums) {
        length = nums.length;
        if (length == 0) {
            return permutations;
        }   // 空数组
        Arrays.sort(nums);                          // 排序，可有可无，原因见后文注释
        mark = new boolean[length];
        backtrack(nums);
        return permutations;
    }

    public void backtrack(int[] nums) {
        // 结束条件
        if (path.size() == length) {
            permutations.add(new LinkedList(path));
            return;
        }
        for (int i = 0; i < length; i++) {
            // 被用过了
            if (mark[i]) {
                continue;
            }
            // 需要排序，相同的开头是相邻的
            if (i > 0 && nums[i] == nums[i - 1] && mark[i - 1]) {
                continue;
            }

            mark[i] = true;         // 标记被用过
            path.add(nums[i]);      // 加入路径
            backtrack(nums);        // 深度搜索路径
            path.remove(path.size() - 1);   // 从路径中去掉刚才加的
            mark[i] = false;                // 恢复没用过
        }
    }

}

