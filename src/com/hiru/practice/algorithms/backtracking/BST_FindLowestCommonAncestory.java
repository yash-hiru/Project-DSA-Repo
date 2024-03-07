package com.hiru.practice.algorithms.backtracking;

import com.hiru.practice.datastructures.bst.impl.MyBinarySearchTree;
import com.hiru.practice.datastructures.bst.impl.MyNode;

import java.util.ArrayList;

public class BST_FindLowestCommonAncestory {
    public static void main(String args[]) {
        MyBinarySearchTree bst = new MyBinarySearchTree();
        bst.insert(10);
        bst.insert(5);
        bst.insert(20);
        bst.insert(8);
        bst.insert(15);

        System.out.println(lcs(bst.root, 8, 15));

    }


    /**
     * Get Both Node paths and return false if path not found
     * Post process both the paths to find  the LCA ( first mismatching node)
     *
     * @param root
     * @param a
     * @param b
     *
     * @return lcs.data
     */
    static int lcs(MyNode root, int a, int b) {
        ArrayList<Integer> path1 = new ArrayList<>();
        ArrayList<Integer> path2 = new ArrayList<>();
        Boolean b1 = findPath(root, a, path1);
        Boolean b2 = findPath(root, b, path2);
        // Process
        if (b1 == true && b2 == true) {
            // 2. Find Common Ancenstory
            return commmonAnc(path1, path2);
        } else {
            // Did not find any ancestor
            return -1;
        }
    }

    /**
     * Find Single element path
     *
     * @param root
     * @param i
     * @param path
     *
     * @return
     */
    static boolean findPath(MyNode root, int i, ArrayList<Integer> path) {
        if (root == null) {
            return false;
        }
        if (root.getData() == i) {
            return true;
        } else {
            // EXPLORE-- Make choice
            path.add(root.getData());
            if (true == findPath(root.getLeft(), i, path)) {
                return true;
            }
            if (true == findPath(root.getRight(), i, path)) {
                return true;
            }
            // EXPLORE-- backtrack
            path.remove(path.size() - 1);
        }
        return false;

    }


    static int commmonAnc(ArrayList<Integer> path1, ArrayList<Integer> path2) {
        for (int i = 0; i < path1.size() || i < path2.size(); i++) {
            if (path1.get(i) != path2.get(i)) {
                return path1.get(i - 1);
            }
        }
        return -1;
    }

}
