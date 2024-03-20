package com.hiru.dsa.java.datastructures.tree;

import com.hiru.dsa.java.datastructures.tree.impl.MyBinarySearchTree;
import com.hiru.dsa.java.datastructures.tree.impl.MyNode;

/**
 * GFG: https://www.geeksforgeeks.org/sorted-array-to-balanced-bst/amp/
 */
public class NAIVE_BST_SortedArrayToBST {
    public static void main(String args[]) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        MyNode node = getTree(arr, 0, arr.length - 1);
        System.out.println("Root: " + node.data);
        MyBinarySearchTree bst = new MyBinarySearchTree();
        bst.root = node;
        bst.traverseInorder(bst.root);
    }

    /**
     * Fill tree and return root
     */
    public static MyNode getTree(int[] arr, int i, int j) {
        MyNode node;
        // validations
        if (i < 0 && j == arr.length) {
            return null;
        }
        // Base case-- 1 element
        else if (j - i == 0) {
            return new MyNode(arr[i]);
        }
        // Base case-- 2 elements
        else if (j - i == 1) {
            node = new MyNode(arr[j]); // OR node = new MyNode(arr[i])
            node.left = new MyNode(arr[i]);  // OR node.right = new MyNode(arr[j])
            return node;
        }
        // Recursive -- for 3 or more elements in subarray
        else {
            // mid
            int imid = i + (j - i) / 2; // Learning--MID is always relative to i in recursive calls
            node = new MyNode(arr[imid]);
            // Left
            node.left = getTree(arr, i, imid - 1);
            // Right
            node.right = getTree(arr, imid + 1, j);
        }
        return node;
    }
}
