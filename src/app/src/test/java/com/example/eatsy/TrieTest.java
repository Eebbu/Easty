package com.example.eatsy;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import search.Trie;

public class TrieTest {
    private Trie trie;

    @Before
    public void setUp() {
        trie = new Trie();
    }

    @Test
    public void testInsertAndSearchValidPrefix() {
        trie.insert("hello");
        assertTrue("Expected true because 'hello' is inserted and 'hel' is a prefix", trie.searchPrefix("hel"));
    }

    @Test
    public void testSearchInvalidPrefix() {
        trie.insert("hello");
        assertFalse("Expected false because 'world' is not a prefix of any inserted word", trie.searchPrefix("world"));
    }

    @Test
    public void testSearchExactWordAsPrefix() {
        trie.insert("hello");
        assertTrue("Expected true because 'hello' is inserted and is an exact match", trie.searchPrefix("hello"));
    }

    @Test
    public void testSearchBeyondWordLength() {
        trie.insert("hello");
        assertFalse("Expected false because there is no word 'helloooo' in the trie", trie.searchPrefix("helloooo"));
    }

    @Test
    public void testInsertAndSearchMultipleWords() {
        trie.insert("hello");
        trie.insert("helicopter");
        trie.insert("help");
        assertTrue("Expected true because 'hel' is a common prefix", trie.searchPrefix("hel"));
        assertTrue("Expected true because 'hello' is an exact word", trie.searchPrefix("hello"));
        assertTrue("Expected true because 'help' is an exact word", trie.searchPrefix("help"));
        assertFalse("Expected false because 'heaven' is not inserted", trie.searchPrefix("heaven"));
    }
}
