package com.hiru.core_dsa_problems.algorithms.dynamicprogramming;

import java.util.Arrays;

/**
 * Given a rod of length n inches
 * and an array of prices that includes prices of all pieces of size smaller than n.
 * <p>
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 * <p>
 * HINT ==> Similar to coin change problem: Here denominations are single element in array ( rate)
 * Learnings ==> Did not consider repeated items( like coin change), silly mistakes , Did not consider the permutations optimization
 * e.g. (1111112, 2111111)
 * <p>
 * Findings ==> Geeks solution is different than this..It is same as knapsack ? with 2 choices at every stage.
 * <p>
 * https://www.geeksforgeeks.org/cutting-a-rod-dp-13/
 */
public class ARRAY_KnapSack_CuttingRodToGetMaxValue {

    private int numberOfCalls;

    ////////////////////////////////////////////
    public static void main(String[] args) {
        int rates[] = {1, 5, 8, 9, 10, 17, 17, 20};
        System.out.println("Max Profit: " + new ARRAY_KnapSack_CuttingRodToGetMaxValue().getMaxProfitCut(rates));
        //Expected output : 22
    }

    //////////////////////////////////////////////
    public int getMaxProfitCut(int[] rates) {
        int[] memo = new int[rates.length];
        Arrays.fill(memo, -1);
        int maxProfit = getMaxProfitCutUtil(rates, rates.length, memo);
        System.out.println("Number of calls: " + numberOfCalls);
        return maxProfit;
    }

    ///////////////////////////////////////////////
    public int getMaxProfitCutUtil(final int[] rates, int capacity, int[] memo) {
        // FINDING==> GFG number of calls 452 compared to our 256 (Even without memoization optimization)
        // CONCLUSION ==>
        //          Our approach ==> FOR loop for all items in each stage(recursive call) is WAY OPTIMAL :-)
        //          GFG approach ==> Typical knapsack approach of 2 choices,consider/don't consider item.
        //                              And reduce the capacity and decrement item index with base condition on index.
        numberOfCalls += 1;
        // Base case: We have No more rod to sell ( this is the only base case, no other constraints)
        if (capacity == 0) {
            return 0;
        }

        // LEARNINGS ==> Did not think about simple memoization to avoid repeated nested calls for
        // e.g. <1112, 2111, 1121, 23, 41, 5>  for let's say capacity 5 so on an so forth
        if (memo[capacity - 1] != -1) {
            return memo[capacity - 1];
        }

        int maxProfit = Integer.MIN_VALUE;

        // CRITICAL LEARNINGS ==> Repeated items is allowed ( THEN DON'T consider it as knapsack problem)
        // Consider it as coin change problem.

        // LEARNINGS ==> int arithmetic (capacity-rodPart+1) is different than (capacity-(rodPart+1))

        // LEARNINGS ==> Capacity being a reducing parameter and NO other constraints, we can simply have that as mutable/reducing
        // parameter and hence could have base case ONLY on top of that.
        for (int rodPart = 0; rodPart < rates.length; rodPart++) {
            if ((rodPart + 1) <= capacity) {
                maxProfit = Math.max(maxProfit,
                        rates[rodPart] + getMaxProfitCutUtil(rates,
                                capacity - (rodPart + 1),
                                memo));
            }
        }
        memo[capacity - 1] = maxProfit;
        return memo[capacity - 1];
    }

}
