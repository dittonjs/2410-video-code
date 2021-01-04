package com.usu;

public class BinaryTree {
    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;
    }

    private TreeNode root;

    public BinaryTree(int initialValue) {
        root = new TreeNode();
        root.value = initialValue;
    }

    public void insert(int value) {
        insert(root, value);
    }

    private void insert(TreeNode node, int value) {
        if (node == null) return;
        if (node.value == value) return;
        if (value > node.value) {
            if (node.right == null) {
                node.right = new TreeNode();
                node.right.value = value;
            } else {
                insert(node.right, value);
            }
        }
        if (value < node.value) {
            if (node.left == null) {
                node.left = new TreeNode();
                node.left.value = value;
            } else {
                insert(node.left, value);
            }
        }
    };

    public boolean find(int value) {
        return find(root, value);
    }

    private boolean find(TreeNode node, int value) {
        if (node == null) return false;
        if (node.value == value) return true;
        if (value > node.value) return find(node.right, value);
        return find(node.left, value);
    }

    public void print() {
        print(root, 1);
    }

    private void print(TreeNode node, int depth) {
        if (node == null) return;
        int numSpaces = depth * 4;

        print(node.right, depth + 1);
        System.out.printf("%" + numSpaces + "d\n", node.value);
        print(node.left, depth + 1);
    }
}
