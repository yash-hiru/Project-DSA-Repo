package com.hiru.core_dsa_problems.algorithms.dynamicprogramming;

/**
 * Given a set of non-negative integers and a value sum, the task is to check
 * if there is a subset of the given set whose sum is equal to the given sum.
 * <p>
 * Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 9
 * Output: True
 * Explanation: There is a subset (4, 5) with sum 9.
 * <p>
 * Input: set[] = {3, 34, 4, 12, 5, 2}, sum = 30
 * Output: False
 * Explanation: There is no subset that add up to 30.
 */
public class SubsetSumKnapsackVariation {
    public static void main(String args[]) {
        // Test data
        int set[] = new int[]{3, 34, 4, 12, 5, 2};
        int sum = 9;
        System.out.print(checkSubset(set, set.length, sum));
    }


    public static boolean checkSubset(int set[], int size, int sum) {
        return false;
    }

    public static boolean checkSubsetUtil(int set[], int size, int sum /** Extra Output parameters*/) {
        return false;
    }
}
