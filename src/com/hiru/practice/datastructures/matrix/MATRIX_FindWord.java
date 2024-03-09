package com.hiru.practice.datastructures.matrix;

public class MATRIX_FindWord {
    public static void main(String args[]) {
        //Input
        int mm = 5, nn = 4;
        char[][] arr = new char[mm][nn];
        arr[0] = new char[]{'a', 'a', 'b', 'S'};
        arr[1] = new char[]{'S', 'a', 'E', 'b'};
        arr[2] = new char[]{'a', 'Y', 'E', 'S'};
        arr[3] = new char[]{'a', 'a', 'E', 'b'};
        arr[4] = new char[]{'a', 'a', 'b', 'S'};
        String w = "YES";

        // Check at all positions
        for (int i = 0; i < mm; i++) {
            for (int j = 0; j < nn; j++) {
                if (arr[i][j] == w.charAt(0) && search(arr, mm, nn, i, j, w)) {
                    // return if we found
                    System.out.println("Found at: " + i + "," + j);
                }
            }
        }
    }

    // Search from all the positions and go in all the directions
    public static boolean search(char[][] arr, int mm, int nn, int i, int j, String w) {
        return (i + 1 - w.length() >= 0 && searchUp(arr, mm, nn, i, j, w)) ||
                (j + 1 - w.length() >= 0 && searchLeft(arr, mm, nn, i, j, w)) ||
                (i + w.length() - 1 < mm && searchDown(arr, mm, nn, i, j, w)) ||
                (j + w.length() - 1 < nn && searchRight(arr, mm, nn, i, j, w));
    }

    private static boolean searchUp(char[][] arr, int mm, int nn,
                                    int i, int j,
                                    String w) {
        int pos = 0;
        if (i >= 0 && i < mm && j >= 0 && j < nn) {
            // Bound check
            for (int var = i; var >= 0 && pos < w.length(); var--) {
                // Current position
                if (arr[var][j] != w.charAt(pos++)) {
                    return false;
                }
            }
        }
        return (pos == w.length()); // It should process all the chars
    }

    private static boolean searchDown(char[][] arr, int mm, int nn,
                                      int i, int j,
                                      String w) {
        int pos = 0;
        if (i >= 0 && i < mm && j >= 0 && j < nn) {

            // Bound check
            for (int var = i; var < mm && pos < w.length(); var++) {
                // Current position
                if (arr[var][j] != w.charAt(pos++)) {
                    return false;
                }
            }
        }
        return (pos == w.length()); // It should process all the chars
    }

    private static boolean searchLeft(char[][] arr, int mm, int nn,
                                      int i, int j,
                                      String w) {
        int pos = 0;
        if (i >= 0 && i < mm && j >= 0 && j < nn) {
            // Bound check
            for (int var = j; var >= 0 && pos < w.length(); var--) {
                // Current position
                if (arr[i][var] != w.charAt(pos++)) {
                    return false;
                }
            }
        }
        return (pos == w.length()); // It should process all the chars
    }

    private static boolean searchRight(char[][] arr, int mm, int nn,
                                       int i, int j,
                                       String w) {
        int pos = 0;
        if (i >= 0 && i < mm && j >= 0 && j < nn) {
            // Bound check
            for (int var = j; var < nn && pos < w.length(); var++) {
                // Current position
                if (arr[i][var] != w.charAt(pos++)) {
                    return false;
                }
            }
        }
        return (pos == w.length()); // It should process all the chars
    }


}
