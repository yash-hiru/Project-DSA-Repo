package com.yashodhan.practice;

class GFG {

    public static int findKthLargest(int[] arr, int n,
                                     int k)
    {
        int low = Integer.MAX_VALUE,
                high = Integer.MIN_VALUE;

        // Find the minimum and maximum elements in the
        // array
        for (int i : arr) {
            low = Math.min(low, i);
            high = Math.max(high, i);
        }

        // Perform binary search on the range of elements
        // between low and high
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = 0;

            // Count the number of elements greater than mid
            // in the array
            for (int i : arr) {
                if (i > mid) {
                    count++;
                }
            }

            // If there are at least K elements greater than
            // mid, search the right half
            if (count >= k) {
                low = mid + 1;
            }
            // Otherwise, search the left half
            else {
                high = mid - 1;
            }
        }

        // Return the Kth largest element
        return high;
    }

    public static void printKLargest(int[] arr, int n,
                                     int k)
    {
        // Find the Kth largest element
        int kthLargest = findKthLargest(arr, n, k);

        // Print the K largest elements in decreasing order
        for (int i : arr) {
            if (i >= kthLargest) {
                System.out.print(" " + i);
            }
        }
    }

    public static void main(String[] args)
    {
        int[] arr = { 12, 5, 787, 1, 23 };
        int n = arr.length;
        int k = 4;

        System.out.print("K largest elements:");
        printKLargest(arr, n, k);
    }
}
// This code is contributed by Rohit Singh
