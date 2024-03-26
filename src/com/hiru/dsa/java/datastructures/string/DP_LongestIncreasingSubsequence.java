package com.hiru.dsa.java.datastructures.string;
//TODO: Implement 15/02/2024

/**
 * LONGEST INCREASING SUBSEQUENCE >>>>>>>>>>
 * ...................GFG : https://www.geeksforgeeks.org/longest-increasing-subsequence-dp-3/.
 * ...................Input: arr[] = {3, 10, 2, 1, 20}
 * ...................Output: 3
 * ...................Explanation: The longest increasing subsequence is 3, 10, 20
 * ...................
 * ...................Input: arr[] = {3, 2}
 * ...................Output:1
 * ...................Explanation: The longest increasing subsequences are {3} and {2}
 *
 * LEARNINGS >>>>>>>>>>
 * ...................Required post processing since it always return count less than ONE (:-))
 */
public class DP_LongestIncreasingSubsequence {

    public static void main(String[] args) {
        //////////////// Trigger method code
        int[] input1 = new int[]{3, 10, 2, 1, 20};
        System.out.println("LIS: " + (1 + CORE_lis(input1, input1.length, 0))); //3 -- {3,10,20}
        //LEARNING--- Requires POST PROCESSING processing by adding 1 after getting result due to Happy case check

        int[] input2 = new int[]{4, 3, 2, 1};
        System.out.println("LIS: " + (1 + CORE_lis(input2, input2.length, 0))); //1 -- {4}

        int[] input3 = new int[]{50, 3, 10, 7, 40, 80};
        System.out.println("LIS: " + (1 + CORE_lis(input3, input3.length, 0))); //4 -- {3,10,40,80}


    }

    public static int CORE_lis(int[] s,
                               int len,
                               int i) {
        if (i == len) {
            return 0;
        }

        // Explore choices ( 1--- match and advance 2---Advance)
        if (i + 1 < len && s[i] < s[i + 1]) {
            // Happy case
            return 1 + CORE_lis(s, len, i + 1);
        } else {
            // Do nothing but to explore next
            return CORE_lis(s, len, i + 1);
        }
    }

}
