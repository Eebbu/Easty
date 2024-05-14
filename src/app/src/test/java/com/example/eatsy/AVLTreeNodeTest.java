package com.example.eatsy.searchengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AVLTreeNode.
 * It ensures that AVLTreeNode instances initialize correctly
 * and that their data, height, and children attributes behave as expected.
 */
public class AVLTreeNodeTest {
    private AVLTreeNode node;

    /**
     * Sets up a new AVLTreeNode for each test.
     */
    @BeforeEach
    void setUp() {
        node = new AVLTreeNode(10);  // Initialize the node with a sample integer data
    }

    /**
     * Tests if the node is correctly initialized with provided data.
     */
    @Test
    void testNodeInitialization() {
        assertEquals(10, node.data, "Node data should be initialized to 10.");
        assertEquals(1, node.height, "Node height should be initialized to 1.");
        assertNull(node.left, "Left child should be null upon initialization.");
        assertNull(node.right, "Right child should be null upon initialization.");
    }

    /**
     * Tests the setting of left and right child nodes.
     */
    @Test
    void testSettingChildren() {
        AVLTreeNode leftChild = new AVLTreeNode(5);
        AVLTreeNode rightChild = new AVLTreeNode(15);

        node.left = leftChild;
        node.right = rightChild;

        assertSame(leftChild, node.left, "Left child should reference the object leftChild.");
        assertSame(rightChild, node.right, "Right child should reference the object rightChild.");
    }

    /**
     * Tests updating the height of the node.
     */
    @Test
    void testHeightUpdate() {
        node.height = 3;
        assertEquals(3, node.height, "Node height should be updated to 3.");
    }

    /**
     * Tests modifications to the node's data.
     */
    @Test
    void testDataModification() {
        node.data = 20;  // Changing the data stored in the node
        assertEquals(20, node.data, "Node data should be updated to 20.");
    }
}
