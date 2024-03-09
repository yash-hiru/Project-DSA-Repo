package com.hiru.dsa.java.datastructures.matrix;

import java.util.LinkedList;

/**
 * GFG >>> https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/amp/
 */
public class STD_RottenOranges {
    public static void main(String[] args) {
        int[][] bucket = new int[6][6]; //4 directions
        // 1 - good, 2- rooten, 0-GAP
        bucket[0] = new int[]{0, 0, 0, 0, 1, 2};
        bucket[1] = new int[]{0, 0, 1, 0, 0, 0};
        bucket[2] = new int[]{1, 2, 1, 0, 0, 0};
        bucket[3] = new int[]{0, 1, 1, 1, 1, 0};
        bucket[4] = new int[]{0, 0, 0, 0, 0, 0};
        bucket[5] = new int[]{0, 0, 1, 1, 2, 0};

        System.out.println(minTimeToRotTomatoes(bucket, 6, 6));

    }

    private static LinkedList<NAIVE_FloodFillPaintBrush_DFS_BFS.Coord> getRotten(int[][] bucket, int mm, int nn) {
        LinkedList<NAIVE_FloodFillPaintBrush_DFS_BFS.Coord> currentRotten = new LinkedList<>();
        // Preprocess-- Get the current rotten tomatoes
        for (int i = 0; i < mm; i++) {
            for (int j = 0; j < nn; j++) {
                if (bucket[i][j] == 2) {
                    currentRotten.add(new NAIVE_FloodFillPaintBrush_DFS_BFS.Coord(i, j));
                }
            }
        }
        return currentRotten;
    }

    public static int minTimeToRotTomatoes(int[][] bucket, int mm, int nn) {
        int t = 0;
        // Success
        while (allRotten(bucket, mm, nn) == false) {
            rotOneTime(bucket, mm, nn);
            t += 1;
        }
        return t;
    }

    /**
     * Tricky to stop once you rot neighbour. Better to pass the rotten list
     *
     * @param bucket
     * @param mm
     * @param nn
     */
    private static void rotOneTime(int[][] bucket, int mm, int nn) {
        LinkedList<NAIVE_FloodFillPaintBrush_DFS_BFS.Coord> currentRotten = new LinkedList<>();
        // Preprocess-- Get the current rotten tomatoes
        for (int i = 0; i < mm; i++) {
            for (int j = 0; j < nn; j++) {
                if (bucket[i][j] == 2) {
                    currentRotten.add(new NAIVE_FloodFillPaintBrush_DFS_BFS.Coord(i, j));
                }
            }
        }
        // Rot only ONE level adjacent tomatoes of CURRENT rotten tomatoes
        for (NAIVE_FloodFillPaintBrush_DFS_BFS.Coord p : currentRotten) {
            int i = p.x;
            int j = p.y;
            if (i - 1 >= 0 && bucket[i - 1][j] == 1) {
                bucket[i - 1][j] = 2;
            }
            if (j - 1 >= 0 && bucket[i][j - 1] == 1) {
                bucket[i][j - 1] = 2;
            }
            if (i + 1 < mm && bucket[i + 1][j] == 1) {
                bucket[i + 1][j] = 2;
            }
            if (j + 1 < nn && bucket[i][j + 1] == 1) {
                bucket[i][j + 1] = 2;
            }
        }
    }


    private static boolean allRotten(int[][] bucket, int mm, int nn) {
        for (int i = 0; i < mm; i++) {
            for (int j = 0; j < nn; j++) {
                if (bucket[i][j] == 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
