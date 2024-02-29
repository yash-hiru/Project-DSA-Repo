package com.hiru;

import java.util.Arrays;

import static java.lang.Integer.MAX_VALUE;

public class DeleteMe {
    public static void main(String args[]) {
        int[] memo = new int[12];
        Arrays.fill(memo, -1);
        System.out.println(minJumps(new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}, 0, memo));
        return;
    }

    static int minJumps(int[] arr, int i, int[] memo) {
        // Base cases
        if (i >= arr.length) {
            return Integer.MAX_VALUE; //Invalid State
        }
        if (i == arr.length - 1) {
            // No jumps are possible further
            return 0; // Valid State
        }
        // Not end state--Solve further
        // Optimization -- MEMO read
        if (memo[i] != -1) {
            return memo[i];
        }
        // Recursive subproblems
        int range = arr[i]; // Options
        int jumps = Integer.MAX_VALUE; // Solution
        // Explore options
        for (int j = i + 1; j <= i + range; j++) {
            // Valid next position/index (Make a choice)
            if (j <= arr.length - 1) {
                memo[j] = minJumps(arr, j, memo); // MEMOIZE- Write
                // Jump and count jump. Check if sub-problem did NOT lead to INVALID state
                // Ignore invalid state returns
                if (memo[j] < MAX_VALUE) {
                    jumps = Math.min(jumps, 1 + memo[j]); // 1 Valid jump for possible next state
                }
            }
        }
        memo[i] = jumps;
        return memo[i];
    }
}
