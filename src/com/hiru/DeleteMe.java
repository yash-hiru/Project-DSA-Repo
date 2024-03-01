package com.hiru;

public class DeleteMe {
    public static void main(String args[]) {
        String str = "ZZAABAACFRTACA";
        //String str = "DDDAABAACRRRRRCZZ";
        int[][] memo = new int[str.length()][str.length()];
        System.out.println(longestPalindromeString(str, 0, str.length() - 1, memo));
        return;
    }

    static int longestPalindromeString(String str, int s, int e, int[][] memo) {
        if (e < s || s == str.length() || e == -1) {
            return 0; // No palindrome further
        }

        if (e == s) {
            // Odd palindrome mid
            memo[s][e] = 1;
            return memo[s][e];
        }

        // Explore choices
        int sol1 = -2, sol2 = -1, sol3 = -1;
        if (str.charAt(s) == str.charAt(e)) {
            if (s + 1 < str.length() && e - 1 >= 0) {
                sol1 = memo[s + 1][e - 1] = 2 + longestPalindromeString(str, s + 1, e - 1, memo);
            }
        }
        //Option2-3
        if (s + 1 < str.length()) {
            sol2 = memo[s + 1][e] = longestPalindromeString(str, s + 1, e, memo);
        }
        if (e - 1 >= 0) {
            sol3 = memo[s][e - 1] = longestPalindromeString(str, s, e - 1, memo);
        }

        return Math.max(sol1,
                Math.max(sol2,
                        sol3)
        );
    }
}
