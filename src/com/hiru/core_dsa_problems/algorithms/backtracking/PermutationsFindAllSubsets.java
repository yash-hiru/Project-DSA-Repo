package com.hiru.core_dsa_problems.algorithms.backtracking;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Fix Bug inside the duplicate lookup logic..Rest looks good
 */
public class PermutationsFindAllSubsets {

    public static void main(String args[]) {
        // Inptus
        // Outputs
        // Invoke solution
        System.out.println(findAllSubsets());
    }

    /**
     * Find all Subsets kickoff logic
     *
     * @return
     */
    public static HashSet<String> findAllSubsets() {

        // Input-- Superset
        ArrayList<Integer> inputSet = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            inputSet.add(i);
        }

        // Result with base cases added before
        HashSet<String> supersetResultsHashes = new HashSet<>();
        supersetResultsHashes.add("");
        supersetResultsHashes.add("[0, 1, 2, 3]");

        // TEST-- Recursive call with initial state
        CORE_findAllSubsetsRecursively(inputSet,
                (ArrayList<Integer>) inputSet.clone(),
                supersetResultsHashes);
        // return result
        return supersetResultsHashes;
    }

    /**
     * CORE -- Find all subsets recursively
     *
     * @param inputSet       Immutable list of unique elements
     * @param subset         Partial Combination being passed in 'reduced' fashion
     * @param supersetHashes
     */
    public static void CORE_findAllSubsetsRecursively(final ArrayList<Integer> inputSet,
                                                      ArrayList<Integer> subset,
                                                      HashSet<String> supersetHashes) {
        // Stop recursive calls
        if (subset.size() == 0) {
            return;
        }
        // Choice (remove element if present)
        for (Integer elementChoice : inputSet) {
            // Remove if present
            if (subset.contains(elementChoice)) {
                // CHOOSE-- remove element
                subset.remove(elementChoice);

                //CHOOSE -- Cache the subset subsetHash
                subset.sort(null);
                String subsetHash = subset.toString();

                // Memoization -- SKip already processed subset and avoid cost of processing and duplication
                if (supersetHashes.contains(subsetHash) == false) {
                    // CHOOSE -- Add result to solution ( This is VALID solution) ..Add immediately
                    supersetHashes.add(subsetHash);// Track duplicates

                    // RECURSE--- Recursively call for next phases
                    // Pass cloned copy of partial set to avoid concurrent modification exception.
                    CORE_findAllSubsetsRecursively(inputSet, (ArrayList<Integer>) (subset.clone()), supersetHashes);
                }

                // BACKTRACK--- for exploring other options
                subset.add(elementChoice);
            }
        }
    }
}
