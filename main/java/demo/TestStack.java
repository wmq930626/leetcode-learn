package demo;

import java.util.*;

/**
 * 利用栈来判断{{[(())]}}是否合法 包括顺序，和左右符号的匹配
 */
public class TestStack {
    private static final List<Character> left = Arrays.asList('{','[','(');
    private static final List<Character> right = Arrays.asList('}',']',')');
    private static final Map<Character,Character> map = new HashMap();

    static {
        map.put('{','}');
        map.put('(',')');
        map.put('[',']');
    }
    public static void main(String[] args) {
        boolean b = checkStr("{{[(())]}}");
        System.out.println(b);
    }

    public static boolean checkStr(String str){
        Stack<Character> stack = new Stack();
        for (int i = 0; i < str.length(); i++) {
            if (left.contains(str.charAt(i))){
                if (!stack.isEmpty()){
                    Character before = stack.pop();
                    if (str.charAt(i) > before){
                        return false;
                    }
                    stack.push(before);
                    stack.push(str.charAt(i));
                }else {
                    stack.push(str.charAt(i));
                }
                continue;
            }
            if (right.contains(str.charAt(i))){
                if (stack.isEmpty()){
                    return false;
                }else {
                    if (str.charAt(i) != map.get(stack.pop())){
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

}
