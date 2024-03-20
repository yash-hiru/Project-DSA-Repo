package com.hiru.dsa.java.datastructures.tree;

import com.hiru.dsa.java.datastructures.tree.impl.MyBinarySearchTree;
import com.hiru.dsa.java.datastructures.tree.impl.MyNode;

import java.util.ArrayList;

public class BACKTRACKING_FindPaths_And_LCA {
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
            return false; // Backtracking Level
        }
        if (root.getData() == i) {
            return true; // Desired level
        } else {
            // EXPLORE-- Make choice
            path.add(root.getData());

            // Reurse both the options (LEFT and RIGHT)
            if (findPath(root.getLeft(), i, path) ||
                    findPath(root.getRight(), i, path)) {
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
