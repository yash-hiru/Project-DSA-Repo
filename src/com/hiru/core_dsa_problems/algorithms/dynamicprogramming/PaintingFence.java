package com.hiru.core_dsa_problems.algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * GFG ==> https://www.geeksforgeeks.org/painting-fence-algorithm/
 * Problem Statement:
 * ........Given a fence with n posts and k colors,
 * ........find out the number of ways of painting the fence
 * ........such that at most 2 adjacent posts have the same color.
 * ........Since the answer can be large return it modulo 10^9 + 7.
 *
 * Deep Dive:
 * ........Inputs: int[n] posts, int[k] colors
 * ........Output: Number of ways coloring with constraints
 * ........Constraints: At most 2 adjacent posts have same color
 */
public class PaintingFence {
    ArrayList solutions = new ArrayList<int[]>();

    //////////////////////////////////////
    public static void main(String args[]) {
        PaintingFence underTest = new PaintingFence();

        // Input data
        int[] posts = new int[3];
        Arrays.fill(posts, -1);
        int[] colors = new int[]{1, 2};

        // Run algorithm - APPROACH -- Backtracking
        underTest.CORE_solveWithBacktracking(posts, colors, 0);
    }

    ///////////////////////////////////// CORE Method
    public void CORE_solveWithBacktracking(final int[] posts, final int[] colors, int iPost) {
        // BASE CASE-- Happy
        if (iPost == posts.length) {
            System.out.print("\nSolution--" + Arrays.toString(posts));
            solutions.add(posts.clone());
            return;
        }

        // BACKTRACKING ==> MAKE A CHOICE (color) for current STATE ( post)-- COLOR
        // ...and ...Backtrack for other choices
        for (int color : colors) {
            if (isSafe(posts, iPost, color)) {
                posts[iPost] = color; // 1. Make choice
                CORE_solveWithBacktracking(posts, colors, iPost + 1); // 2. Recursively solve next state
                posts[iPost] = -1; // 3. Backtrack
            }
        }
    }

    ///////////////////////////////////////
    private boolean isSafe(final int[] posts, int iP, int color) {
        // Unsafe== Left 2 posts having same color
        if (iP - 1 >= 0 && posts[iP - 1] == color && iP - 2 >= 0 && posts[iP - 2] == color) {
            return false;
        }
        // Unsafe== Right 2 posts having same color
        if (iP + 1 < posts.length && posts[iP + 1] == color && iP + 2 < posts.length && posts[iP + 2] == color) {
            return false;
        }

        // Unsafe== Left 1 and right 1
        if (iP - 1 >= 0 && posts[iP - 1] == color && iP + 1 < posts.length && posts[iP + 1] == color) {
            return false;
        }
        // Safe to use color for iP fence
        return true;
    }
}
