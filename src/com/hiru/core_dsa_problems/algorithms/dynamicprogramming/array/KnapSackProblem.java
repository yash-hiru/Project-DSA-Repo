package com.hiru.core_dsa_problems.algorithms.dynamicprogramming.array;

/**
 * Standard 0/1 knapsack problem
 * Choices -- Put or don't put item and move to next one
 */
public class KnapSackProblem {

    public static void main(String[] args) {

        int W = 4;
        int profit[] = new int[]{1, 2, 3};
        int weight[] = new int[]{4, 5, 1};


        System.out.println("0/1 Knapsack Solution: " + knapsackMaxProfit(profit, weight, W));
    }


    //////////////////////////////////////////////
    static int knapsackMaxProfit(int[] prof, int[] wt, int W) {
        // initial state -- Start from 0th item
        return knapsackMaxProfitUtil(prof, wt, prof.length, W, 0);
    }

    //////////////////////////////////////////////
    static int knapsackMaxProfitUtil(int[] prof, int[] wt, int size, int W, int i) {
        // Base case
        if (i == size) {
            return 0;
        }

        // Base case -- Constraints SKIP current and try next)
        if (wt[i] > W) {
            // Skip this one and try next item
            return knapsackMaxProfitUtil(prof, wt, size, W, i + 1); // LEARNING --- DON'T return 0 BLINDLY.
        }


        // Explore choices
        return Math.max(
                prof[i] + knapsackMaxProfitUtil(prof, wt, size, W - wt[i], i + 1), //Choice1 -- Put item
                knapsackMaxProfitUtil(prof, wt, size, W, i + 1) // Choice2-- Don't put item
        );
    }

    /////////////////////////////////////////////////// TODO -- To be tested
    int knapsackMaxProfitUtilMemoized(int[] prof, int[] wt, int size, int W, int i, int[] memo) {
        // Base case
        if (wt[i] > W) {
            return 0;
        }

        // Base case
        if (i > size) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        // Explore choices
        memo[i] = Math.max(prof[i] + knapsackMaxProfitUtil(prof, wt, size, W - wt[i], i + 1),
                knapsackMaxProfitUtil(prof, wt, size, W, i + 1));

        // Recursive choices
        return memo[i];
    }
}
