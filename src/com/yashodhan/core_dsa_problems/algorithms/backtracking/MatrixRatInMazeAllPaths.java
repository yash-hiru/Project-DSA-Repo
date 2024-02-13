package com.yashodhan.core_dsa_problems.algorithms.backtracking;

import java.util.ArrayList;

public class MatrixRatInMazeAllPaths {


    public static ArrayList<String> results = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("START Rat in a maze GAME..... Finding all Valid paths....");
        MatrixRatInMazeAllPaths underTest = new MatrixRatInMazeAllPaths();

        // INPUT
        int[][] maze = new int[4][10];
        maze[0] = new int[]{1, 0, 0, 0, 1, 1, 0, 0, 1, 1};
        maze[1] = new int[]{1, 1, 1, 1, 1, 0, 0, 0, 0, 1};
        maze[2] = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 1, 1};
        maze[3] = new int[]{0, 1, 1, 1, 1, 1, 1, 0, 0, 1};

        // TEST-- Recursive call with initial state
        underTest.findPaths(maze, 4, 10);
        System.out.println("END Rat in a maze GAME................");
    }

    /**
     * Find all possible paths
     */
    private void findPaths(int[][] maze, int numRows, int numColumns) {
        boolean[][] visited = new boolean[numRows][numColumns];
        //Start recursive with entry point 0,0
        CORE_findPathsRecursively(maze, numRows, numColumns, 0, 0, visited, "");
    }

    /**
     * Find paths recursively
     * 1. Update any potential solution BEFORE during actual recursive call
     * 2. Insert valid candidate to result inside its own recursive call
     * 3. path String being immutable, need not tobe BACKTRACKEWD
     */
    private void CORE_findPathsRecursively(
            // read only params
            final int[][] maze,
            final int numRows,
            final int numColumns,
            // Mutable parameters
            int row,
            int column,
            boolean[][] visited,
            String path) {

        // BASE CASE 1 (Happy case) Stop when reached to final cell
        if (row == numRows - 1 && column == numColumns - 1) {
            results.add(path); // SUCCESS-- Add to path
            return;
        }

        //  BASE CASE 2 (Sad case) Reached to blocked cell
        if (maze[row][column] == 0 || visited[row][column]) {
            // Cant backtrack here since updating immutable path string is not possible (call by value)
            return;
        }

        // BACKTRACKING LOGIC ===>
        //************ Core logic
        // MAKE CHOICE >>>>> Mark this position as visited
        visited[row][column] = true;

        // EXPLORE == Explore all other valid options aka. potential choices (D, U, L, R )
        //Choice1- Explore Down
        if (row + 1 <= numRows - 1) {
            CORE_findPathsRecursively(maze, numRows, numColumns, row + 1, column, visited, path + 'D');
        }
        //Choice2- Explore UP
        if (row - 1 >= 0) {
            CORE_findPathsRecursively(maze, numRows, numColumns, row - 1, column, visited, path + 'U');
        }
        //Choice3- Explore RIGHT
        if (column + 1 <= numColumns - 1) {
            CORE_findPathsRecursively(maze, numRows, numColumns, row, column + 1, visited, path + 'R');
        }
        //Choice4- Explore Down
        if (column - 1 >= 0) {
            CORE_findPathsRecursively(maze, numRows, numColumns, row, column - 1, visited, path + 'L');
        }

        // BACKTRACK == Reverse the state by marking current node as NON VISITED to let caller try other choices for
        // this recursive level call
        visited[row][column] = false;
    }

}
