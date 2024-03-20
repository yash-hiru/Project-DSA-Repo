package com.hiru.dsa.java.datastructures.tree;

import com.hiru.dsa.java.datastructures.tree.impl.MyNode;

/**
 * Find if this is SYMMETRIC ( Mirror) Tree
 * GFG: https://www.geeksforgeeks.org/symmetric-tree-tree-which-is-mirror-image-of-itself/amp/
 */
public class NAIVE_BT_MirrorTree {

    public static void main(String[] args) {
        MyNode tree = new MyNode(10);

        tree.left = new MyNode(7);
        tree.left.right = new MyNode(9);
        tree.left.left = new MyNode(3);

        tree.right = new MyNode(7);
        tree.right.left = new MyNode(9);
        tree.right.right = new MyNode(3);

        System.out.println("IS MIRROR: " + isMirror(tree.left, tree.right));

    }

    /**
     * Recursive function with initial stage as Level 1 (left and right)
     */
    public static boolean isMirror(MyNode t1, MyNode t2) {
        // BASE == NULL TREE is always mirror (Bottom up return)
        if (t1 == null && t2 == null) {
            return true;
        }
        // BASE == Non Balanced tree could NOT be mirrored
        if ((t1 == null && t2 != null) || (t1 != null && t2 == null)) {
            return false;
        }
        // Recurse and check ( should meet the both the conditions)
        if (t1.data == t2.data) {
            // Mirror condition (t1.left == t2.right && t.right == t2.left)
            return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
        }
        // Other cases
        return false;
    }
}
