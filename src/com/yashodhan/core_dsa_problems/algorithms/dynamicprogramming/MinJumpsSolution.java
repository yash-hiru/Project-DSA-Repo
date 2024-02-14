package com.yashodhan.core_dsa_problems.algorithms.dynamicprogramming;

/**
 * Given an array arr[] where
 * each element represents the max number of steps that can be made forward from that index.
 * The task is to find the minimum number of jumps to reach the end of the array
 * starting from index 0.
 * <p>
 * Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 * Output: 3 (1-> 3 -> 9 -> 9)
 */

import java.util.Arrays;

/**
 * Problem -->
 * Given an array arr[] where each element represents the max number of steps
 * that can be made forward from that index.
 * The task is to find the minimum number of jumps to reach the end of the array starting from index 0.
 * Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 * Output: 3 (1-> 3 -> 9 -> 9)
 * <p>
 * <p>
 * Learnings -->
 * 1. Choice condition was right but did not think through memoization and tabulation approaches.
 * 2. Memoization was quick to implement on top of plain recursive function
 * 3. Handling integer MAX_VALUE+1 leading to integer overflow. Avoid it
 * 4. Avoid returning MAX_VALUE if it gets overflowed in subsequent recursive calls.
 */
public class MinJumpsSolution {

    /**
     * MAIN Function ==> Driver function
     *
     * @param args
     */
    public static void main(String args[]) {
        MinJumpsSolution underTest = new MinJumpsSolution();
        int arr[] = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};

        // TEST all approaches
        underTest.minJumps(arr);
    }

    /**
     * Driver method for kickoff recursive util
     *
     * @param arr
     */
    private void minJumps(int[] arr) {
        // Approach-- PLAIN RECURSION
        System.out.println("Minimum Jumps for Array (PLAIN RECURSIVE):" + Arrays.toString(arr));
        System.out.println(RECURSIVE_minJumpsUtil(arr, 0));

        // Approach-- MEMOIZATION
        System.out.println("Minimum Jumps for Array (MEMOIZATION):" + Arrays.toString(arr));
        int[] memo = new int[arr.length];
        Arrays.fill(memo, -1);
        System.out.println(MEMOIZATION_minJumpsUtil(arr, 0, memo));

    }

    /**
     * APPROACH-Plain Recursion
     * Move forward with possible outputs. Block at end state
     *
     * @param arr
     * @param pos
     * @return minJumps
     */
    private int RECURSIVE_minJumpsUtil(int[] arr, int pos) {

        // == BASE CASE== Happy (Found some solution)
        if (pos == arr.length - 1) {
            return 0;
        }

        // == RECURSE == Recursive for partial solution
        // Read position to find next steps
        int steps = arr[pos];
        int minJumps = Integer.MAX_VALUE - 1;

        for (int i = pos + 1; i < pos + 1 + steps; i++) {
            // == BASE CASE== Ignore Invalid state instead of returning INT_MAX for it..It could lead to INT overflow
            // due to repeated addition of 1 in recursive calls
            if (i <= arr.length - 1) {
                // Ignore invalid positions
                // increment jump count by ONE for valid jump
                minJumps = Math.min(minJumps, 1 + RECURSIVE_minJumpsUtil(arr, i));
            }

        }
        return minJumps;
    }

    /**
     * APPROACH-MEMOIZATION Solution recursive
     * Move forward with possible outputs. Block at end state
     *
     * @param arr
     * @param pos
     * @param memo
     * @return
     */
    private int MEMOIZATION_minJumpsUtil(int[] arr, int pos, int[] memo) {
        // == BASE CASE== Happy (Found some solution)
        if (pos == arr.length - 1) {
            return 0;
        }
        // == OPTIMIZATION== Use Memo (optimization)
        if (memo[pos] != -1) {
            return memo[pos];
        }
        // == RECURSE == Recursive for partial solution
        // Read position to find next steps
        int steps = arr[pos];
        int minJumps = Integer.MAX_VALUE - 1;

        for (int i = pos + 1; i < pos + 1 + steps; i++) {
            // == BASE CASE== Ignore Invalid state instead of returning INT_MAX for it..It could lead to INT overflow
            // due to repeated addition of 1 in recursive calls
            if (i <= arr.length - 1) {
                // Ignore invalid positions
                // increment jump count by ONE for valid jump
                minJumps = Math.min(minJumps, 1 + MEMOIZATION_minJumpsUtil(arr, i, memo));
            }

        }
        memo[pos] = minJumps;
        return minJumps;
    }

    /**
     * APPROACH-TABULATION
     *
     * @param arr
     * @return minJumps
     * @2024-02-14
     */
    private int TABULATION_minJumpsUtil(int[] arr, int size) {
        // jumpsTable[size-1] will hold the result (min jumpsTable)
        int jumpsTable[] = new int[size];
        // result
        int pos, oldPos;

        // if first element is 0,
        if (size == 0 || arr[0] == 0)
            return Integer.MAX_VALUE;
        // end cannot be reached

        jumpsTable[0] = 0;

        // OUTER FOR LOOP-- For finding Min Jumps from 0 to that 'pos'.
        for (pos = 1; pos < size; pos++) {
            jumpsTable[pos] = Integer.MAX_VALUE;
            // INNER FOR LOOP -- To use previous result from <0> to <pos-1> to determine optimal jumpsTable for pos
            for (oldPos = 0; oldPos < pos; oldPos++) {
                // IF-- Condition1:To check upper bound --Condition2: To Filter out old invalid paths
                if (pos <= (oldPos + arr[oldPos]) && jumpsTable[oldPos] != Integer.MAX_VALUE) {
                    // Choose MIN between: 1. Current solution(jumpsTable[pos]) and (jumpsTable[oldPos] + 1)
                    jumpsTable[pos] = Math.min(jumpsTable[pos], jumpsTable[oldPos] + 1);
                    // TODO-- STILL CONFUSING AS WHY we stopped as soon as if condition gets satisfied.
                    break;
                }
            }
        }
        return jumpsTable[size - 1];
    }

}
