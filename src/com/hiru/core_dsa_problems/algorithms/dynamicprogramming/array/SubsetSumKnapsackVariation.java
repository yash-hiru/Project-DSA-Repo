package com.hiru.core_dsa_problems.algorithms.dynamicprogramming.array;

/**
 * SUBSET SUM PROBLEM--DP (Knapsack version)
 * ............GFG: https://www.geeksforgeeks.org/subset-sum-problem-dp-25/amp/
 * ............Given a set of non-negative integers and a value sum, the task is to check
 * ............If there is a subset of the given set whose sum is equal to the given sum. (Decision problem)
 *
 * APPROACH >>>>>
 * ............For the recursive approach, there will be TWO cases/CHOICES.
 * ............1. Consider the ‘last’ element to be a part of the subset. Now the new required sum = required sum – value of ‘last’ element.
 * ............2. Don’t include the ‘last’ element in the subset. Then the new required sum = old required sum.
 * ............In both cases, (** IMPORTANT ** )the number of available elements decreases by 1.
 */
public class SubsetSumKnapsackVariation {
    public static void main(String args[]) {
        // Test data
        int set[] = new int[]{3, 34, 4, 12, 5, 2};
        int sum = 28; //False
        //int sum = 26; //True
        System.out.print(checkSubset(set, set.length, sum));
    }


    public static boolean checkSubset(int set[], int size, int sum) {
        return checkSubsetUtil(set, size, sum, 0);
    }

    public static boolean checkSubsetUtil(final int set[], final int size, int sum, int i) {
        // Approach:
        // Choices: Consider element (if not considered before) dont consider element
        // Base Case: 1. No element 2. Element > remaining_sum
        //BASE CASE: No Further element to process
        if (i == size) {
            // HAPPY CASE
            if (sum == 0) {
                return true;
            }
            // SAD CASE
            else {
                return false;
            }
        }

        //BASE CASE:  Try another element
        if (set[i] > sum) {
            checkSubsetUtil(set, size, sum, i + 1);
        }


        //CHOICE1- Choose element
        //CHOICE2- Don't Choose element

        return (checkSubsetUtil(set, size, sum - set[i], i + 1) ||
                checkSubsetUtil(set, size, sum, i + 1)); // either of subtree should return true

    }
}
