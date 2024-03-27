package com.hiru.dsa.java.datastructures.arrays;

/**
 * Count number of occurrences (or frequency) in a sorted array - GeeksforGeeks
 * Given a sorted array arr[] and a number x, write a function
 * that counts the occurrences of x in arr[]. Expected time complexity is O(Logn)
 *
 * https://www.geeksforgeeks.org/count-number-of-occurrences-or-frequency-in-a-sorted-array/amp/
 * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 2
 * Output: 4 // x (or 2) occurs 4 times in arr[]
 * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 3
 * Output: 1
 * Input: arr[] = {1, 1, 2, 2, 2, 2, 3},   x = 1
 * Output: 2
 * Input: arr[] = {1, 1, 2, 2, 2, 2, 3,},   x = 4
 * Output: -1 // 4 doesn't occur in arr[]
 */

/**
 * Leanings:=========>
 * .............1) Have if else for BOTH happy and sad for exits
 * .............2) OK to have redundant index conditions even after for loops..Obvious Index bound checks NEVER DISAPPOINT us
 * .............3) Try to have base-base conditions( 0 or 1 elements) in the beginning itself
 * .............4) There are other base conditions ( 0 or 1 elements) but in the middle of array ( handle both types gracefully)
 */
public class NAIVE_CountSingleElementFrequencyInLogNTime {
    public static void main(String args[]) {
        System.out.println(getNumOccurances(new int[]{1, 1, 2, 2, 2, 2, 3}, 1) + ", Expected: 2");
        System.out.println(getNumOccurances(new int[]{1, 1, 2, 2, 2, 2, 3}, 2) + ", Expected: 4");
        System.out.println(getNumOccurances(new int[]{3, 3}, 3) + ", Expected: 2");
        System.out.println(getNumOccurances(new int[]{3}, 3) + ", Expected: 1");
        System.out.println(getNumOccurances(new int[]{1}, 2) + ", Expected: 0");
        System.out.println(getNumOccurances(new int[]{1, 2}, 3) + ", Expected: 0");
        System.out.println(getNumOccurances(new int[]{}, 3) + ", Expected: 0");
    }

    public static int getNumOccurances(int[] arr, int x) {
        if (arr.length == 0 || x < arr[0] || x > arr[arr.length - 1]) {
            return 0;
        }
        int i = 0, j = arr.length - 1, iMid = i;
        int count = 0;

        // Find element
        while (i + 2 <= j && i < arr.length - 1 && j >= 0) {
            iMid = (int) i + (j - i) / 2;

            if (arr[iMid] == x) {
                break;
            } else if (arr[iMid] < x) {
                i = iMid;
            } else {
                j = iMid;
            }
        }
        // case1-- Found at iMid
        if (arr[iMid] == x) {
            count++;
            for (int k = iMid - 1; k >= 0; k--) {
                if (arr[k] == x)
                    count++;
            }
            for (int k = iMid + 1; k < arr.length; k++) {
                if (arr[k] == x)
                    count++;
            }
        }
        // case2-- Not found at iMid
        else {
            // Mid was not found and we have 2 or less elements to be explored
            // 2 elements
            if (i >= arr.length - 1 && j >= 0 && i + 1 == j) {
                if (arr[i] == x)
                    count++;
                if (arr[j] == x)
                    count++;
            } else if (i >= 0 && i < arr.length && arr[i] == x)
                count++;
        }
        return count;
    }
}


/**
 * Element Exists --> 0 freq
 * Element Dont exist --> 0 Freq
 */