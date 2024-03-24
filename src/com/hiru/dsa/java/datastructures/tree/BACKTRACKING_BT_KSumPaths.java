package com.hiru.dsa.java.datastructures.tree;

import java.util.ArrayList;

public class BACKTRACKING_BT_KSumPaths {
    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int d) {
            data = d;
        }
    }

    static int count = 0;

    public static void main(String args[]) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(1);
        root.left.right = new Node(2);
        root.right = new Node(-1);
        root.right.left = new Node(3);
        root.right.left.left = new Node(2);
        root.right.left.right = new Node(5);
        printKSumPaths(root, new ArrayList<>(), 3);
        System.out.println(count);

    }

    /**
     * Testing  ==============>
     * Node root = new Node(1);
     * root.left = new Node(2);
     * root.left.left = new Node(1);
     * root.left.right = new Node(2);
     * root.right = new Node(-1);
     * root.right.left = new Node(3);
     * root.right.left.left = new Node(2);
     * root.right.left.right = new Node(5);
     * printKSumPaths(root, new ArrayList<>(), 3);
     * System.out.println(count);
     *
     * Call Traces at each node ==========>
     * 1-->[1, 2, 1]
     * 2-->[1, 2, 2]
     * 2-->[1, 2]
     * 2-->[1, -1, 3, 2]
     * 5-->[1, -1, 3, 5]
     * 3-->[1, -1, 3]
     * -1-->[1, -1]
     * 1-->[1] ROOT
     */
    static void printKSumPaths(Node root, ArrayList<Integer> pathFromRoot, int k) {
        // Step1 ------------- NODE ==> Base case
        if (root == null) {
            return;
        }

        // Step2------------- Make choice(add to path and recurse)
        pathFromRoot.add(root.data);

        // Step3------------- RECURSE-Preorder----- LEFT
        printKSumPaths(root.left, pathFromRoot, k);

        // Step4------------- RECURSE-Preorder-----RIGHT
        printKSumPaths(root.right, pathFromRoot, k);

        // Step5------------- Process path from root to current node
        int sum = 0;
        for (int i = pathFromRoot.size() - 1; i >= 0; i--) {
            sum += pathFromRoot.get(i);

            if (k == sum) {
                count += 1;
            }
        }
        // Step6------------- Backtrack
        pathFromRoot.remove(pathFromRoot.size() - 1);
    }
}
