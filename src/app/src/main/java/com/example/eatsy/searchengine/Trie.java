package com.example.eatsy.searchengine;

/*
    A Trie class is defined to implement a Trie
    (dictionary tree) data structure,
    specifically used to efficiently manage and query string data.
    Author: Lin Xi(u7777752)
 */
public class Trie {
    TrieNode root;
    /*
        When initializing the Trie,
        create a root node root.
        The root node contains no characters but serves as the starting point for all words.
     */
    public Trie() {
        root = new TrieNode();
    }
    /*
        Receives a word word and inserts it into a Trie.
        Iterate through each character of the word
        and calculate the index corresponding to the character
        by subtracting 'a' from the character
        (assuming that only lowercase letters are processed,
        that is, 'a' to 'z'),
        so that each letter corresponds to an index from 0 to 25.
        If the current character's child node does not exist,
        create a new TrieNode and place it at the correct location.
        Move to the child nodes of the current character
        until all characters of the word have been processed.
        Mark the node of the last character of the word as the end of a word.
     */
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            int index = ch - 'a';
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }
    /*
        Receives a prefix and searches the Trie for this prefix.
        This method is used to check if there is any word starting with this prefix.
        Similar to the insert method,
        each character of the prefix is traversed
        and the index is calculated to access the child nodes.
        If the child node does not exist at any point,
        it means that there is no word starting with this prefix in the Trie,
        and the method returns false.
        If all the characters of the prefix
        can find the corresponding path in the Trie,
        even if there is no end of word later,
        true will be returned, indicating that the prefix is valid.
     */
    public boolean searchPrefix(String prefix) {
        TrieNode current = root;
        for (char ch : prefix.toCharArray()) {
            int index = ch - 'a';
            if(index==136){
                System.out.println(1);
            }
            if (index<0){
                return false;
            }
            if (current.children[index] == null) {
                return false;
            }
            current = current.children[index];
        }
        return current.isEndOfWord;
    }
}

