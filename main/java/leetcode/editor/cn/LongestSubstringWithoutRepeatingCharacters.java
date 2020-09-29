/**
 * //给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * //
 * // 示例 1:
 * //
 * // 输入: "abcadcba"
 * //输出: 3
 * //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * //
 * //
 * // 示例 2:
 * //
 * // 输入: "bbbbb"
 * //输出: 1
 * //解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * //
 * //
 * // 示例 3:
 * //
 * // 输入: "pwwkew"
 * //输出: 3
 * //解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * //     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * //
 * // Related Topics 哈希表 双指针 字符串 Sliding Window
 * // 👍 4328 👎 0
 */
package leetcode.editor.cn;
import java.util.HashMap;
import java.util.Map;


public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        Solution3 solution = new Solution3();
        int l = solution.lengthOfLongestSubstring("12213445112334556");
        System.out.println(l);
    }
}

class Solution3 {

    /**
     * a b c a d c b a
     * 0 1 2 3 4 5 6 7
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int res = 1;
        int flag=-1;
        Map<Character, Integer> map = new HashMap();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                flag = Math.max(flag,map.get(s.charAt(i)));
            }
            map.put(s.charAt(i),i);
            res = Math.max(res,i-flag);
        }
        return res;
    }
}
