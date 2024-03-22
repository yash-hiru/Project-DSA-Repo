package com.hiru.dsa.java;

import com.hiru.dsa.java.util.MyLogger;

import java.util.List;

public class __Scratchpad__ {

    public static void main(String[] args) {
        System.out.println("Enter Main----------------------");
        // Test here
        System.out.println("Exit Main----------------------");
    }

    //////////////////////////////////////////////////////////////////////////////////////////
    static void numNQueen(int[][] chess, int size, int q) {
        if (q == size) {
            MyLogger.info("found Solution");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (!conflicting(chess, size, q, i)) {
                chess[q][i] = 1;
                numNQueen(chess, size, q + 1);
                chess[q][i] = 0; // Backtrack
            }
        }
    }

    private static boolean conflicting(int[][] chess, int size, int r, int c) {
        //Horizontal conflicts
        for (int k = 0; k < size; k++) {
            if (chess[r][k] == 1) {
                return true;
            }
        }

        //Vertical conflicts
        for (int k = 0; k < size; k++) {
            if (chess[k][c] == 1) {
                return true;
            }
        }

        // Diagonal conflicts-- BR
        for (int k1 = r + 1, k2 = c + 1; k1 < size || k2 < size; k1++, k2++) {
            if (chess[k1][k2] == 1) {
                return true;
            }
        }

        // Diagonal conflicts-- BR
        for (int k1 = r - 1, k2 = c - 1; k1 >= 0 || k2 >= 0; k1--, k2--) {
            if (chess[k1][k2] == 1) {
                return true;
            }
        }

        // Diagonal conflicts-- BR
        for (int k1 = r + 1, k2 = c - 1; k1 < size || k2 >= 0; k1++, k2--) {
            if (chess[k1][k2] == 1) {
                return true;
            }
        }

        // Diagonal conflicts-- BR
        for (int k1 = r - 1, k2 = c + 1; k1 >= 0 || k2 < size; k1--, k2++) {
            if (chess[k1][k2] == 1) {
                return true;
            }
        }
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    /**
     * int coins[] = {25, 10, 5};
     * int V = 10;
     * int[] memo = new int[V + 1];
     * Arrays.fill(memo, -1);
     * System.out.println("Min Coins: " + minCoins(coins, V, memo));
     */
    static int DP_unboudedKnap_minCoins(int[] coins, int change, int[] memo) {
        if (change == 0) {
            return 0; // No coins could be used
        }

        if (memo[change] != -1) {
            return memo[change];
        }

        int sol = Integer.MAX_VALUE;
        for (int i = 0; i < coins.length; i++) {
            if (coins[i] <= change) {
                memo[change - coins[i]] = DP_unboudedKnap_minCoins(coins, change - coins[i], memo);
                if (memo[change - coins[i]] < Integer.MAX_VALUE)
                    sol = Math.min(sol, 1 + memo[change - coins[i]]);
            }
        }
        return sol; // Could return MAX_VALUE for invalid state (Handle it carefully in parent callers to avoid overflow)
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    /**
     * int arr[] = {1, 5, 8, 9, 10, 17, 17, 20};
     * int memo[] = new int[arr.length + 1];
     * Arrays.fill(memo, -1);
     * System.out.println(DP_rodCutMaxProfit(arr, arr.length, memo));
     */
    static int DP_unboundedKnap_rodCutMaxProfit(int[] rates, int size, int[] memo) {
        // Base--invalid and happy conditions
        if (size == 0) {
            return 0;
        }
        // Memoize -- cache hit
        if (memo[size] != -1) {
            return memo[size];
        }

        int sol = Integer.MIN_VALUE;
        // Explore valid choices --
        for (int i = 0; i < size; i++) {
            if (i + 1 <= size) {
                // Memoize -- cache save
                memo[size - i - 1] = DP_unboundedKnap_rodCutMaxProfit(rates, size - i - 1, memo);
                if (memo[size - i - 1] != Integer.MIN_VALUE)
                    sol = Math.max(sol, rates[i] + memo[size - i - 1]);
            }
        }
        return sol; // Could return MIN_VALUE for invalid state (Handle it carefully in parent callers)
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    /**
     * int[][] maze = new int[5][5];
     * maze[0] = new int[]{1, 0, 0, 1, 1};
     * maze[1] = new int[]{1, 1, 1, 1, 1};
     * maze[2] = new int[]{1, 0, 0, 0, 1};
     * maze[3] = new int[]{1, 1, 0, 0, 1};
     * maze[4] = new int[]{1, 0, 1, 1, 1};
     *
     * boolean visited[][] = new boolean[5][5];
     * ratInMaze(maze, 5, 5, 0, 0, visited, "");
     *
     *
     * output ====>
     * ratInMaze solution: DRRRURDDDD
     * ratInMaze solution: DRRRRDDD
     */
    public static void ratInMaze(int[][] maze, int xx, int yy, int x, int y, boolean[][] visited, String path) {
        //  Bounds check
        if (x < 0 || y < 0 || x == xx || y == yy) {
            return;
        }

        // Is final state
        if (x == xx - 1 && y == yy - 1) {
            System.out.println("ratInMaze solution: " + path); // Here we can maintain List too.. clone the string to list
        }

        //Explore 4 possible choices from current valid position
        if (maze[x][y] == 1) {
            visited[x][y] = true;
            if (x + 1 < xx && !visited[x + 1][y]) {
                ratInMaze(maze, xx, yy, x + 1, y, visited, path + "D");
                // Backtrack for next choice
                //path = path.substring(0, path.length() - 1);
            }

            if (x - 1 >= 0 && !visited[x - 1][y]) {
                ratInMaze(maze, xx, yy, x - 1, y, visited, path + "U");
                // Backtrack for next choice
                //path = path.substring(0, path.length() - 1);
            }

            if (y + 1 < yy && !visited[x][y + 1]) {
                ratInMaze(maze, xx, yy, x, y + 1, visited, path + "R");
                // Backtrack for next choice
                //path = path.substring(0, path.length() - 1);
            }

            if (y - 1 >= 0 && !visited[x][y - 1]) {
                ratInMaze(maze, xx, yy, x, y - 1, visited, path + "L");
                // Backtrack for next choice
                //path = path.substring(0, path.length() - 1);
            }
            visited[x][y] = false; // Backtrack for current state
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    /**
     * int V = 4;
     * List<Integer>[] adj = new List[V];
     * adj[0] = Arrays.asList(1, 2, 3);
     * adj[1] = Arrays.asList(0, 2);
     * adj[2] = Arrays.asList(0, 1, 3);
     * adj[3] = Arrays.asList(0, 2);
     * System.out.println(minColor(V, adj));
     * return;
     */
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

    //////////////////////////////////////////////////////////////////////////////////////////

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
