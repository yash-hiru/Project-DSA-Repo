package com.hiru.dsa.java.datastructures.tree;

import com.hiru.dsa.java.datastructures.tree.impl.MyBinarySearchTree;
import com.hiru.dsa.java.datastructures.tree.impl.MyNode;
import com.hiru.dsa.java.util.MyLogger;

/**
 * PROBLEM: Burn the tree from target/Trigger Node
 * Speciality ==> multiple traversal.. Act on the child return. Do special trversal for BURN
 * GFG : https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/amp/
 *
 * SIMILAR PROBLEM: Find the kth distance nodes from target node
 * GFG (https://www.geeksforgeeks.org/print-nodes-distance-k-given-node-binary-tree/amp/)
 * We need to do int arithematic with return 0 for target and handle k ( pass) to determine the depth
 * in ALL possible ancestors (tree root too) subtrees
 */
public class NAIVE_BT_BurnTree {

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
