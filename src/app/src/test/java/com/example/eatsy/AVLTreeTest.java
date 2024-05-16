package com.example.eatsy;

import com.example.eatsy.searchengine.AVLTree;
import com.example.eatsy.searchengine.AVLTreeNode;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class AVLTreeTest {
    private AVLTree avlTree;

    @Before
    public void setUp() {
        avlTree = new AVLTree();
    }

    @Test
    public void testBuildTree() {
        Object[] objects = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10, 8, 9};
        avlTree.buildTree(objects);
        List<AVLTreeNode> nodeList = avlTree.traverseTree();

        // Check the size of the tree
        assertEquals(objects.length, nodeList.size());

        // Check if the tree is balanced
        for (AVLTreeNode node : nodeList) {
            int balance = avlTree.getBalance(node);
            assertTrue(balance >= -1 && balance <= 1);
        }
    }

    @Test
    public void testInsert() {
        avlTree.buildTree(new Object[]{10, 20, 30, 40, 50, 25});
        List<AVLTreeNode> nodeList = avlTree.traverseTree();

        // Check the size of the tree
        assertEquals(6, nodeList.size());

        // Check if the tree is balanced
        for (AVLTreeNode node : nodeList) {
            int balance = avlTree.getBalance(node);
            assertTrue(balance >= -1 && balance <= 1);
        }
    }

    @Test
    public void testRightRotate() {
        AVLTreeNode root = new AVLTreeNode(30);
        root.left = new AVLTreeNode(20);
        root.left.left = new AVLTreeNode(10);

        AVLTreeNode newRoot = avlTree.rightRotate(root);

        assertEquals(20, newRoot.data);
        assertEquals(10, newRoot.left.data);
        assertEquals(30, newRoot.right.data);
    }

    @Test
    public void testLeftRotate() {
        AVLTreeNode root = new AVLTreeNode(10);
        root.right = new AVLTreeNode(20);
        root.right.right = new AVLTreeNode(30);

        AVLTreeNode newRoot = avlTree.leftRotate(root);

        assertEquals(20, newRoot.data);
        assertEquals(10, newRoot.left.data);
        assertEquals(30, newRoot.right.data);
    }

    @Test
    public void testTraverseTree() {
        Object[] objects = {10, 20, 30, 40, 50, 25};
        avlTree.buildTree(objects);
        List<AVLTreeNode> nodeList = avlTree.traverseTree();

        // Check the in-order traversal of the tree
        assertEquals(6, nodeList.size());
        assertEquals(10, nodeList.get(0).data);
        assertEquals(20, nodeList.get(1).data);
        assertEquals(25, nodeList.get(2).data);
        assertEquals(30, nodeList.get(3).data);
        assertEquals(40, nodeList.get(4).data);
        assertEquals(50, nodeList.get(5).data);
    }
}

