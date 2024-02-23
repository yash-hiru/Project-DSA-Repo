package com.hiru.core_dsa_problems.algorithms.dynamicprogramming.array;

/**
 * LONGEST ZIG ZAG SEQUENCE
 * GFG : https://www.geeksforgeeks.org/longest-zig-zag-subsequence/amp/
 */

public class LongestZigZagSequence {

    /////////////////////////////////////////////
    public static void main(String[] args) {
        int[] arr = new int[]{10, 22, 9, 33, 49, 50, 31, 60, 70};

        //Initial stage --
        // 1. Be careful in passing flag value
        // 2. Add 1 for first initial state sequence between i and i+1
        System.out.println("Max ZigZag Solution: " + (1 + CORE_getLongestZigZagSequence(arr, 0, arr[0] < arr[1])));
    }

    /////////////////////////////////////////////
    public static int CORE_getLongestZigZagSequence(int[] arr, int i, boolean flag) {
        // Base conditions >>>>>>>>
        if (arr.length == 0 || arr.length == 1 || i == arr.length - 1) {
            return 0;
        }
        if (flag == false && i == arr.length - 2 && (arr[i] > arr[i + 1])) {
            return 2; // 2 elements in zig zag sequence.
        }
        if (flag == true && i == arr.length - 2 && (arr[i] < arr[i + 1])) {
            return 2; // 2 elements in zig zag sequence.
        }

        // RECURSIVE APPROACH >>>>>>> 3 or more elements
        // CHOICE1 & 2: SOLUTION AT Index i --- Zig or Zag
        if ((flag == true && arr[i] < arr[i + 1]) ||
                (flag == false && arr[i] > arr[i + 1])) {
            // Enforce false in next
            return 1 + CORE_getLongestZigZagSequence(arr, i + 1, !flag);
        }
        // CHOICE3: Try searching solution at other indexes than i without** adding 1
        else {
            // Dont flip flag, Dont increase counter
            return CORE_getLongestZigZagSequence(arr, i + 1, flag);
        }
    }

    /////////////////////////////////////////////
    public static int DISCARD_CORE_getLongestZigZagString(int[] arr, int i, boolean flag) {
        // Base conditions >>>>>>>>
        if (arr.length == 0 || arr.length == 1 || i == arr.length - 1) {
            return 0;
        }
        if (flag == false && i == arr.length - 2 && (arr[i] > arr[i + 1])) {
            return 2; // 2 elements in zig zag sequence.
        }
        if (flag == true && i == arr.length - 2 && (arr[i] < arr[i + 1])) {
            return 2; // 2 elements in zig zag sequence.
        }

        // RECURSIVE APPROACH >>>>>>> 3 or more elements
        int solution = Integer.MIN_VALUE;
        // CHOICE1: SOLUTION AT Index i --- ZigZag--Type1 (Zig--Increasing)
        if (flag == true && arr[i] < arr[i + 1]) {
            // Enforce false in next
            solution = Math.max(solution, 1 + DISCARD_CORE_getLongestZigZagString(arr, i + 1, !flag));

        }
        // CHOICE2:  SOLUTION AT Index i --- ZigZag--Type1 (Zag--Decreasing)
        else if (flag == false && arr[i] > arr[i + 1]) {
            solution = Math.max(solution, 1 + DISCARD_CORE_getLongestZigZagString(arr, i + 1, !flag));
        } else {
            // CHOICE3: Try searching solution at other indexes than i without** adding 1
            solution = Math.max(solution, DISCARD_CORE_getLongestZigZagString(arr, i + 1, !flag));
        }
        return solution;
    }
}

/**********************************************************************
 * Research >>>>>>>>>>>>>>>>>
 * Zigzag can be alternate number sequence
 * Could exist anywhere in array
 *
 * Greedy Not possible-- We can not follow same strategy and coverge to optimal solution
 * End to end.
 *
 * DP--Overlapping subproblems..Yes do exist
 * Optimal SubStructure ? ==> Yes
 * 1 + subproblem() if current element satishfy condition
 *
 * Tricky part --> determining direction change ( Boolean)
 *
 *
 * Choice --> include next element if constraints match
 * Constraint --> Alternate direction(inc/dec) last element
 *
 * State >>>> Initial (0), Final ( len-1)
 * Base condition -->  len-1
 */
