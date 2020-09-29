/**
 * //给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * //
 * // 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * //
 * // 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * //
 * // 示例：
 * //
 * // 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * //输出：7 -> 0 -> 8
 * //原因：342 + 465 = 807
 * //
 * // Related Topics 链表 数学
 * // 👍 4909 👎 0
 */
package leetcode.editor.cn;
import java.util.ArrayList;
import java.util.List;

public class AddTwoNumbers {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        ListNode listNode = new ListNode(2);
        listNode.next = new ListNode(0);
        listNode.next.next = new ListNode(9);
        String str = "18600290626";
        String substring = str.substring(str.length() - 4);
        System.out.println(substring);
    }
}


/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /**
         * leetCode 官方答案
         */
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;

        /**
         * mycoding
         */
        /*if (l1 == null && l2 == null){
            return null;
        }
        if (l1 == null){
            return l2;
        }
        if (l2 == null){
            return l1;
        }
        // 计算第一个节点的值
        int addValue = 0;
        int firstSum;
        if (l1.val + l2.val >= 10){
            firstSum = l1.val + l2.val -10;
            addValue = 1;
        }else {
            firstSum = l1.val + l2.val;
        }
        ListNode firstNode = new ListNode(firstSum);
        // 计算后面节点的值
        ListNode l1Next = l1.next;
        ListNode l2Next = l2.next;

        ListNode nowNode = firstNode;

        while (l1Next != null || l2Next != null){
            // 当两个节点都不为null
            if (l1Next != null && l2Next != null){
                int sum = l1Next.val + l2Next.val;
                // 判断是否需要进1
                if (sum + addValue< 10){
                    nowNode.next = new ListNode(sum + addValue);
                    addValue = 0;
                }else {
                    nowNode.next = new ListNode(sum + addValue -10);
                    addValue = 1;
                }
                l1Next = l1Next.next;
                l2Next = l2Next.next;
                nowNode = nowNode.next;
                continue;
            }
            // 当其中一个节点为null
            if (l1Next == null){
                // 判断是否需要进1
                if (l2Next.val + addValue< 10){
                    nowNode.next = new ListNode(l2Next.val + addValue);
                    addValue = 0;
                }else {
                    nowNode.next = new ListNode(l2Next.val + addValue -10);
                    addValue = 1;
                }
                l2Next = l2Next.next;
                nowNode = nowNode.next;
                continue;
            }
            if (l2Next == null){
                // 判断是否需要进1
                if (l1Next.val + addValue< 10){
                    nowNode.next = new ListNode(l1Next.val + addValue);
                    addValue = 0;
                }else {
                    nowNode.next = new ListNode(l1Next.val + addValue -10);
                    addValue = 1;
                }
                l1Next = l1Next.next;
                nowNode = nowNode.next;
                continue;
            }
        }
        if (addValue == 1 && l1Next == null && l2Next == null){
            nowNode.next = new ListNode(1);
        }
        return firstNode;*/
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
