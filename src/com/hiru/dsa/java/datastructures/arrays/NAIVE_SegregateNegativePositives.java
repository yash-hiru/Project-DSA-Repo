package com.hiru.dsa.java.datastructures.arrays;

import java.util.Arrays;

/**
 * Move all negative numbers to beginning and positive to end with constant extra space - GeeksforGeeks
 * https://www.geeksforgeeks.org/move-negative-numbers-beginning-positive-end-constant-extra-space/amp/
 */
public class NAIVE_SegregateNegativePositives {
    public static void main(String args[]) {
        int[] arr = new int[]{-1, 44, 22, -3, 11, -2, -4};
        sort(arr, arr.length);
        System.out.println(Arrays.toString(arr));
    }

    static void sort(int[] arr, int size) {
        int i = 0;
        int j = size - 1;

        while (i < j && i < size - 1 && j >= 0) {
            // 3 cases
            if (arr[i] >= 0 && arr[j] < 0) {
                // swap and update i,j
                swap(arr, i, j);
                i++;
                j--;
            } else if (arr[i] >= 0) {
                j--;
            } else if (arr[j] < 0) {
                i++;
            } else {
                i++;
                j--;
            }
        }
    }

    static void swap(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}



