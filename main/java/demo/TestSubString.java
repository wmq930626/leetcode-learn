package demo;

/**
 * 判断一个字符串是不是字串
 */
public class TestSubString {
    public static void main(String[] args) {
        String parent = "hello world";
        String sub1 = "l";
        String sub2 = "llo wor";
        String sub3 = " world";
        boolean isSub1 = isSub(parent, sub1);
        System.out.println(isSub1);
        boolean isSub2 = isSub(parent, sub2);
        System.out.println(isSub2);
        boolean isSub3 = isSub(parent, sub3);
        System.out.println(isSub3);
    }

    public static  boolean isSub(String parent,String sub){
        boolean isSub = false;
        for (int i = 0; i < parent.length(); i++) {
            isSub = true;
            for (int j = 0 ; j < sub.length(); j++){
                // 如果父串遍历完了，字串还没遍历完，证明一定不是字串
                if (i == parent.length()){
                    return false;
                }
                // 如果不相等就直接跳出循环
                if (sub.charAt(j) != parent.charAt(i)){
                    isSub = false;
                    break;
                }
                // 如果相等 父串往后移动一位
                else {
                    i++;
                }
            }
            // 如果内层循环遍历完了还是true，就证明包含字串
            if (isSub){
                return true;
            }
        }
        return isSub;
    }
}
