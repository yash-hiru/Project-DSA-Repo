package com.yashodhan.core_dsa_problems.algorithms.backtracking;

import java.util.ArrayList;

/**
 * Given a string S, the task is to write a program to print all permutations of a given string.
 * <p>
 * Input: S = “ABC”
 * Output: “ABC”, “ACB”, “BAC”, “BCA”, “CBA”, “CAB”
 * <p>
 * Variation===> Find all the subsets of set. Accept any solution (NO length based check for adding the result) but exit
 * on the max size
 */
public class PermutationsOfString {

    /**
     * MAIN Method-- Drive program
     *
     * @param argv
     */
    public static void main(String[] argv) {
        System.out.println(findPermutations());

    }

    /**
     * Approach: Start with single character, try permutations
     */
    private static ArrayList<String> findPermutations() {
        ArrayList<String> permutations = new ArrayList<>();
        String[] uniqueCharacters = new String[]{"A", "B", "C"};

        // Initialize and find permutations recursively
        CORE_findPermutationsRecursive(uniqueCharacters, "", permutations);
        return permutations;
    }

    /**
     * CORE METHOD-- Recursively find permutations and add to the result
     *
     * @param permutation      Increases upon each recursive invocation..Leads to solution ( size N string) for some valid candidates
     * @param uniqueCharacters Unique characters
     * @param permutations     Solution (In out parameter)
     * @return true for valid candidate call and false for invalid/backtracked partial solution inner recursive calls
     */
    private static boolean CORE_findPermutationsRecursive(
            final String[] uniqueCharacters,
            String permutation,
            ArrayList<String> permutations) {
        // Exit condition
        if (permutation.length() == uniqueCharacters.length) {
            // YAY..YOU got one permutation..!!
            permutations.add(permutation);
            // Return true to let caller know that you DONT need to backtrack for this VALID LEAF NODE
            return true;
        }

        // Explore all the valid choices.. for "each" string "position" staring with [0...N-1]
        for (String ch : uniqueCharacters) {
            if (!permutation.contains(ch)) {
                // CHOOSE --- VALID CHOICE
                permutation = permutation + ch; // ADD/Accepth the partial solution at this stage and recurse

                // RECURSE FURTHER--If we have valid choice ( Non-repeated character)
                if (CORE_findPermutationsRecursive(uniqueCharacters, permutation, permutations) == false) {
                    // INVALID PARTIAL SOLUTION----
                    //      OOPS..Path we took by adding "ch" to the solution DOES NOT end to desired state deep inside recursive tree.
                    //      There is no point in exploring further on this partial solution(permutation+ch)

                    // BACKTRACK=== -- Remove current choice (Strip last character ( which is current )
                    // EXPLORE OTHER CANDIDATES --- For loop will do the work
                    permutation = permutation.substring(0, permutation.length() - 1);
                }
                // Else continue other sibling choices for this call
            }
        }
        // INVALID leaf node
        return false;
    }
}
