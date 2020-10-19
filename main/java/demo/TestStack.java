package demo;

import java.util.*;

/**
 *
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
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);
        Node node9 = new Node(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;
        node8.next = node9;
        Node reverse = reverse(node1, 3);
        while (reverse != null){
            System.out.println(reverse.value);
            reverse = reverse.next;
        }
    }

    /**
     * 利用栈来判断{{[(())]}}是否合法 包括顺序，和左右符号的匹配
     * @param str
     * @return
     */
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

    /**
     *  按照指定个数反转链表
     *
     *  eg:123456 -> 321654
     *
     * @param head
     * @param k
     * @return
     */
    public static Node reverse(Node head, int k){
        Stack<Node> nodes = new Stack<>();
        Node newHead = null;
        int flag = 0;
        int count = 1;
        Node before = null;
        while (head != null){
            nodes.push(head);
            head = head.next;
            if (count == k){
                if (flag == 0){
                    newHead = nodes.pop();
                    before = newHead;
                }
                while (!nodes.isEmpty()){
                    Node node = nodes.pop();
                    before.next = node;
                    before = node;
                }
                count = 0;
                flag++;
            }
            if (head == null){
                before.next = null;
            }
            count++;
        }
        return newHead;
    }


    static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

}
