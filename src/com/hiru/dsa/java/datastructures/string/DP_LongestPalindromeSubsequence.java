package com.hiru.dsa.java.datastructures.string;

/**
 * LONGEST PALINDROME SUB-SEQUENCE
 * GFG ==> https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
 *
 * Learning >>>>>>>>>>>>
 * .............1. Problem was simple enough. Just pay attention to required inputs
 * .............2. On match, increase count by 2 and not by 1
 * .............3. Pay attention to all the base cases
 * .............4. Also, make sure to choose among  IF <Choice1> ... ELSE MAX<choice2, choice3>
 */
public class DP_LongestPalindromeSubsequence {

    public static void main(String[] args) {
        String input = "GEEKSFORGEEKS";
        System.out.println("Longest Palindrome Subsequence: " + lps(input, input.length(), 0, input.length() - 1));
    }

    public static int lps(final String s,
                          final int len,
                          int iStart,
                          int iEnd) {
        // BASE case-- VALIDATIONS >>> No palindrome could exist further ( reached adjacent pos, iStart reached end, iEnd reached start)
        if (iStart > iEnd || iStart == len || iEnd == -1) {
            return 0;
        }

        // BASE case-- Odd palindrome >>>
        if (iEnd == iStart) {
            return 1;
        }

        //Explore sub-problems >>>>>>

        // CHOOSE between 3 choices( 1 + 2 ) since its Sub-SEQUENCE problem.
        // CHOICE 1 -- Matched
        if (s.charAt(iStart) == s.charAt(iEnd)) {
            return 2 + lps(s, len, iStart + 1, iEnd - 1); // IMPORTANT--Increase by 2 (not by 1 :-) )
        } else {
            return Math.max(lps(s, len, iStart, iEnd - 1),
                    lps(s, len, iStart + 1, iEnd));
        }
    }


}
