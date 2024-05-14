package com.example.eatsy.searchengine;

import java.util.ArrayList;
import java.util.List;

/**
 * A collection of classes to demonstrate parsing expressions from tokenized input,
 * constructing a syntax tree, and handling parsing exceptions.
 */
public class Test {
    /**
     * Represents a node in a syntax tree.
     * Each node holds a value and may have child nodes.
     */
    static class Node {
        private String value;
        private List<Node> children;
        /**
         * Constructor for creating a Node with a given value.
         * @param value The value of the node.
         */
        public Node(String value) {
            this.value = value;
            this.children = new ArrayList<>();
        }
        /**
         * Adds a child node to this node.
         * @param child The child node to be added.
         */
        public void addChild(Node child) {
            children.add(child);
        }
        /**
         * Returns the list of child nodes.
         * @return A list of child nodes.
         */
        public List<Node> getChildren() {
            return children;
        }
        /**
         * Returns the value of the node.
         * @return The node's value.
         */
        public String getValue() {
            return value;
        }
        /**
         * Converts the subtree rooted at this node into a list of strings.
         * Each string represents a node in the tree.
         * @return A list of strings representing the nodes.
         */
        public  List<String> toArray() {
            List<String> res = new ArrayList<>();
            toStringHelper(res, this, 0);
            return res;
        }
        /**
         * Converts the subtree rooted at this node into a list of strings.
         * Each string represents a node in the tree.
         * @return A list of strings representing the nodes.
         */
        private void toStringHelper(List<String> list, Node node, int level) {
            list.add(node.getValue());
            for (Node child : node.getChildren()) {
                toStringHelper(list, child, level + 1);
            }
        }
    }
    /**
     * Custom exception class for handling parsing errors.
     */
    static class IllegalProductionException extends Exception {
        public IllegalProductionException(String message) {
            super(message);
        }
    }
    /**
     * Represents a token, a basic unit of parsing.
     */
    static class Token {
        private String value;

        public Token(String value) {

            this.value = value;
        }
        /**
         * Returns the value of the token.
         * @return The token's value.
         */
        public String getValue() {

            return value;
        }
    }
    /**
     * Parser class to convert a list of tokens into a syntax tree.
     */
    static class Parser {
        private List<Token> tokens;
        private int index;

        public Parser(List<Token> tokens) {
            this.tokens = tokens;
            this.index = 0;
        }
        /**
         * Consumes the current token and moves the index to the next token.
         * @return The current token before the index is incremented.
         */
        private Token consume() {
            if (index < tokens.size()) {
                return tokens.get(index++);
            }
            return null;
        }
        /**
         * Peeks at the current token without consuming it.
         * @return The current token or null if no tokens are left.
         */
        private Token peek() {
            if (index < tokens.size()) {
                return tokens.get(index);
            }
            return null;
        }
        /**
         * Parses an expression starting from the current token.
         * @return A Node representing the root of the parsed expression.
         * @throws IllegalProductionException If the token sequence does not match the expected pattern.
         */
        public Node parseExp() throws IllegalProductionException {
            Node termNode = parseCoefficient();
            Token token = peek();
            Node tempNode = termNode;
            while (token != null && (token.getValue().equals(" "))) {
                consume();
                Node nextTermNode = parseCoefficient();
                tempNode.addChild(nextTermNode);
                tempNode = tempNode.getChildren().get(0);
                token = peek();
            }
            return termNode;
        }

        /**
         * Parses an expression starting from the current token.
         * @return A Node representing the root of the parsed expression.
         * @throws IllegalProductionException If the token sequence does not match the expected pattern.
         */
        public Node parseCoefficient() throws IllegalProductionException {
            Token token = consume();
            if (token == null) {
                throw new IllegalProductionException("Unexpected end of input.");
            }
            if (isValidToken(token.getValue())) {
                return new Node(token.getValue());
            } else  {
                throw new IllegalProductionException("Invalid token: " + token.getValue());
            }
        }
        /**
         * Checks if a given string is a valid token.
         * @param value The string to check.
         * @return true if the string is a valid token, false otherwise.
         */
        private boolean isValidToken(String value) {
            return value.matches("[a-zA-Z]+");
        }
    }

    public static void main() {
        // 输入标记序列
        List<Token> tokens = new ArrayList<>();
        tokens.add(new Token("apple"));
//        tokens.add(new Token(" "));
//        tokens.add(new Token("title"));
//        tokens.add(new Token("+"));
//        tokens.add(new Token("31231"));

        // 创建 Parser 实例并解析
        Parser parser = new Parser(tokens);
        try {
            Node rootNode = parser.parseExp();
            System.out.println("Parsing successful.");
            System.out.println("Parse tree:\n" + rootNode.toString());
            System.out.println(1);
        } catch (IllegalProductionException e) {
            System.out.println("Parsing failed: " + e.getMessage());
        }
    }


}

