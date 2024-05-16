package com.example.eatsy;

import com.example.eatsy.searchengine.Trie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {
    private Trie trie;

    @Before
    public void setUp() {
        trie = new Trie();
    }

    @Test
    public void testInsertSingleWord() {
        trie.insert("apple");
        assertTrue(trie.searchPrefix("apple"));
    }

    @Test
    public void testInsertMultipleWords() {
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apricot");

        assertTrue(trie.searchPrefix("apple"));
        assertTrue(trie.searchPrefix("app"));
        assertTrue(trie.searchPrefix("apricot"));
        assertFalse(trie.searchPrefix("banana"));
    }

    @Test
    public void testSearchPrefix() {
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");

        assertTrue(trie.searchPrefix("app"));
        assertFalse(trie.searchPrefix("appl"));
    }

    @Test
    public void testSearchNonExistentPrefix() {
        trie.insert("apple");

        assertFalse(trie.searchPrefix("banana"));
        assertFalse(trie.searchPrefix("appl"));
    }

    @Test
    public void testInsertAndSearchEmptyString() {
        trie.insert("");
        assertTrue(trie.searchPrefix(""));
    }

    @Test
    public void testInsertAndSearchSingleCharacter() {
        trie.insert("a");
        assertTrue(trie.searchPrefix("a"));
        assertFalse(trie.searchPrefix("b"));
    }

    @Test
    public void testInsertAndSearchOverlappingWords() {
        trie.insert("apple");
        trie.insert("app");

        assertTrue(trie.searchPrefix("apple"));
        assertTrue(trie.searchPrefix("app"));
        assertFalse(trie.searchPrefix("appl"));
    }

    @Test
    public void testInsertAndSearchWithCommonPrefix() {
        trie.insert("apple");
        trie.insert("applet");
        trie.insert("application");

        assertTrue(trie.searchPrefix("apple"));
        assertTrue(trie.searchPrefix("applet"));
        assertTrue(trie.searchPrefix("application"));
        assertFalse(trie.searchPrefix("appli"));
    }
}
