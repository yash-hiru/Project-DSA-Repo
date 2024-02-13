package com.yashodhan.core_dsa_problems.algorithms.dynamicprogramming;

/**
 * Given an array arr[] where
 * each element represents the max number of steps that can be made forward from that index.
 * The task is to find the minimum number of jumps to reach the end of the array
 * starting from index 0.
 *
 * Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
 * Output: 3 (1-> 3 -> 9 -> 9)
 *
 */

/**
 *  30/01------ >>>
 * YASHODHAN- This is ridiculous..Though it seems to be DP problem initially..
 * I was not able to build recursive bottom up solution.
 * I tried to write partial recursive code ( Assuming DONT KNOW whether backtracking or something)
 * This also did not work..
 *
 * UNDERSTAND BACKTRACKING properly and also see why DP way was not came to mind.
 *
 */
public class JumpingArray_WIP {
    int minimumNumberOfJumps = Integer.MAX_VALUE; // Solution

    public void findMinimumNumberOfJumpsUtil(int [] array, int index, int jumps) {
        System.out.println(" Index: "+index + ", Jumps: "+jumps);

        // Base cases - Solution case
        if(index == array.length - 1) {
            if (minimumNumberOfJumps > jumps) {
                minimumNumberOfJumps = jumps; // Keep updating minimum
                System.out.println("Minimum Number of Jumps= "+ minimumNumberOfJumps);
            }
        }

        // Base case- Invalid state
        if (index >= array.length) {
            // Do nothing
            return;
        }

        // Read the max allowed jumps from given index
        int localJumpCount = 0;
        for (int i = index+1; i <= array[index]; i++) {
            localJumpCount+=1;
            // Explore
            findMinimumNumberOfJumpsUtil(array, i, jumps+localJumpCount);
            // Backtrack
        }
    }

    public static void main(String args [] ) {
        JumpingArray_WIP array = new JumpingArray_WIP();
        array.findMinimumNumberOfJumpsUtil(
                new int [] {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9},
                0,
                0
        );

    }

}
