package com.example.eatsy.searchengine;
/*
    To support the implementation of trie, we define this class.
    The Trie data structure can build a vocabulary character by character,
    each node represents a letter, and the links between nodes constitute words.
    Author: Lin Xi(u7777752)
 */
public class TrieNode {
    public TrieNode[] children;
    public boolean isEndOfWord;

    public TrieNode() {
        children = new TrieNode[26]; // Assuming only lowercase English letters
        isEndOfWord = false;
    }
}