package com.example.drawing;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Tree {
    TreeNode root = new TreeNode();
    int length = 100;
    private class TreeNode {
        int startX;
        int startY;
        int endX;
        int endY;
        TreeNode left;
        TreeNode right;
    }

    public void generate(int depth, int startX, int startY) {
        root = new TreeNode();
        generate(root, startX, startY, 0, 10);
    }

    private void generate(TreeNode node, int startX, int startY, int angle, int depth) {
        if (depth == 0) return;
        int endX = (int)(startX + Math.sin(Math.toRadians(angle)) * length);
        int endY = (int)(startY - Math.cos(Math.toRadians(angle)) * length);
        node.startX = startX;
        node.startY = startY;
        node.endX = endX;
        node.endY = endY;
        node.left = new TreeNode();
        node.right = new TreeNode();
        generate(node.left, endX, endY, -80, depth - 1);
        generate(node.right, endX, endY, 80, depth - 1);
    }

    public void draw(Canvas canvas, Paint paint) {
        draw(canvas, paint, root);
    }

    private void draw(Canvas canvas, Paint paint, TreeNode node) {
        if(node == null) return;
        paint.setStrokeWidth(10);
        canvas.drawLine(node.startX, node.startY, node.endX, node.endY, paint);
        paint.reset();
        draw(canvas, paint, node.left);
        draw(canvas, paint, node.right);
    }
}
