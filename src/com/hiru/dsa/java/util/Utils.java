package com.hiru.dsa.java.util;

import java.util.Arrays;

public class Utils {
    public static void fill2D(int[][] memo, int val) {
        for (int i = 0; i < memo.length; i++) {
            Arrays.fill(memo[i], val);
        }
    }
}
