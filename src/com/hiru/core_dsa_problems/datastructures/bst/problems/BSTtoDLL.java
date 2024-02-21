package com.hiru.core_dsa_problems.datastructures.bst.problems;

// Java program to convert a given Binary Tree to Doubly Linked List

/* Structure for tree and Linked List */
class Node {
    int data;
    Node left, right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class BinaryTree {
    // root    --> Root of Binary Tree
    Node root;

    // head --> Pointer to head node of created doubly linked list
    Node head;

    // A simple recursive function to convert a given
    // Binary tree to Doubly Linked List
    void BSTToDLL(Node root) {
        // Base cases
        if (root == null)
            return;

        // Recursively convert right subtree
        BSTToDLL(root.right);

        // insert root into DLL
        root.right = head;

        // Change left pointer of previous head
        if (head != null)
            head.left = root;

        // Change head of Doubly linked list
        head = root;

        // Recursively convert left subtree
        BSTToDLL(root.left);

    }

    // Utility function for printing double linked list.
    void printList(Node head) {
        System.out.println("Extracted Double Linked List is : ");
        while (head != null) {
            System.out.print(head.data + " ");
            head = head.right;
        }
    }

    // Driver program to test the above functions
    public static void main(String[] args) {
        /* Constructing below tree
               5
             /   \
            3     6
           / \     \
          1   4     8
         / \       / \
        0   2     7   9  */

        BinaryTree tree = new BinaryTree();
        tree.root = new Node(5);
        tree.root.left = new Node(3);
        tree.root.right = new Node(6);
        tree.root.left.right = new Node(4);
        tree.root.left.left = new Node(1);
        tree.root.right.right = new Node(8);
        tree.root.left.left.right = new Node(2);
        tree.root.left.left.left = new Node(0);
        tree.root.right.right.left = new Node(7);
        tree.root.right.right.right = new Node(9);

        tree.BSTToDLL(tree.root);
        tree.printList(tree.head);
    }
}
