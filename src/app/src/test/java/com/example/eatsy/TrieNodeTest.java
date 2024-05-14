package com.example.eatsy.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for TrieNode.
 * It ensures that TrieNode instances initialize correctly
 * and that their children array and isEndOfWord flag behave as expected.
 */
public class TrieNodeTest {
    private TrieNode node;

    @BeforeEach
    void setUp() {
        node = new TrieNode();
    }

    @Test
    void testInitialChildrenAreNull() {
        // Ensure all children are initially null
        for (int i = 0; i < node.children.length; i++) {
            assertNull(node.children[i], "Child at index " + i + " should initially be null.");
        }
    }

    @Test
    void testInitialIsEndOfWord() {
        // The isEndOfWord should initially be false
        assertFalse(node.isEndOfWord, "isEndOfWord should initially be false.");
    }

    @Test
    void testSettingChildren() {
        // Test setting a child node
        TrieNode child = new TrieNode();
        node.children[0] = child;
        assertNotNull(node.children[0], "Child at index 0 should not be null after setting.");
        assertSame(child, node.children[0], "Child at index 0 should be the same object as assigned.");
    }

    @Test
    void testSettingIsEndOfWord() {
        // Test setting the isEndOfWord flag
        node.isEndOfWord = true;
        assertTrue(node.isEndOfWord, "isEndOfWord should be true after setting.");
    }
}
