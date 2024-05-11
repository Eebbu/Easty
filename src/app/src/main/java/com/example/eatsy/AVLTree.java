package com.example.eatsy;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    AVLTreeNode root;

    public AVLTree() {
        this.root = null;
    }

    public void buildTree(Object[] objects) {
        for (Object obj : objects) {
            AVLTreeNode newNode = new AVLTreeNode(obj);
            root = insert(root, newNode);
        }
    }

    private AVLTreeNode insert(AVLTreeNode node, AVLTreeNode newNode) {
        if (node == null) {
            return newNode;
        }

        if (compare(newNode.data, node.data) < 0) {
            node.left = insert(node.left, newNode);
        } else {
            node.right = insert(node.right, newNode);
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        int balance = getBalance(node);

        if (balance > 1 && compare(newNode.data, node.left.data) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && compare(newNode.data, node.right.data) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && compare(newNode.data, node.left.data) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && compare(newNode.data, node.right.data) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private int compare(Object obj1, Object obj2) {
        // Custom comparison logic based on your requirements
        // Return a negative value if obj1 is less than obj2,
        // a positive value if obj1 is greater than obj2,
        // or 0 if obj1 is equal to obj2.
        // You need to implement this according to the type of objects you are storing.
        return 0;
    }

    private int getHeight(AVLTreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalance(AVLTreeNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    private AVLTreeNode rightRotate(AVLTreeNode y) {
        AVLTreeNode x = y.left;
        AVLTreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    private AVLTreeNode leftRotate(AVLTreeNode x) {
        AVLTreeNode y = x.right;
        AVLTreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }



    public List<AVLTreeNode> traverseTree() {
        List<AVLTreeNode> nodeList = new ArrayList<>();
        traverseTree(root, nodeList);
        return nodeList;
    }

    private void traverseTree(AVLTreeNode node, List<AVLTreeNode> nodeList) {
        if (node == null) {
            return;
        }

        traverseTree(node.left, nodeList);
        nodeList.add(node);
        traverseTree(node.right, nodeList);
    }
}
