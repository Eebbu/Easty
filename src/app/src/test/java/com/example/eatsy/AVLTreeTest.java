package com.example.eatsy.searchengine;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * Test class for AVLTree.
 * It tests the insertion balance, tree structure integrity, and traversal correctness.
 */
public class AVLTreeTest {
    private AVLTree avlTree;

    /**
     * Setup method executed before each test method.
     * It initializes a new instance of AVLTree.
     */
    @BeforeEach
    void setUp() {
        avlTree = new AVLTree();
    }

    /**
     * Tests if the traversal of an empty tree returns an empty list.
     */
    @Test
    void testEmptyTree() {
        // Verify that traversing an empty tree should return an empty list
        assertTrue(avlTree.traverseTree().isEmpty(), "Traversing an empty tree should return an empty list.");
    }

    /**
     * Tests insertions into the AVL tree and checks if the tree remains balanced.
     */
    @Test
    void testInsertionsAndBalance() {
        // Insert elements and check the balance of the tree
        avlTree.buildTree(new Integer[]{20, 15, 25, 10, 18, 17});
        List<AVLTreeNode> nodeList = avlTree.traverseTree();
        assertNotNull(nodeList, "Node list should not be null after insertions.");
        assertFalse(nodeList.isEmpty(), "Node list should not be empty after insertions.");
        assertEquals(6, nodeList.size(), "Node list should contain exactly 6 elements.");

        // Verify tree balance
        AVLTreeNode root = avlTree.root;
        assertEquals(15, root.data, "Root should be 15 after rotations to balance the tree.");
        assertNotNull(root.left, "Root should have a left child.");
        assertNotNull(root.right, "Root should have a right child.");
        assertEquals(10, root.left.data, "Left child of root should be 10.");
        assertEquals(20, root.right.data, "Right child of root should be 20.");
    }

    /**
     * Tests the structure of the tree after specific insertions.
     */
    @Test
    void testTreeStructure() {
        // Test the structure of the tree
        avlTree.buildTree(new Integer[]{30, 20, 40, 10, 25, 35, 50});
        AVLTreeNode root = avlTree.root;
        assertEquals(30, root.data, "Root of the tree should be 30.");
        assertEquals(20, root.left.data, "Left child of root should be 20.");
        assertEquals(40, root.right.data, "Right child of root should be 40.");
    }
}
