package com.example.eatsy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;



public class TrieNodeTest {
    private TrieNode node;

    @Before
    public void setUp() {
        node = new TrieNode();
    }

    @Test
    public void testInitialChildrenAreNull() {
        for (TrieNode child : node.children) {
            assertNull("Expected child to be null", child);
        }
    }

    @Test
    public void testInitialIsEndOfWordIsFalse() {
        assertFalse("Expected isEndOfWord to be false", node.isEndOfWord);
    }
}
