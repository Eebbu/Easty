package com.example.eatsy;

public class AVLTreeNode {
    Object data;
    int height;
    AVLTreeNode left;
    AVLTreeNode right;

    public AVLTreeNode(Object data) {
        this.data = data;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}
