package demo;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 树的各个节点的称呼
 * 父节点 孩子节点 兄弟节点 叶子节点
 */
public class TestTree {

    public static void main(String[] args) {
        TreeNode root7 = new TreeNode(7);
        TreeNode root2 = new TreeNode(2);
        TreeNode root9 = new TreeNode(9);
        TreeNode root1 = new TreeNode(1);
        TreeNode root3 = new TreeNode(3);
        TreeNode root8 = new TreeNode(8);
        TreeNode root10 = new TreeNode(10);
        root7.setLeft(root2);
        root2.setLeft(root1);
        root9.setLeft(root8);
        root7.setRight(root9);
        root2.setRight(root3);
        root9.setRight(root10);
        /*prePrint(root7);
        System.out.println();
        postPrint(root7);
        System.out.println();
        middlePrint(root7);
        System.out.println();
        System.out.println(selectNode(root7,1).value);
        insertNode(new TreeNode(6),root7);
        middlePrint(root7);*/
        levelPrint(root7);

    }

    /**
     * 树的先序遍历
     * @param root
     */
    public static void prePrint(TreeNode root){
        System.out.print(root.value + "\t");
        if (root.getLeft() != null){
            prePrint(root.left);
        }
        if (root.getRight() != null){
            prePrint(root.right);
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public static void middlePrint(TreeNode root){
        if (root.getLeft() != null){
            middlePrint(root.left);
        }
        System.out.print(root.value + "\t");
        if (root.getRight() != null){
            middlePrint(root.right);
        }
    }
    /**
     * 树的后序遍历
     * @param root
     */
    public static void postPrint(TreeNode root){
        if (root.getLeft() != null){
            postPrint(root.left);
        }
        if (root.getRight() != null){
            postPrint(root.right);
        }
        System.out.print(root.value + "\t");
    }

    /**
     * 层次遍历
     * @param root
     */
    public static void levelPrint(TreeNode root){
        if (root == null){
            return;
        }
        Queue<TreeNode> treeNodes = new ArrayDeque<>();
        treeNodes.add(root);
        while(!treeNodes.isEmpty()){
            root = treeNodes.poll();
            System.out.print(root.value + "\t");
            if (root != null){
                if (root.left != null) {
                    treeNodes.add(root.left);
                }
                if (root.right != null) {
                    treeNodes.add(root.right);
                }
            }
        }

    }

    /**
     * 在二叉树中查找值
     * @param root
     * @param value
     * @return
     */
    public static TreeNode selectNode(TreeNode root, int value){
        for (TreeNode next = root; next != null; ) {
            if (next.value == value){
                return next;
            }
            if (next.value > value){
                next = next.left;
            }
            if (next.value < value){
                next = next.right;
            }
        }
        return null;
    }

    public static TreeNode deleteNode(TreeNode root, TreeNode node){
        TreeNode treeNode = selectNode(root, node.value);
        if (treeNode == null){
            return null;
        }
        if (treeNode.left == null && treeNode.right == null){

        }
        return treeNode;
    }

    public static void insertNode(TreeNode node,TreeNode root){
        TreeNode next = root;
        TreeNode pre = next;
        while (next != null){
            pre = next;
            if (next.value > node.value){
                next = next.left;
            }else {
                next = next.right;
            }
        }
        if (pre.value > node.value){
            pre.setLeft(node);
        }else {
            pre.setRight(node);
        }
    }
    static class TreeNode{
        private int value;
        private TreeNode left;
        private TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public TreeNode getLeft() {
            return left;
        }

        public void setLeft(TreeNode left) {
            this.left = left;
        }

        public TreeNode getRight() {
            return right;
        }

        public void setRight(TreeNode right) {
            this.right = right;
        }
    }

}
