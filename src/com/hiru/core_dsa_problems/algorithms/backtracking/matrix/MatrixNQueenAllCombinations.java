package com.hiru.core_dsa_problems.algorithms.backtracking.matrix;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Considerations:
 * 1. N queen problem for NxN board.
 * 2. You DO NOT need to track <row,colum> since row IMPLICITLY Indicate queen_id
 * 3. Which means, solution should have tracking JUST FOR Column Numbers
 * validSolution = int[size];
 * validSolutionVisitor = boolean[size]
 * 4. Which also means, MAX depth of recursive tree will be N ( Which indicates that
 * you are on the right path of 'potential' solution(7 columns depth recursion) and just ONE step away
 * from getting 'final' solution (last column placement)
 */
public class MatrixNQueenAllCombinations {
    public static final int SIZE = 5;

    /**
     * MAIN -- DRIVER FUNCTION
     */
    public static void main(String[] args) {
        MatrixNQueenAllCombinations underTest = new MatrixNQueenAllCombinations();

        // Single combination
        char[][] combination = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; ++i) {
            Arrays.fill(combination[i], '-');
        }
        // Result--All Combinations
        ArrayList<char[][]> allCombinations = new ArrayList<>();

        // TEST-- Start processing
        underTest.nQueenProblemRecurse(0, SIZE, combination, allCombinations);

    }

    /**
     * Recursive method for backtracking
     *
     * @param column -- Column to be updated with current queen ( 1column :: 1queen)
     * @param size readonly -- dimension of array
     * @param singleCombination -- Single valid combination
     * @param allCombinations
     */
    public void nQueenProblemRecurse(int column,
                                     final int size,
                                     char[][] singleCombination,
                                     ArrayList<char[][]> allCombinations) {

        //*** Happy Exit conditions (Processed all the queens/columns)
        if (column == size) {
            // We had got the solution in singleResult
            // Simply add to global state or print it
            allCombinations.add(singleCombination.clone());

            //DEBUG- Print
            System.out.println("\n >>>>>>>>>>>>>>>>> Solution");
            for (char[] row : singleCombination) {
                System.out.println(Arrays.toString(row));
            }
            return;
        }

        //*** Explore choices recursively - Explore ALL ROWS at this column(recursion depth)
        for (int row = 0; row < size; row++) {
            // EXTREMELY IMPORTANT PART (Applicable for Any Backtracking problem)
            if (isConflicting(row, column, singleCombination) == false) {
                // Found some valid choice which is NON-Conflicting row for given <column>
                // UPDATE STATE -  Add queen to singleResult set and explore further depth (subsequent columns)
                singleCombination[row][column] = 'Q';
                // RECURSE- Recurse for next column
                nQueenProblemRecurse(column + 1, size, singleCombination, allCombinations);
                // BACKTRACK-- Update the same current state ( NOT the CHOICE)
                singleCombination[row][column] = '-';
                // Point to ponder: We DID not make any decision about further recursive call here... EXTREMELY IMPORTANT
            }
        }
    }

    /**
     * Check if safe/conflicting
     *
     * @param row
     * @param column
     * @param singleCombination
     *
     * @return
     */
    private boolean isConflicting(int row, int column, char[][] singleCombination) {
        int existingQueenColumn = 0; //Follows Standard Incremental path( as per recursive depth logic)
        // CONFLICTING at row level
        for (int i = 0; i < SIZE; i++) {
            if (singleCombination[i][column] == 'Q') {
                return true;
            }
        }
        // CONFLICTING at column level
        for (int i = 0; i < SIZE; i++) {
            if (singleCombination[row][i] == 'Q') {
                return true;
            }
        }
        int i, j;
        // CONFLICTING at diagonal level- BOTTOM RIGHT
        for (i = row + 1, j = column + 1; i < SIZE && j < SIZE; i++, j++) {
            if (singleCombination[i][j] == 'Q') {
                return true;
            }
        }
        // CONFLICTING at diagonal level- BOTTOM LEFT
        for (i = row + 1, j = column - 1; i < SIZE && j >= 0; i++, j--) {
            if (singleCombination[i][j] == 'Q') {
                return true;
            }
        }
        // CONFLICTING at diagonal level- TOP RIGHT
        for (i = row - 1, j = column + 1; i >= 0 && j < SIZE; i--, j++) {
            if (singleCombination[i][j] == 'Q') {
                return true;
            }
        }
        // CONFLICTING at diagonal level- TOP LEFT
        for (i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            if (singleCombination[i][j] == 'Q') {
                return true;
            }
        }
        return false; // Return only false if given new <row,column> position does NOT conflict with ANY existing queen
    }
}
