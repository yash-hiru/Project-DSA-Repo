package com.hiru.dsa.md.practiceOnline.orcl;

/**
 * Problem1: Assign delievery to client
 * Problem2: This one (coding)
 */
public class OrcR1Perc {
    public static void main(String args[]) {
        int[][] grid = new int[4][4];
        grid[0] = new int[]{0, 1, 1, 0};
        grid[1] = new int[]{0, 0, 1, 1};
        grid[2] = new int[]{0, 0, 0, 1};
        grid[3] = new int[]{1, 1, 0, 0};
        System.out.println(percolate(grid, 0, 0, 4, 4));
    }


    /**
     * WHAT WENT WRONG:
     * All conditions within the CHOSEN option
     * Unnecessary calls to other choices even though some inner choice was TRUE
     * (add condition of ret == false to explore only if we DID Not find any solution with other option)
     *
     * @return True(if percolate to last level ELSE false)
     */
    static boolean percolate(int[][] grid, int r, int c, int rowMax, int colMax) {
        boolean ret = false;
        // Base case : ONLY IF
        if (r == rowMax - 1 && (c >= 0 && c < colMax) && grid[r][c] == 0) {
            return true;
        }
        // Make a choice if its valid
        if (grid[r][c] == 0) {
            grid[r][c] = 2; // Mark filled and explore other nearer cells
            // Explore valid options
            if (r + 1 < rowMax) {
                ret = percolate(grid, r + 1, c, rowMax, colMax);
            }
            if (ret == false && r - 1 >= 0) {
                ret = percolate(grid, r - 1, c, rowMax, colMax);
            }
            if (ret == false && c + 1 < colMax) {
                ret = percolate(grid, r, c + 1, rowMax, colMax);
            }
            if (ret == false && c - 1 >= 0) {
                ret = percolate(grid, r, c - 1, rowMax, colMax);
            }
            grid[r][c] = 0;
        }
        return ret;
    }
}
