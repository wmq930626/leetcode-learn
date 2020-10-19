package demo;


import java.util.Stack;

public class LinkDesc {

    public static void main(String[] args) {
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


    static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static Node  reverse(Node head,int k){
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
}
