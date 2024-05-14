package com.example.eatsy.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Trie data structure.
 * Author： Lin Xi(u7777752)
 */
public class TrieTest {
    private Trie trie;

    @BeforeEach
    void setUp() {
        trie = new Trie();
    }

    @Test
    void testInsertAndSearchWord() {
        // Test insertion and searching for a complete word
        String word = "apple";
        trie.insert(word);
        // Directly searching for a word should return true if it was inserted
        assertTrue(trie.searchPrefix("apple"), "The word 'apple' should be found.");
    }

    @Test
    void testSearchNonExistentWord() {
        // Try to find a word that has not been inserted
        assertFalse(trie.searchPrefix("banana"), "The word 'banana' should not be found.");
    }

    @Test
    void testPrefix() {
        // Test searching for prefixes
        trie.insert("apple");
        trie.insert("app");
        assertTrue(trie.searchPrefix("app"), "The prefix 'app' should be valid.");
        assertFalse(trie.searchPrefix("apz"), "The prefix 'apz' should not be valid.");
    }

    @Test
    void testWordIsAlsoAPrefix() {
        // Test case where a whole word is also a prefix to another word
        trie.insert("apple");
        trie.insert("app");
        assertTrue(trie.searchPrefix("app"), "The prefix 'app' should be valid and is a complete word.");
        assertTrue(trie.searchPrefix("apple"), "The word 'apple' should be found.");
    }

    @Test
    void testEmptyString() {
        // Testing edge cases with empty string
        trie.insert("");
        assertTrue(trie.searchPrefix(""), "Empty string should always be a valid prefix.");
    }

    @Test
    void testPrefixAndCompleteWord() {
        // Insert multiple words and test both as complete words and prefixes
        String[] words = {"test", "tester", "testing", "testimony", "tested"};
        for (String word : words) {
            trie.insert(word);
        }

        assertTrue(trie.searchPrefix("test"), "The prefix 'test' should be valid.");
        assertTrue(trie.searchPrefix("tester"), "'tester' should be found as a word.");
        assertFalse(trie.searchPrefix("testify"), "'testify' should not be found.");
    }
}
