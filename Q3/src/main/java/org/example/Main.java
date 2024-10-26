package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Tree tree = new Tree(); // Create the tree
        initialize(tree); //initialize the tree
        if(!tree.validateLeafCharacters()){
            System.err.println("Leaf Nodes Must Contain Character.");
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer to search in the tree: ");
        int inputValue = scanner.nextInt();
        Character result = tree.search(inputValue);
        if (result != null) {
            System.out.println("Found character: " + result);
        } else {
            System.out.printf("Integer %d is not found in any leaf node.", inputValue);
        }

        scanner.close();
    }


    private static void initialize(Tree tree){
        tree.setRoot(1,'R');
        Node root = tree.getRoot();
        Node child1 = new Node(4,'G');
        Node child2 = new Node(2,'F');
        Node child3 = new Node(8,'Q');
        root.addChild(child1);
        root.addChild(child2);
        root.addChild(child3);
        child1.addChild(new Node(6,'H'));
        child1.addChild(new Node(10,'E'));
        Node child4 = new Node(5,'Z');
        child2.addChild(child4);
        child2.addChild(new Node(7,'P'));
        child2.addChild(new Node(11,'L'));
        child4.addChild(new Node(3,'X'));
        Node child5 = new Node(9,'u');
        child3.addChild(child5);
        child5.addChild(new Node(12,'T'));
        child5.addChild(new Node(13,'D'));
    }
}