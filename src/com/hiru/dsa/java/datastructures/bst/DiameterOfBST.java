package com.hiru.dsa.java.datastructures.bst;

import com.hiru.dsa.java.datastructures.bst.impl.MyNode;

/**
 * PROBLEM: The diameter of a tree is the number of nodes on the longest path between two leaves in the tree.
 * GFG:https://www.geeksforgeeks.org/diameter-of-a-binary-tree-in-on-a-new-method/amp/
 */
public class DiameterOfBST {

    public static void main(String args[]) {
        MyNode tree = new MyNode(10);

        tree.left = new MyNode(7);
        tree.left.right = new MyNode(9);
        tree.left.left = new MyNode(3);

        tree.right = new MyNode(7);
        tree.right.left = new MyNode(9);
        tree.right.right = new MyNode(3);
        tree.right.right.right = new MyNode(33);
        tree.right.right.right.right = new MyNode(333);

        // Step1: Get the left subtree height( max)
        int hLeft = getHeightUtil(tree.left);
        // Step2: Get the Right subtree height( max)
        int hRight = getHeightUtil(tree.right);
        // Step3: Calculate diameter (1 for root+ hLeft + hRight)
        System.out.println(" Diameter of given tree: " + (1 + hLeft + hRight));

    }

    /**
     * Get the height ( aka max height) of given tree
     *
     * @param node (tree root)
     *
     * @return height
     */
    public static int getHeightUtil(MyNode node) {
        if (node == null) {
            return 0;
        }
        // Leaf node
        if (node.left == null && node.right == null) {
            return 1;
        }
        if (node.left == null) {
            return 1 + getHeightUtil(node.right);
        }
        if (node.right == null) {
            return 1 + getHeightUtil(node.left);
        }
        return 1 + Math.max(getHeightUtil(node.left), getHeightUtil(node.right));

    }

}
