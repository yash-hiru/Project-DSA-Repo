package com.hiru.dsa.java;

import java.util.Arrays;
import java.util.List;

public class __Scratchpad__ {

    public static void main(String[] args) {
        int V = 4;
        List<Integer>[] adj = new List[V];
        adj[0] = Arrays.asList(1, 2, 3);
        adj[1] = Arrays.asList(0, 2);
        adj[2] = Arrays.asList(0, 1, 3);
        adj[3] = Arrays.asList(0, 2);
        System.out.println(minColor(V, adj));
        return;
    }


    static int minColor(int V, List<Integer>[] adj) {
        int[] colors = new int[V];

        for (int v = 0; v < V; v++) {
            colors[v] = adj[v].size();
        }

        int min = Integer.MIN_VALUE;
        for (int c : colors) {
            min = Math.max(min, c);
        }
        return 1 + min;

    }

    ///////////////////////////////////////////////////////////////

    /**
     * int coins[] = {9, 6, 5};
     * int V = 11;
     * int[] memo = new int[V + 1];
     * Arrays.fill(memo, -1);
     * System.out.println(DP_getMinCoins(coins, V, memo));
     */
    static int DP_getMinCoins(int[] coins, int change, int[] memo) {
        if (change == 0) {
            return 0;
        }
        if (change < 0) {
            return Integer.MAX_VALUE;
        }
        if (memo[change] != -1)
            return memo[change];

        int sol = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= change) {
                memo[change - coins[i]] = DP_getMinCoins(coins, change - coins[i], memo);
                if (memo[change - coins[i]] < Integer.MAX_VALUE)
                    sol = Math.min(sol, 1 + memo[change - coins[i]]);
            }
        }
        return memo[change] = sol;
    }
}
