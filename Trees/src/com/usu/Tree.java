package com.usu;

public class Tree {
    private TreeNode root;

    private class TreeNode {
        TreeNode left;
        TreeNode right;
        int startX;
        int startY;
        int endX;
        int endY;
    }

    public void generate(int maxDepth) {
        root = new TreeNode();
        root.startX = 1000 / 2;
        root.startY = 1500;
        root.endX = 1000 / 2;
        root.endY = root.startY - 100;
        generate(root, 1, maxDepth, root.endX, root.endY);
    }

    private void generate(TreeNode node, int currentDepth, int maxDepth, int startX, int startY) {
        if (currentDepth >= maxDepth) return;
        node.left = new TreeNode();
        node.left.startX = startX;
        node.left.startY = startY;

        // pick a random angle between 135 and 90
        node.left.endX = node.left.startX + (int)(Math.cos(Math.toRadians(30)) * 100);
        node.left.endY = node.left.startY - (int)(Math.sin(Math.toRadians(30)) * 100);

        // do the same thing for right but with 90 45
        node.right = new TreeNode();
//        node.right.personName = "Person Right " + currentDepth;

        generate(node.left, currentDepth + 1, maxDepth, node.left.endX, node.left.endY);
        generate(node.right, currentDepth + 1, maxDepth, node.right.endX, node.right.endY);
    }

    public void print() {
        print(root, 1);
    }

    private void print(TreeNode node, int depth) {
        if (node == null) return;
        int numSpaces = (depth * 4) + 10;

        print(node.right, depth + 1);
        System.out.printf("%" + numSpaces + "s\n", node.personName);
        print(node.left, depth + 1);
    }

    public void draw(Canvas canvas, Paint paint) {
        draw(canvas, paint, root);
    }

    private void draw(Canvas canvas, Paint paint, TreeNode node) {
        if(node == null) return;
        // use canvas to draw your node using node.startX ect

        draw(canvas, paint, node.right);
        draw(canvas, paint, node.left);
    }
}
