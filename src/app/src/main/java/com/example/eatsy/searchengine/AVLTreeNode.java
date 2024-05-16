package com.example.eatsy.searchengine;
/**
 * The AVLTreeNode class represents a node in an AVL tree.
 * Each node contains data and has a height attribute to help maintain the tree's balance.
 * The node also has references to its left and right children, which are essential for the structure of the AVL tree.
 * Author: Lin Xi(u7777752)
 */
public class AVLTreeNode {
    public Object data;
    public int height;
    public AVLTreeNode left;
    public AVLTreeNode right;

    public AVLTreeNode(Object data) {
        this.data = data;
        this.height = 1;
        this.left = null;
        this.right = null;
    }
}
