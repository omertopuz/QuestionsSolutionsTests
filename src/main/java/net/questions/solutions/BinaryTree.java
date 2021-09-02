package net.questions.solutions;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new TreeNode(13,new TreeNode(6,new TreeNode(5,new TreeNode(2),null),new TreeNode(9))
        ,new TreeNode(22,new TreeNode(20,new TreeNode(17),null),new TreeNode(30)));
        List<Integer> a = bt.inorderTraversal(bt.root);
        a.size();
//        bt.inOrderTraversal(bt.root);
    }

    private static class TreeNode{
        int data;
        TreeNode left;
        TreeNode right;
        TreeNode(int d){
            data = d;
        }
        TreeNode(int d,TreeNode l,TreeNode r){
            data = d;
            right=r;
            left=l;
        }
    }

    TreeNode root;

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root,result);
        return result;
    }

    private void inorderTraversal(TreeNode node,List<Integer> list) {
        if (node!= null) {
            inorderTraversal(node.left,list);
            list.add(node.data);
            inorderTraversal(node.right,list);
        }
    }

    void inOrderTraversal(TreeNode node) {
        if (node!= null) {
            inOrderTraversal(node.left);
            visit(node);
            inOrderTraversal(node.right);
        }
    }

    int visit(TreeNode node){
//        System.out.println(node.data);
        return node.data;
    }
}
