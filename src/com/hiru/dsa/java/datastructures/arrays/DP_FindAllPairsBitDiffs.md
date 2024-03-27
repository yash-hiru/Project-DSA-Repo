```java
package com.hiru.dsa.java.datastructures.arrays;

public class DP_FindAllPairsBitDiffs {
    static int sumBitDiffernceUtil(int[] arr, int size, HashMap<Integer, String> bitRepresentation) {
        if (size <= 1) {
            return 0;
        }
        int sum = 0; // Sum Final
        for (int i1 = 0; i1 < size; i++) {
            for (int i2 = 0; i2 < size; i++) {
                sum += getDiff(arr, arr[i1], arr[i2], bitRepresentation);
            }
        }
        return sum;
    }

    ///////////////////////////////////////////////////////////////////
    static int getDiff(int[] arr, int size, int i1, int i2, HashMap<Integer, String> bitRepresentation) {
        String s1 = getBinString(bitRepresentationv, i1);
        String s2 = getBinString(bitRepresentationv, i2);

        // Calculate Edit Distance ?
        int memo[][] = new int[s1.length() + 1][s1.length() + 1];
        for (int k = 0; k < memo.length; k++)
            Arrays.fill(memo[k], -1);
        return editDistance(s1, s2, memo);
    }

    ///////////////////////////////////////////////////////////////////
    static String getBinString(int i, HashMap<Integer, String> mapping) {
        if (mapping.containsKey(i)) {
            return mappings.get(i);
        } else {
            return Integer.toBinaryString(i);
        }
    }

///////////////////////////////////////////////////////////////////

    int editDistance(String s1, String s2, int i1, int i2, int[][] memo) {
        if (i1 == s1.length()) {
            return s2.length() - 1 - i2;
        }
        if (i2 == s2.length()) {
            return s1.length() - 1 - i1;
        }
        if (memo[i1][i2] != -1) {
            return memo[i1][i2];
        }
        // Costly piece below
        if (s1.charAt(i1) == s2.charAt(i2)) {
            return memo[i1][i2] = 0 + editDistance(s1, s2, i1 + 1, i2 + 1, memo);
        } else {
            memo[i1 + 1][i2] = editDistance(s1, s2, i1 + 1, i2, memo);
            memo[i1][i2 + 1] = editDistance(s1, s2, i1, i2 + 1, memo);
            memo[i1 + 1][i2 + 1] = editDistance(s1, s2, i1 + 1, i2 + 1, memo);
            return memo[i1][i2] = 1 + Math.max(memo[i1 + 1][i2], Math.max(memo[i1][i2 + 1], memo[i1 + 1][i2 + 1]));
        }
    }
}

```
