package com.hiru.dsa.java.datastructures.arrays;

import java.util.Arrays;

/**
 * * Sort an array of 0s, 1s and 2s | Dutch National Flag problem - GeeksforGeeks
 * * https://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/amp/
 */
public class NAIVE_SortZeroOnesTwos {
    public static void main(String args[]) {
        int[] arr = new int[]{2, 1, 2, 1, 0, 1, 0, 0, 2};
        sort(arr, arr.length, new int[]{0, 0, 0});
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr, int size, int[] keys) {
        int numKeys = keys.length;
        // Preprocess -- Get key counts (Tricky for keys other than 0-N range)
        for (int i : arr) {
            if (i < numKeys && i >= 0) {
                keys[i] = keys[i] + 1;
            } else {
                throw new RuntimeException("Found out of bound key");
            }
        }

        // Regenerate arrays
        int i = 0; //reset index to 0

        for (int j = 0; j < numKeys; j++) {
            int occ = keys[j];
            for (int o = 0; o < occ; o++) {
                arr[i] = j; //write key j to ith index in original array
                i++;
            }
        }
    }
}
