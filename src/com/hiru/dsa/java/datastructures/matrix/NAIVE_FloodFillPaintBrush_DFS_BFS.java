package com.hiru.dsa.java.datastructures.matrix;

import java.util.LinkedList;

/**
 * Flood Fill ( paint brush implementation)
 * GFG: https://www.geeksforgeeks.org/flood-fill-algorithm/
 */
public class NAIVE_FloodFillPaintBrush_DFS_BFS {
    public static void main(String args[]) {
        //Input
        int mm = 5, nn = 5;
        // Desired state-- Replace all surrounding and current 1 by 2 if clicked any 1 cell

        int[][] arrDFS = new int[mm][nn];
        arrDFS[0] = new int[]{0, 0, 0, 0, 0};
        arrDFS[1] = new int[]{0, 0, 0, 1, 0};
        arrDFS[2] = new int[]{0, 0, 1, 1, 0};
        arrDFS[3] = new int[]{0, 1, 1, 1, 0};
        arrDFS[4] = new int[]{0, 0, 0, 0, 0};
        floodFill_DepthFirstSearch(arrDFS, mm, nn, 1, 3);

        int[][] arrBFS = new int[mm][nn];
        arrBFS[0] = new int[]{0, 0, 0, 0, 0};
        arrBFS[1] = new int[]{0, 0, 0, 1, 0};
        arrBFS[2] = new int[]{0, 0, 1, 1, 0};
        arrBFS[3] = new int[]{0, 1, 1, 1, 0};
        arrBFS[4] = new int[]{0, 0, 0, 0, 0};
        floodFill_BreadthFirstSearch(arrBFS, mm, nn, 1, 3);
        System.out.println("Done");
    }

    private static void floodFill_DepthFirstSearch(int[][] arr, int mm, int nn, int i, int j) {
        // Validations
        if (i >= 0 && i < mm && j >= 0 && j < nn) {
            // Ignore 0(cant filled) and 2(already filled)
            if (arr[i][j] == 1) {
                arr[i][j] = 2;
                // recurse
                floodFill_DepthFirstSearch(arr, mm, nn, i - 1, j);
                floodFill_DepthFirstSearch(arr, mm, nn, i + 1, j);
                floodFill_DepthFirstSearch(arr, mm, nn, i, j - 1);
                floodFill_DepthFirstSearch(arr, mm, nn, i, j + 1);
            }
        }
    }

    static class Coord {
        public Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
    }

    private static void floodFill_BreadthFirstSearch(int[][] arr, int mm, int nn, int i, int j) {
        LinkedList<Coord> queue = new LinkedList<>();
        // Initial state (Assuming i, j are within limits)
        if (arr[i][j] == 1) {
            queue.push(new Coord(i, j));
        }

        // BFS routine
        while (queue.isEmpty() == false) {
            Coord coord = queue.poll(); // get element
            int x = coord.x;
            int y = coord.y;
            // Run validations
            if (arr[x][y] == 1) {
                arr[x][y] = 2; // Update element
                // Visit Adj elements (IMPORTANT-- Check if visited before--AVOID infinite loop)
                if (x - 1 >= 0 && arr[x - 1][y] == 1)
                    queue.add(new Coord(x - 1, y));
                if (x + 1 < mm && arr[x + 1][y] == 1)
                    queue.add(new Coord(x + 1, y));
                if (y - 1 >= 0 && arr[x][y - 1] == 1)
                    queue.add(new Coord(x, y - 1));
                if (y + 1 < nn && arr[x][y + 1] == 1)
                    queue.add(new Coord(x, y + 1));
            }
        }
    }


}
