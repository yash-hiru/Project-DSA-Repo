package com.hiru.core_dsa_problems.algorithms.dynamicprogramming;

import java.util.ArrayList;

public class MinimumCoins {
    static ArrayList<ArrayList<Integer>> solutions = new ArrayList<>();

    public static void main(String args[]) {
        System.out.println(getMinCoins(new int[]{9, 6, 5, 2, 1, 7}, 7));
    }

    public static int getMinCoins(final int[] coins,
                                  final int V) {
        ArrayList<Integer> selectedCoins = new ArrayList<>();
        int minValue = getMinCoinsRecurseDP(coins, V, selectedCoins);
        System.out.println(minValue);
        System.out.println(solutions);
        return minValue;
    }

    public static int getMinCoinsRecurseDP(final int[] coins,
                                           final int Value,
                                           ArrayList<Integer> selectedCoins) {
        // Base case (Value constraint) -- You exhausted value limit
        if (Value == 0) {
            // Valid perfect combination
            System.out.println("Found Some Solution(NEED NOT TO BE OPTIMAL)" + selectedCoins);
            // IMPORTANT -- Cloning is important since list elements gets added by reference
            // This would end up adding all empty lists to global list since we backtrack for each choice in each solution.
            ArrayList<Integer> selectedCoinsCloned = (ArrayList<Integer>) selectedCoins.clone();
            solutions.add(selectedCoinsCloned);
            return 0;
        } else if (Value < 0) {
            // Invalid combination
            return Integer.MAX_VALUE;
        }

        // DP explore choices recursively
        int minValue = Integer.MAX_VALUE;
        for (int i : coins) {
            selectedCoins.add(i);
            int currMinCoins = getMinCoinsRecurseDP(coins, Value - i, selectedCoins);
            selectedCoins.remove(selectedCoins.size() - 1); // FOLLOW PARTIAL Backtracking

            if (currMinCoins < minValue) {
                minValue = currMinCoins;
            }
        }
        return 1 + minValue;
    }
}
