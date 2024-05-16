package com.example.eatsy.searchengine;

import java.util.ArrayList;
import java.util.List;
/**
 * The AVLTree class implements the structure and functionality of an AVL tree.
 * An AVL tree is a self-balancing binary search tree, which ensures that the tree remains balanced
 * after insertions and deletions, keeping the operational complexity of these operations logarithmic.
 * Author: Lin Xi(u7777752)
 */
public class AVLTree {
    AVLTreeNode root;
    /**
     * Constructor initializes an empty AVL tree.
     */
    public AVLTree() {

        this.root = null;
    }
    /**
     * Builds the AVL tree from an array of objects.
     * @param objects Array of objects to be inserted into the tree.
     */
    public void buildTree(Object[] objects) {
        for (Object obj : objects) {
            AVLTreeNode newNode = new AVLTreeNode(obj);
            root = insert(root, newNode);
        }
    }
    /**
     * Recursively inserts a new node into the AVL tree while maintaining the tree's balance.
     * @param node The current node.
     * @param newNode The new node to be inserted.
     * @return The root node of the rebalanced tree.
     */
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
        // Perform rotations to restore balance if necessary
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
    /**
     * Compares two objects.
     * @param obj1 The first object.
     * @param obj2 The second object.
     * @return Negative if obj1 is less than obj2, positive if obj1 is greater than obj2, 0 if equal.
     */
    private int compare(Object obj1, Object obj2) {
        // Custom comparison logic based on your requirements
        // Return a negative value if obj1 is less than obj2,
        // a positive value if obj1 is greater than obj2,
        // or 0 if obj1 is equal to obj2.
        // You need to implement this according to the type of objects you are storing.
        return 0;
    }
    /**
     * Retrieves the height of a given node.
     * @param node The node.
     * @return The height of the node.
     */
    private int getHeight(AVLTreeNode node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }
    /**
     * Calculates the balance factor of a given node.
     * @param node The node.
     * @return The balance factor of the node.
     */
    public int getBalance(AVLTreeNode node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }
    /**
     * Performs a right rotation on the given node y to restore tree balance.
     * @param y The node to rotate.
     * @return The new root after the rotation.
     */
    public AVLTreeNode rightRotate(AVLTreeNode y) {
        AVLTreeNode x = y.left;
        AVLTreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }
    /**
     * Performs a left rotation on the given node x to restore tree balance.
     * @param x The node to rotate.
     * @return The new root after the rotation.
     */
    public AVLTreeNode leftRotate(AVLTreeNode x) {
        AVLTreeNode y = x.right;
        AVLTreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;

        return y;
    }


    /**
     * Traverses the entire tree and returns a list of all nodes.
     * @return A list of nodes.
     */
    public List<AVLTreeNode> traverseTree() {
        List<AVLTreeNode> nodeList = new ArrayList<>();
        traverseTree(root, nodeList);
        return nodeList;
    }
    /**
     * Recursively traverses the tree using in-order traversal and collects nodes.
     * @param node The current node.
     * @param nodeList The list to store the nodes.
     */
    private void traverseTree(AVLTreeNode node, List<AVLTreeNode> nodeList) {
        if (node == null) {
            return;
        }

        traverseTree(node.left, nodeList);
        nodeList.add(node);
        traverseTree(node.right, nodeList);
    }
}
