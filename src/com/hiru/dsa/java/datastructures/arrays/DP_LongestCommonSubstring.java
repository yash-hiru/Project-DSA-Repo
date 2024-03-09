package com.hiru.dsa.java.datastructures.arrays;

import java.util.Arrays;

/**
 * LONGEST COMMON SUBSTRING
 * ........... https://www.geeksforgeeks.org/longest-common-substring-space-optimized-dp-solution/
 * ........... GFG is incomplete and buggy solution
 * ........... Does NOT work for following input (ababcdcdaaab, abaabaaabccd) ==> aaab
 */
public class DP_LongestCommonSubstring {

    /**
     * Driver Code
     *
     * @param args
     */
    public static void main(String args[]) {
        //--------- Inputs
        // TEST-OK (Tricky input for which GFG solution fails)
        String s1 = "ababcdcdaaab"; //aaab
        String s2 = "abaabaaabccd"; //aaab

        // TEST-OK
        // String s1 = "GeeksforGeeks";
        //String s2 = "GeeksQuiz";

        // TEST-OK
        // String s1 = "abcd";
        // String s2 = "xyzabcd";

        //--------- Memo Initialize
        int[][] memo = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        //--------- INVOKE LCS UTIL
        CORE_calculate_lcsUtil(s1, s2, s1.length(), s2.length(), 0, 0, memo);

        // EXTREMELY IMPORTANT-- Unlike typical DP problem, we don't find solution at memo[0][0]
        // Make sure to explore all the memo to find the max solution
        int maxLcs = Integer.MIN_VALUE;
        int pos1 = 0;
        int pos2 = 0;
        for (int m1 = 0; m1 < s1.length(); m1++) {
            for (int m2 = 0; m2 < s2.length(); m2++) {
                if (memo[m1][m2] > maxLcs) {
                    maxLcs = memo[m1][m2];
                    pos1 = m1;
                    pos2 = m2;
                }
            }
        }
        System.out.println("Done >>>>>>  Max Longest Common Sub-STRING: " + maxLcs + ",  S1 Position: " + pos1 + ",  S2 position: " + pos2);
    }

    /**
     * CORE function to detect maxLCS inside memo.
     *
     * Extremely Important learnings =======>
     * .......1) Diff between <LCSubsquence, LCString> >>>
     * ..........Unlike Longest common sub-SEQUENCE, longest common sub-STRING DOES NOT have
     * ..........3 choices. ONLY choice it has is to match and cache the result. DON'T use other 2 cases for comparison of result
     * ..........Always Return only matched number of characters ( Consecutive ****)
     * ..........HOWEVER, you HAVE to invoke 2 other combinations ( i1+1, i2) and (i1, i2+1) to give them OPPORTUNITY
     *
     * .......2) Output Postprocessing >>>
     * ..........Required for this particular sub-problem.
     * ..........task is to find the MAX value from memo and its 2D position.
     *
     * @return
     */
    private static int CORE_calculate_lcsUtil(final String s1,
                                              final String s2,
                                              final int len1,
                                              final int len2,
                                              int i1,
                                              int i2,
                                              int[][] memo) {
        //------ BASE CASE-- s1 empty
        if (i1 == len1 || i2 == len2) {
            memo[len1][len2] = 0;
            return memo[len1][len2];
        }

        //------Cache check
        if (memo[i1][i2] != -1) {
            return memo[i1][i2];
        }

        //------ EXPLORE 3 cases ( Match, Mismatch1, Mismatch2)
        // CASE 1 -- Character matched (special case) >>> JUMP FROM HERE
        if (s1.charAt(i1) == s2.charAt(i2)) {
            // CHARACTER MATCHED --Happy case
            memo[i1 + 1][i2 + 1] = CORE_calculate_lcsUtil(s1, s2, len1, len2, i1 + 1, i2 + 1, memo); // Nested call cached
            memo[i1][i2] = 1 + memo[i1 + 1][i2 + 1]; // +1 for matched character
        }
        //EXTREMELY IMPORTANT--- We have got the best SHOT for max common substring at i1, i2 respectively..
        // Preserve the solution and explore other options

        // EXTRA PROCCESING >>> Explore other options ==> Unlike in LCSubsequence, DONT let these choices influence memo[i1][i2] position outcome
        memo[i1 + 1][i2] = CORE_calculate_lcsUtil(s1, s2, len1, len2, i1 + 1, i2, memo);// Nested call cached
        memo[i1][i2 + 1] = CORE_calculate_lcsUtil(s1, s2, len1, len2, i1, i2 + 1, memo);// Nested call cached

        // >>> JUMP TO HERE
        //This is the answer for starting pos < i1, i2>
        return memo[i1][i2];
    }
}
