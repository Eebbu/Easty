package com.example.eatsy;
import com.example.eatsy.searchengine.AVLTreeNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AVLTreeNodeTest {
    private AVLTreeNode node;

    @Before
    public void setUp() {
        node = new AVLTreeNode(10);
    }

    @Test
    public void testNodeCreation() {
        assertNotNull("Node should not be null", node);
        assertEquals("Node data should be 10", 10, node.data);
        assertEquals("Node height should be 1", 1, node.height);
        assertNull("Left child should be null", node.left);
        assertNull("Right child should be null", node.right);
    }

    @Test
    public void testSetLeftChild() {
        AVLTreeNode leftChild = new AVLTreeNode(5);
        node.left = leftChild;
        assertNotNull("Left child should not be null", node.left);
        assertEquals("Left child data should be 5", 5, node.left.data);
        assertEquals("Left child height should be 1", 1, node.left.height);
    }

    @Test
    public void testSetRightChild() {
        AVLTreeNode rightChild = new AVLTreeNode(15);
        node.right = rightChild;
        assertNotNull("Right child should not be null", node.right);
        assertEquals("Right child data should be 15", 15, node.right.data);
        assertEquals("Right child height should be 1", 1, node.right.height);
    }

    @Test
    public void testUpdateHeight() {
        node.height = 2;
        assertEquals("Node height should be updated to 2", 2, node.height);
    }

    @Test
    public void testSetLeftAndRightChildren() {
        AVLTreeNode leftChild = new AVLTreeNode(5);
        AVLTreeNode rightChild = new AVLTreeNode(15);
        node.left = leftChild;
        node.right = rightChild;

        assertNotNull("Left child should not be null", node.left);
        assertEquals("Left child data should be 5", 5, node.left.data);

        assertNotNull("Right child should not be null", node.right);
        assertEquals("Right child data should be 15", 15, node.right.data);
    }

    @Test
    public void testHeightAfterChildrenSet() {
        AVLTreeNode leftChild = new AVLTreeNode(5);
        AVLTreeNode rightChild = new AVLTreeNode(15);
        node.left = leftChild;
        node.right = rightChild;

        node.height = Math.max(node.left.height, node.right.height) + 1;
        assertEquals("Node height should be 2", 2, node.height);
    }
}
