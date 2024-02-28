package com.hiru.practice.algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * CALCULATE EDIT DISTANCE OF TWO STRINGS
 * ...........GFG >>> https://www.geeksforgeeks.org/edit-distance-dp-5/amp/
 * ...........Inputs: sunday saturday
 * ...........Edit distance(recursive): 3, Num Calls: 350
 * ...........Edit distance(memoized): 3, Num Calls: 40
 */
public class ARRAY_CalculateEditDistance {
    static int numCalls = 0; // Keep track of optimization across multiple approaches

    ////////////////////////////////// Driver Method
    public static void main(String[] args) {
        // String s1 = "geeks", s2 = "gesek"; // Output: 1
        String s1 = "sunday", s2 = "saturday"; // Output: 3

        System.out.println("Inputs: " + s1 + " " + s2);
        calculateEditDistance(s1, s2);
    }

    ////////////////////////////////// Core Method
    private static void calculateEditDistance(String s1, String s2) {
        // Recursive way
        System.out.println("Edit distance(recursive): "
                + calculateEditDistanceUtil(s1, s2, s1.length(), s2.length(), 0, 0)
                + ", Num Calls: " +
                numCalls);

        numCalls = 0; // reset num calls

        //Memoization way
        int[][] memo = new int[s1.length() + 1][s2.length() + 1]; // LEARNING: Allocate extra to avoid AIOOB exception
        for (int i = 0; i < s1.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
        System.out.println("Edit distance(memoized): "
                + calculateEditDistanceUtilMemoized(s1, s2, s1.length(), s2.length(), 0, 0, memo)
                + ", Num Calls: " +
                numCalls);
    }


    ////////////////////////////////// Recursive Util
    private static int calculateEditDistanceUtil(String s1,
                                                 String s2,
                                                 int len1,
                                                 int len2,
                                                 int i1,
                                                 int i2) {
        // Base cases+validations >>>>>>>
        // 1. i1 == len1
        if (i1 == len1) {
            return (len2 - 1 - i2);// other remaining string ( Index arithmetic)
        }
        // 2. i2 == len2
        if (i2 == len2) {
            return (len1 - 1 - i1);// other remaining string ( Index arithmetic)
        }
        numCalls += 1; // >>>>>>>>>>>> Following part is costly
        // CH1 >>> There is some strings to be processed
        if (s1.charAt(i1) == s2.charAt(i2)) {
            // Advance both without updating edit distance ( no action)
            return calculateEditDistanceUtil(s1, s2, len1, len2, i1 + 1, i2 + 1);
        }
        // CH2 >>>
        return 1 + Math.min(
                calculateEditDistanceUtil(s1, s2, len1, len2, i1 + 1, i2),
                calculateEditDistanceUtil(s1, s2, len1, len2, i1, i2 + 1)
        );
    }

    /////////////////////////////////////// Memoized
    private static int calculateEditDistanceUtilMemoized(String s1,
                                                         String s2,
                                                         int len1,
                                                         int len2,
                                                         int i1,
                                                         int i2,
                                                         int[][] memo) {
        // Base cases+validations >>>>>>>
        // 1. i1 == len1
        if (i1 == len1) {
            return (len2 - 1 - i2);// other remaining string ( Index arithmetic)
        }
        // 2. i2 == len2
        if (i2 == len2) {
            return (len1 - 1 - i1);// other remaining string ( Index arithmetic)
        }
        // Memoization --Optimization (Cache)
        if (memo[i1][i2] != -1) {
            return memo[i1][i2];
        }
        numCalls += 1; // >>>>>>>>>>>> Following part is costly
        // CH1 >>> There is some strings to be processed
        if (s1.charAt(i1) == s2.charAt(i2)) {
            if (memo[i1][i2] == -1) {
                memo[i1 + 1][i2 + 1] = calculateEditDistanceUtilMemoized(s1, s2, len1, len2, i1 + 1, i2 + 1, memo);
            }
            memo[i1][i2] = memo[i1 + 1][i2 + 1];
        } else {
            // CH2 >>>
            if (memo[i1 + 1][i2] == -1) {
                memo[i1 + 1][i2] = calculateEditDistanceUtilMemoized(s1, s2, len1, len2, i1 + 1, i2, memo);
            }
            if (memo[i1][i2 + 1] == -1) {
                memo[i1][i2 + 1] = calculateEditDistanceUtilMemoized(s1, s2, len1, len2, i1, i2 + 1, memo);
            }
            memo[i1][i2] = 1 + Math.min(memo[i1 + 1][i2], memo[i1][i2 + 1]);
        }
        return memo[i1][i2];
    }
}
