package org.example;

import java.util.ArrayList;
import java.util.List;

class Node {
    int value;
    Character character;
    List<Node> children; // List of child nodes


    // Constructor
    public Node(int value) {
        this.value = value;
        this.character = null; // Default character for non-leaf nodes
        this.children = new ArrayList<>();
    }

    public Node(int value, char character) {
        this.value = value;
        this.character = character;
        this.children = new ArrayList<>();
    }

    public void addChild(Node child) {
        children.add(child);
    }
}