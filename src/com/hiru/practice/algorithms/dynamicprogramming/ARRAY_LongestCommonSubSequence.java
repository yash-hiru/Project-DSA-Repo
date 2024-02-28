package com.hiru.practice.algorithms.dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Explored all 3 approaches
 * ==================================
 * 1. Plain recursive
 * Simple brutt force way to explore choices at EACH stage and continue recursive flow
 * until we hit base case.
 * Choose BEST of the subproblem solution for current problem and return current problem solution to parent
 * ==================================
 * 2. Top Down ( Memoization) DP[M][N]
 * == Initialize DP[0][0] = 0 and rest of cells to -1
 * == Base case for end of S1 or S2
 * return 0;
 * == Else ==> Handle 2 cases
 * If matched ==>
 * Recursively get DP[i][j] solution it was not called before
 * Use if for DP[i+1][j+1] = 1+DP[i][j]
 * return DP[i+1][j+1]
 * <p>
 * *              If NOT matched ==>
 * Recursively get DP[i+1][j] and get DP[i][j+1] solutions those were not called before
 * DP[M+1][N+1] = MAX (DP[M+1][N], DP[M][N+1])
 * return DP[M+1][N+1]
 * ==================================
 * 3. Bottom Up (Tabulation) DP[M+1][N+1]
 * ==> Initialize First row and first column
 * ==> 2 for loops for rows and columns each.
 * ==> Handle 2 cases
 * If matched ==> DP[M+1][N+1] = 1+DP[M][N]
 * If NOT matched ==> DP[M+1][N+1] = MAX (DP[M+1][N], DP[M][N+1])
 * ===> Exit after for loops
 * ===> Return DP[M+1][N+1] as length of longest increasing subsequence
 */

public class ARRAY_LongestCommonSubSequence {
    public static void main(String args[]) {
        ARRAY_LongestCommonSubSequence testMe = new ARRAY_LongestCommonSubSequence();
        int[] S1 = new int[]{1, 2, 3, 1, 1, 4, 2, 3};
        int[] S2 = new int[]{3, 2, 1, 4, 1, 1, 4, 2};

        testMe.getLcs(S1, S2);
    }


    public void getLcs(int[] s1, int[] s2) {
        ArrayList<Integer> lcs = new ArrayList<>();

        // ----------------- APPROACH 1----- Plain recursive way
        // Invoke
        int lcsLengthApproach1 = getLcsRecursively(s1,
                s2,
                s1.length - 1,
                s2.length - 1,
                lcs
        );
        // Show result
        System.out.println("LCS Length in DP ===> Plain recursive way (Non DP): " + lcsLengthApproach1);

        // ----------------- APPROACH 2----- DP Top down( memoization)
        int[][] DP = new int[s1.length][s2.length];
        // Initialize
        for (int i = 0; i < s1.length; i++) {
            Arrays.fill(DP[i], -1); // Initialize other cells
        }
        DP[0][0] = 0; // Initialize base case
        // Invoke
        int lcsLengthApproach2 = getLcsTopDownDPMemoization(s1, s2, s1.length - 1, s2.length - 1, DP);
        // Show result
        System.out.println("LCS Length in DP ===> Optimized Recursive/Memoization(Top Down) way: " + lcsLengthApproach2);

        // ----------------- APPROACH 3----- DP Top down( memoization)
        // Invoke
        int lcsLengthApproach3 = getLcsBottomUpDPTabulation(s1, s2);
        // Show result
        System.out.println("LCS Length in DP ===> Tabulation(Bottom Up) way: " + lcsLengthApproach3);
    }

    /**
     * APPROACH1- Recursive way ( No DP)
     */
    public int getLcsRecursively(int[] s1, int[] s2, int i1, int i2, ArrayList<Integer> lcs) {
        // BASE CASE
        if (i1 == 0 || i2 == 0) {
            // No common anymore
            return 0;
        } else {
            if (s1[i1] == s2[i2]) {
                int lcsLength = 1 + getLcsRecursively(s1, s2, i1 - 1, i2 - 1, lcs);
                //  LEARNING-- Handling Common State across overlapping calls .. BE EXTREMELY CAREFUL
//                lcs.add(s1[i1]); // Important to keep track of the actual LCS
                return lcsLength;
            } else {
                return Math.max(getLcsRecursively(s1, s2, i1 - 1, i2, lcs),
                        getLcsRecursively(s1, s2, i1, i2 - 1, lcs));
            }
        }
    }


    /**
     * APPROACH1- DP Top down (Memoization)
     *
     * @return
     */
    public int getLcsTopDownDPMemoization(int[] s1,
                                          int[] s2,
                                          int i1,
                                          int i2,
                                          int[][] DP) {
        // BASE CASE
        if (i1 == 0 || i2 == 0) {
            // No common anymore
            return DP[0][0]; // Which is 0
        } else {
            // Match found-- Increment count and update DP
            if (s1[i1] == s2[i2]) {
                if (DP[i1 - 1][i2 - 1] == -1) {
                    // DP optimization--Set Memo for subproblem if not set
                    DP[i1 - 1][i2 - 1] = getLcsTopDownDPMemoization(s1, s2, i1 - 1, i2 - 1, DP);
                }
                DP[i1][i2] = 1 + DP[i1 - 1][i2 - 1];
            }
            // Match not found-- Explore other 2 combinations and choose Max of both
            else {
                if (DP[i1 - 1][i2] == -1) {
                    DP[i1 - 1][i2] = getLcsTopDownDPMemoization(s1, s2, i1 - 1, i2, DP);
                }
                if (DP[i1][i2 - 1] == -1) {
                    DP[i1][i2 - 1] = getLcsTopDownDPMemoization(s1, s2, i1, i2 - 1, DP);
                }
                DP[i1][i2] = Math.max(DP[i1 - 1][i2], DP[i1][i2 - 1]);
            }
        }
        return DP[i1][i2];
    }

    /**
     * APPROACH3- DP Bottom Up (Tabulation)
     * Non recursive--Iterative way
     *
     * @return
     */
    public int getLcsBottomUpDPTabulation(int[] s1,
                                          int[] s2) {
        // Initialize DP-- Base cases
        int[][] DP = new int[s1.length + 1][s2.length + 1]; // IMPORTANT- Extra row and column for base case
        // Initialize first column (base case)
        for (int i = 0; i < s1.length + 1; i++) {
            DP[i][0] = 0; // Ready to use base case value ( unlike -1 in memoization)
        }
        // Initialize First column
        for (int j = 0; j < s2.length + 1; j++) {
            DP[0][j] = 0; // Ready to use base case value ( unlike -1 in memoization)
        }

        // Solve problem in bottom up  ( Base case to DP[N-1][M-1] )
        // IMPORTANT-- Start from [1] cell since its where we are storing actual DP values for each
        // Subproblems
        for (int i = 0; i < s1.length; i++) {
            for (int j = 0; j < s2.length; j++) {
                if (s1[i] == s2[j]) {
                    DP[i + 1][j + 1] = 1 + DP[i][j]; // Update next state based on earlier subproblems
                } else {
                    // Update next state based on earlier subproblems
                    DP[i + 1][j + 1] = Math.max(DP[i + 1][j], DP[i][j + 1]);
                }
            }
        }
        return DP[s1.length][s2.length];
    }
}
