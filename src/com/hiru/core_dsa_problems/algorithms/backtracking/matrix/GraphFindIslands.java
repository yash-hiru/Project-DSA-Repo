package com.hiru.core_dsa_problems.algorithms.backtracking.matrix;

import java.util.ArrayList;

/**
 * Find islands/connected Subgraphs of unconnected graph
 * GFG ==> https://www.geeksforgeeks.org/find-the-number-of-islands-using-dfs/
 *
 * Given a binary 2D matrix, find the number of islands.
 * A group of connected 1s forms an island. For example, the below matrix contains 4 islands.
 *
 * Input: mat[][] =
 * .............{{1, 1, 0, 0, 0},
 * .............{0, 1, 0, 0, 1},
 * .............{1, 0, 0, 1, 1},
 * .............{0, 0, 0, 0, 0},
 * .............{1, 0, 1, 0, 0}}
 *
 * Output: .....4
 *
 * Learnings ================================>
 * ........ Misunderstood matrix/maze problem for graph problem
 * ............Hence instead of taking row, col input, I initially took just v as input (isPartOfIsland())
 * ............Did not thought through of visited node condition and also optimization for preexplored paths (WasVisited())
 * ........ Good that, i added checks for upper adn lower bounds and all choices
 * ...................also..considered the hint quickly
 * ...................chosen simple maze as single result
 * ...................used cache/result lookup optimization (isPartOfIsland())
 * ...................Found the solution within 10 mins upon realizing this as RAT in maze problem
 * ...................Counted as well as stored all the islands
 */

public class GraphFindIslands {

    //////////////////////////////
    public static void main(String[] args) {
        int[][] matrix = new int[5][5];

        matrix[0] = new int[]{1, 1, 0, 0, 0};
        matrix[1] = new int[]{0, 1, 0, 0, 1};
        matrix[2] = new int[]{1, 0, 0, 1, 1};
        matrix[3] = new int[]{0, 0, 0, 0, 0};
        matrix[4] = new int[]{1, 0, 1, 0, 0};

        ArrayList<int[][]> islands = new ArrayList<>();
        //TODO: Multiple DFS
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix.length; column++) {
                //Optimize -- Involke for explorable paths and no visited nodes
                if (isPartOfIsland(islands, row, column) == false && matrix[row][column] == 1) {
                    int[][] island = new int[5][5];
                    CORE_findIsland(matrix, matrix.length, row, column, island);
                    // Ignore the pre-processed entries
                    if (true == wasUpdated(island, matrix.length)) {
                        islands.add(island);
                    }
                }
            }
        }
        System.out.println(islands.size());
        System.out.println(islands);
    }

    //////////////////////////////
    static boolean isPartOfIsland(ArrayList<int[][]> islands, int row, int column) {
        for (int[][] island : islands) {
            if (island[row][column] == 1) {
                return true;
            }
        }
        return false;
    }

    //////////////////////////////
    static boolean wasUpdated(int[][] islands, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (islands[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }


    //////////////////////////////
    public static void CORE_findIsland(final int[][] matrix, final int size, int row, int column, int[][] island) {
        if (matrix[row][column] == 1 && island[row][column] == 0) {
            // Visit V ( add to island) ===>
            island[row][column] = 1;

            // Explore neighbors ===>
            // Explore LEFT
            if (row - 1 >= 0) {
                CORE_findIsland(matrix, size, row - 1, column, island);
            }

            // Explore RIGHT
            if (row + 1 < size) {
                CORE_findIsland(matrix, size, row + 1, column, island);
            }

            // Explore UP
            if (column - 1 >= 0) {
                CORE_findIsland(matrix, size, row, column - 1, island);
            }

            // Explore DOWN
            if (column + 1 < size) {
                CORE_findIsland(matrix, size, row, column + 1, island);
            }

            // Explore diagonal Top-Left
            if (row - 1 >= 0 && column - 1 >= 0) {
                CORE_findIsland(matrix, size, row - 1, column - 1, island);
            }

            // Explore diagonal Top-right
            if (row - 1 >= 0 && column + 1 < size) {
                CORE_findIsland(matrix, size, row - 1, column + 1, island);
            }

            // Explore diagonal Bottom-left
            if (row + 1 < size && column - 1 >= 0) {
                CORE_findIsland(matrix, size, row + 1, column - 1, island);
            }

            // Explore diagonal Bottom-Right
            if (row + 1 < size && column + 1 < size) {
                CORE_findIsland(matrix, size, row + 1, column + 1, island);
            }

        }
    }
}


////////////////////////////// Considerations
/**
 * Concepts =====>
 * Island = isolated connected undirected subgraph
 * Worst case =  Each node unconnected
 * Best case = All node connected
 *
 * Strategy =====>
 * 1. DFS from all the vertexes
 * 2. Keep track of visited nodes
 * 3. Ignore visited node
 * 4. If no more node could be visited (visited=true || unreachable) ==> you are in island
 * 5. Identify unique island
 */
