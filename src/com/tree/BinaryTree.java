package com.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: algorithm
 * @description:
 * @author: 穆野
 * @create: 2021-01-13 22:18
 **/
public class BinaryTree {

    public static void main(String[] args) {
        TreeNode D = new TreeNode('D', null, null);
        TreeNode H = new TreeNode('H', null, null);
        TreeNode K = new TreeNode('K', null, null);
        TreeNode C = new TreeNode('C', D, null);
        TreeNode G = new TreeNode('G', H, K);
        TreeNode B = new TreeNode('B', null, C);
        TreeNode F = new TreeNode('F', G, null);
        TreeNode E = new TreeNode('E', null, F);
        TreeNode A = new TreeNode('A', B, E);

        BinaryTree binaryTree = new BinaryTree();
        System.out.println("前");
        binaryTree.pre(A);
        System.out.println();
        System.out.println("中");
        binaryTree.in(A);
        System.out.println();
        System.out.println("后");
        binaryTree.post(A);
        System.out.println();
        System.out.println("层次");
        binaryTree.level(A);
    }

    public static void pre(TreeNode node) {
        if (node != null) {
            System.out.println(node.value);
            pre(node.left);
            pre(node.right);
        }
    }

    public static void in(TreeNode node) {
        if (node != null) {
            in(node.left);
            System.out.println(node.value);
            in(node.right);
        }
    }

    public static void post(TreeNode node) {
        if (node != null) {
            post(node.left);
            post(node.right);
            System.out.println(node.value);
        }
    }

    //按层次遍历
    public static void level(TreeNode node) {
        if (node != null) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(node);
            while (!queue.isEmpty()) {
                TreeNode curr = queue.poll();

                System.out.println(curr.value);
                if (curr.left != null) {
                    queue.offer(curr.left);
                }
                if (curr.right != null) {
                    queue.offer(curr.right);
                }
            }
        }
    }
}

class TreeNode {
    char value;
    TreeNode left;
    TreeNode right;

    public TreeNode(char value) {
        this.value = value;
    }

    public TreeNode(char value, TreeNode left, TreeNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}