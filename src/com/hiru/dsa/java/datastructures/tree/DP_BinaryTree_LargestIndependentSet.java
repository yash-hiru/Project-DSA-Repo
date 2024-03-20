package com.hiru.dsa.java.datastructures.tree;

import java.util.HashMap;

/**
 * abstract
 * Q) Given a binary tree find the size of largest independent set (LIS). This means that no two nodes in final set have direct parent-child relationship with each other.
 *
 * Node {
 * int data;
 * Node* lChild, rChild;
 * }
 *
 * /**
 * a1
 * /  \
 * a2   a3
 * / \    \
 * a4  a5  a6
 * / \
 * a8  a9
 *
 *
 * LIS->5 because the set would be a1,a6,a4,a8,a9
 * Input: root node of tree
 * Output: Size of LIS
 **/
public class DP_BinaryTree_LargestIndependentSet {
    //////////////////////////
    static class Node {
        public Node(int d) {
            id = d; // This will be unique.
            data = d;
        }

        int id;
        int data;
        Node l, r;
    }

    /////////////////////////////////////
    public static void main(String[] args) {
        Node root = new Node(1);

        root.l = new Node(2);
        root.r = new Node(3);

        root.l.l = new Node(4);
        root.l.r = new Node(5);
        //root.r.l = new Node(6);
        root.r.r = new Node(7);

        root.r.r.l = new Node(9);
        root.r.r.r = new Node(10);

        HashMap<Integer, Integer> memo = new HashMap<Integer, Integer>();

        System.out.println(getLIS(root, memo));
    }

    ///////////////////////////////////////
    public static int getLIS(Node root, HashMap<Integer, Integer> memo) {
        // BASE -- Empty
        if (root == null) {
            return 0;
        }
        // BASE-- Leaf node
        if (root.l == null && root.r == null) {
            return 1;
        }
        // MEMOIZE: READ (Optimize- O(N))
        if (memo.containsKey(root.id)) {
            return memo.get(root.id);
        }

        // ==== RECRSIVELY Traverse tree
        // Choice1: Include the element in set.. COnsider grandchildren too
        int sol1 = 1; // MISSED this TYPO (I did sol1=0)
        if (root.l != null) {

            int i = getLIS(root.l.l, memo);
            if (i > 0) {
                memo.put(root.l.l.id, i);
            }
            sol1 += i;

            i = getLIS(root.l.r, memo);
            if (i > 0) {
                memo.put(root.l.r.id, i);
            }
            sol1 += i;

        }
        if (root.r != null) {
            int i = getLIS(root.r.l, memo);
            if (i > 0) {
                memo.put(root.r.l.id, i);
            }
            sol1 += i;

            i = getLIS(root.r.r, memo);
            if (i > 0) {
                memo.put(root.r.r.id, i);
            }
            sol1 += i;
        }

        // Choice2: DONT include ..Consider children
        int sol2 = 0;
        sol2 += getLIS(root.l, memo);
        sol2 += getLIS(root.r, memo);

        return (Math.max(sol1, sol2));
    }
}





