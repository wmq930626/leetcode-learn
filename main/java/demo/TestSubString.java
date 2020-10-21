package demo;

import com.sun.deploy.util.StringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 字符串匹配算法
 */
public class TestSubString {
    public static void main(String[] args) {
        String parent = "hello world ever one";
        String sub1 = "l";
        String sub2 = "llo wor";
        String sub3 = " world hello";
        boolean isSub1 = isSub(parent, sub1);
        System.out.println(isSub1);
        boolean isSub2 = isSub(parent, sub2);
        System.out.println(isSub2);
        boolean isSub3 = isSub(parent, sub3);
        System.out.println(isSub3);
        System.out.println(maxCommonSun(parent,sub1));
        System.out.println(maxCommonSun(parent,sub2));
        System.out.println(maxCommonSun(parent,sub3));
        System.out.println(reverseString(parent));
    }

    /**
     * 判断一个字符串是不是字串
     * @param parent
     * @param sub
     * @return
     */
    public static boolean isSub(String parent, String sub) {
        boolean isSub = false;
        for (int i = 0; i < parent.length(); i++) {
            isSub = true;
            for (int j = 0; j < sub.length(); j++) {
                // 如果父串遍历完了，字串还没遍历完，证明一定不是字串
                if (i == parent.length()) {
                    return false;
                }
                // 如果不相等就直接跳出循环
                if (sub.charAt(j) != parent.charAt(i)) {
                    isSub = false;
                    break;
                }
                // 如果相等 父串往后移动一位
                else {
                    i++;
                }
            }
            // 如果内层循环遍历完了还是true，就证明包含字串
            if (isSub) {
                return true;
            }
        }
        return isSub;
    }

    /**
     * 求两个字符串的最大公共字串
     * @param a
     * @param b
     * @return
     */
    public static String maxCommonSun(String a, String b) {
        String maxSubStr = "";
        int max_len = 0;
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                if (a.charAt(i) == b.charAt(j)){
                    int m , n;
                    for (m = i, n = j; m < a.length() && n < b.length(); m++, n++) {
                        if (a.charAt(m) != b.charAt(n)){
                            break;
                        }
                        if (max_len < m - i + 1){
                            max_len = m - i;
                            maxSubStr = a.substring(i, m + 1);
                        }
                    }
                }
            }
        }

        return maxSubStr;
    }

    /**
     * 字符串反转
     * @param str
     * @return
     */
    public static String reverseString(String str){
        List<String> stringList = Arrays.asList(str.split(" "));
        Collections.reverse(stringList);
        String reverse = "";
        for (int i = 0; i < stringList.size(); i++) {
            if (i == stringList.size() - 1){
                reverse += stringList.get(i);
            }else {
                reverse += stringList.get(i) + " ";
            }
        }
        return reverse;
    }

}
