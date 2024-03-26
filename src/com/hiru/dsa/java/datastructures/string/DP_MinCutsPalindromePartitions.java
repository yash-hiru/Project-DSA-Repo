package com.hiru.dsa.java.datastructures.string;

import org.apache.commons.lang3.StringUtils;

/**
 * LEARNINGS:
 * ............. If you do i=k+1, one of the input lead to 2 size string
 * ..............e.g. (0-1) which will end up NOT entering loop and returning MAX_VALUE
 * ..............this will lead to INT overflow for its caller where 1+func()+func() is happening.
 * ..............1. Better to draw recursive trees for small inputs and see if ANY BASE CASE is missing
 * ............. 2. Also see if you dont pass on any input accidently in for initialization
 */
public class DP_MinCutsPalindromePartitions {
    // 2 mins
    static boolean isPalindrome(String s) {
        if (s == null || StringUtils.isEmpty(s)) {
            return true;
        }
        int len = s.length();
        for (int i = 0, j = len - 1; i <= j; i++, j--) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    ////////////////////////////////////////////////////
    static int numCutsPalin(String s, int i, int j, Integer[][] memo) {
        if (i >= j)
            return 0;

        if (isPalindrome(s.substring(i, j + 1)))
            return memo[i][j] = 0;


        if (memo[i][j] != null) {
            return memo[i][j];
        }

        // Cut and count
        int mincuts = Integer.MAX_VALUE;

        for (int k = i; k < j; k++) {
            // Cut and count
            memo[i][k] = numCutsPalin(s, i, k, memo);
            memo[k + 1][j] = numCutsPalin(s, k + 1, j, memo);
            mincuts = Math.min(mincuts, 1 + memo[i][k] + memo[k + 1][j]);
        }

        return memo[i][j] = mincuts;
    }

    /**
     * Max cuts: N
     * Min Cuts: 1
     */

    public static void main(String[] args) {
        String str = "abbaccdcc"; //1
        Integer[][] memo = new Integer[str.length() + 1][str.length() + 1];
        System.out.println("PERSONAL////Min Palindrome Cuts:" + numCutsPalin(str, 0, str.length() - 1, memo));
    }
}

