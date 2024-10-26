package org.example;

public class Tree {
    private Node root;
   
    public void setRoot(int value){
        root = new Node(value);
    }
    public void setRoot(int value,Character character){
        root = new Node(value,character);
    }
    public Node getRoot(){return this.root;}
    public Character search(int value) {
        return searchRecursive(root, value);
    }
    private Character searchRecursive(Node node, int value) {
        // Base case: if node is null, return null
        if (node == null) {
            return null;
        }

        // Check if it's a leaf node
        if (node.children.isEmpty() && node.value == value) {
           return node.character; // Return the character if value matches and it is leaf node
        }


        // Search in the children
        for (Node child : node.children) {
            Character result = searchRecursive(child, value);
            if (result != null) {
                return result; // Found in child
            }
        }

        return null; // Not found
    }

    // Validation method to check all leaf nodes for valid characters
    public boolean validateLeafCharacters() {
        return validateLeafCharactersRecursive(root);
    }

    // Helper method for recursive validation
    private boolean validateLeafCharactersRecursive(Node node) {
        // Base case: if node is null, return true
        if (node == null) {
            return true;
        }

        // If it is a leaf node, check the character
        if (node.children.isEmpty()) {
            return node.character != null; // Must be non-null for leaf nodes
        }

        // Check in the children
        for (Node child : node.children) {
            if (!validateLeafCharactersRecursive(child)) {
                return false; // Return false if any leaf is invalid
            }
        }

        return true; // All leaf nodes are valid
    }
}
