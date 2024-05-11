package com.example.eatsy;

import java.util.ArrayList;
import java.util.List;


public class Test {

    static class Node {
        private String value;
        private List<Node> children;

        public Node(String value) {
            this.value = value;
            this.children = new ArrayList<>();
        }

        public void addChild(Node child) {
            children.add(child);
        }

        public List<Node> getChildren() {
            return children;
        }

        public String getValue() {
            return value;
        }

        public  List<String> toArray() {
            List<String> res = new ArrayList<>();
            toStringHelper(res, this, 0);
            return res;
        }

        private void toStringHelper(List<String> list, Node node, int level) {
            list.add(node.getValue());
            for (Node child : node.getChildren()) {
                toStringHelper(list, child, level + 1);
            }
        }
    }

    static class IllegalProductionException extends Exception {
        public IllegalProductionException(String message) {
            super(message);
        }
    }

    static class Token {
        private String value;

        public Token(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    static class Parser {
        private List<Token> tokens;
        private int index;

        public Parser(List<Token> tokens) {
            this.tokens = tokens;
            this.index = 0;
        }

        private Token consume() {
            if (index < tokens.size()) {
                return tokens.get(index++);
            }
            return null;
        }

        private Token peek() {
            if (index < tokens.size()) {
                return tokens.get(index);
            }
            return null;
        }

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

