package com.hiru.practice.datastructures.bst;

import com.hiru.practice.datastructures.bst.impl.MyBinarySearchTree;
import com.hiru.practice.datastructures.bst.impl.MyNode;
import com.hiru.practice.util.MyLogger;

/**
 * ========================== BST TIPS ==========================
 * 1. Mode of Communication:
 * ...............1. Return value from the child
 * ...............2. Passed value from parent and older ancestors
 * ...............3. IN/OUT parameter to keep track of node's path and state
 * 2. Traversal (Choose it wisely):
 * ...............1. PreOrder: If you want to do Something "FIRST" before trying out left and right subtree
 * ...............2. Inorder: For sorting and L--R traversal
 * ...............3. Reverse Inorder -- For K largest Element
 * ...............4. PostOrder: If you want to explore LEFT and RIGHT and take decision for ROOT
 * 3. Useful DataStructures:
 * ...............1. ArrayList or ArrayList<ArrayList> (Path tracking with backtracking technique)
 * ...............2. Stack: If you need to process the state in LIFO manner
 * ...............3. Queue: For level order traversal
 * ...............4. Multiple stacks: Zig zag
 * 4. Nested traversals ?
 * ...............Yes, if you want to take decision on PREVISITED subtree based on other subtree's/Parent's return state.
 * ...............e.g. BST_BurnTree
 * ==============================================================
 */
public class BST_BurnTree {

    public static void main(String args[]) {
        MyBinarySearchTree bst = new MyBinarySearchTree();
        bst.insertAll(new int[]{10, 5, 4, 7, 20, 16, 15, 17, 22, 24});
        burn(bst.root, 16);
    }

    /**
     * 1. Preorder traversal
     * 2. Special treatment for found node
     * 3. How to spread to parent
     * 4. How to spread further
     * GFG : https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/amp/
     * My solution is simpler and space+time efficient than GFG
     *
     * @return True(burned) false(did not find hence did not fired tree)
     */
    public static boolean burn(MyNode node, int s) {
        //====== Preorder-- Invalid Case Check ======//
        if (node == null) {
            return false;
        }
        MyLogger.info("DEBUG: " + node.getData()); // VISIT TYPE1 -- Node was visited in find operation (FOR ALL)
        //====== Preorder-- Check and Visit ======//
        if (node.getData() == s) {
            // Found the trigger; Burn it and its both subtrees
            // Part1-- Burn this node and L,R subtrees
            burnDown(node);
            // Let immediate caller know that fire has started
            return true;
        }

        //====== Preorder-- LEFT Recurse ======//
        if (burn(node.getLeft(), s)) {
            // Fire came from immediate left since it returned true
            //Burn self
            MyLogger.info("Burn: " + node.getData());
            // Burn RIGHT subtree and return true(TRUE means FIRE)
            burnDown(node.getRight());
            return true;
        }

        //====== Preorder-- RIGHT Recurse ======//
        if (burn(node.getRight(), s)) {
            // Fire came from immediate right since it returned true
            //Burn self
            MyLogger.info("Burn: " + node.getData());
            // Burn LEFT subtree and return true(TRUE means FIRE)
            burnDown(node.getLeft());
            return true;
        }
        // Trigger was not found anywhere; Just return false ( No need to burn this tree)
        return false;
    }

    /**
     * Another Preorder function for special operation
     *
     * @param node Subtree root to be processed
     */
    private static void burnDown(MyNode node) {
        if (node == null) {
            return;
        }
        MyLogger.info("DEBUG: " + node.getData()); // VISIT TYPE2 -- Node was visited AGAIN for burning (FOR FEW)
        //Preorder
        MyLogger.info("Burn: " + node.getData());
        burnDown(node.getLeft());
        burnDown(node.getRight());
    }
}
