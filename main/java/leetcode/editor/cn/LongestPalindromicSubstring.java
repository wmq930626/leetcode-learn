/**
//给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。 
//
// 示例 1： 
//
// 输入: "babad"
//输出: "bab"
//注意: "aba" 也是一个有效答案。
// 
//
// 示例 2： 
//
// 输入: "cbbd"
//输出: "bb"
// 
// Related Topics 字符串 动态规划 
// 👍 2715 👎 0

*/
package leetcode.editor.cn;
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String abcbadcaeggeacd = solution.longestPalindrome("abcba");
        System.out.println(abcbadcaeggeacd);
    }
}

class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String ans = "";
        // 字串长度的遍历
        for (int l = 0; l < n; ++l) {
            // 遍历字符串
            for (int i = 0; i + l < n; ++i) {
                int j = i + l;
                // 字串长度位1
                if (l == 0) {
                    dp[i][j] = true;
                // 字串长度位2
                } else if (l == 1) {
                    dp[i][j] = (s.charAt(i) == s.charAt(j));
                // 字串长度大于3
                } else {
                    dp[i][j] = (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]);
                }
                if (dp[i][j] && l + 1 > ans.length()) {
                    ans = s.substring(i, j + 1);
                }
            }
        }
        return ans;
    }
}
