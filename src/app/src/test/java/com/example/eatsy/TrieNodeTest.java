package com.example.eatsy;

import static org.junit.Assert.*;

import com.example.eatsy.searchengine.TrieNode;
import org.junit.Before;
import org.junit.Test;

public class TrieNodeTest {
    private TrieNode trieNode;

    @Before
    public void setUp() {
        trieNode = new TrieNode();
    }

    @Test
    public void testInitialization() {
        // Ensure that the TrieNode is initialized correctly
        assertNotNull(trieNode.children);
        assertEquals(26, trieNode.children.length);
        for (TrieNode child : trieNode.children) {
            assertNull(child);
        }
        assertFalse(trieNode.isEndOfWord);
    }

    @Test
    public void testSetEndOfWord() {
        // Set the end of word flag and check
        trieNode.isEndOfWord = true;
        assertTrue(trieNode.isEndOfWord);
    }

    @Test
    public void testAddChild() {
        // Add a child node and check if it is correctly added
        char ch = 'a';
        int index = ch - 'a';
        trieNode.children[index] = new TrieNode();
        assertNotNull(trieNode.children[index]);
        assertFalse(trieNode.children[index].isEndOfWord);
    }

    @Test
    public void testAddMultipleChildren() {
        // Add multiple children and check if they are correctly added
        char[] chars = {'a', 'b', 'c'};
        for (char ch : chars) {
            int index = ch - 'a';
            trieNode.children[index] = new TrieNode();
        }
        for (char ch : chars) {
            int index = ch - 'a';
            assertNotNull(trieNode.children[index]);
            assertFalse(trieNode.children[index].isEndOfWord);
        }
    }

    @Test
    public void testChildNodeEndOfWord() {
        // Add a child node and set its end of word flag
        char ch = 'a';
        int index = ch - 'a';
        trieNode.children[index] = new TrieNode();
        trieNode.children[index].isEndOfWord = true;
        assertTrue(trieNode.children[index].isEndOfWord);
    }
}
