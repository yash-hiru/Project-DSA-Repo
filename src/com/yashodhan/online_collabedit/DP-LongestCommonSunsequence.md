## Solution

// ===========================
// Longest Common Sunsequence
//============================
Input: S1 = “AGGTAB”, S2 = “GXTXAYB”
Output: 4
Explanation: The longest subsequence which is present in both strings is “GTAB”.

Input: S1 = “BD”, S2 = “ABCD”
Output: 2
Explanation: The longest subsequence which is present in both strings is “BD”.

// ===========================
// Analysis/deep dive
// ===========================
Base cases:    
Both empty
One of them empty
Initial State:
0,0
End State:
size1-1 || size2-1
Any State:
ch1, ch2
Choices:
Matched : ch1==ch2 then i++ j++ (Choice1)
Not matched ch1!=ch2 then
i++, j (choice2)
j++, i (choice3)

// ===========================
// Implementation
// ===========================

```java
class LongestCommonSubsequence {

    ///////////////////////////////////////////// Driver
    public static void main(String[] args) {
        String s1 = “AGGTAB”;
        String s2 = “GXTXAYB”;

        // TODO: Refactor following lines to separate method

        System.out.println(findLCS(s1, s2, s1.size(), s2.size()));

    }

    ////////////////////////////////////////////
    public int findLCSU(String s1, String s2, int len1, int len2) {
        if (s1.isEmpty() || s2.isEmpty()) {
            // No point in initializing memo
            return 0;
        }
        int[][] memo = int [len1][len2];

        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], -1);
        }

        // Initial state --Trigger
        return findLCSUtil(s1, s2, len1, len2, 0, 0, memo)
    }
    //////////////////////////////////////////// Recursive utils

    public int findLCSUtil(String s1, String s2, int len1, int len2, int i, int j, int[][] memo) {
        // Base case
        if (i == len1 || j == len2) {
            return 0; // No common subsequence
        }

        // DP- Read result from cache for (i,j)
        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        // DP -- Recursive (O(N2))
        char ch1 = s1.charAt(i);
        char ch2 = s2.charAt(j);

        int lcs = Integer.MIN_VALUE;

        if (ch1 == ch2) {
            int lcs = Math.max(lcs, 1 + findLCSUtil(s1, s2, len1, len2, i + 1, j + 1));
        }
        lcs = Math.max(lcs, findLCSUtil(s1, s2, len1, len2, i + 1, j));
        lcs = Math.max(lcs, findLCSUtil(s1, s2, len1, len2, i, j + 1));

        // Write Cache 
        memo[i][j] = lcs;

        return memo[i][j];
    }

}
```

### Retro

- Solved with optimization within 25 mins
- Handled all the corner cases too
- End to end fully production grade code
- Discussed time complexity with and without optimization
- Time Complexity (optimal) - O(N^2)
- Space Complexity (optimal) - O(MxN)