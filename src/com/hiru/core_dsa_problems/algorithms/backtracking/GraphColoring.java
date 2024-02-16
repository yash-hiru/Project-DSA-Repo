package com.hiru.core_dsa_problems.algorithms.backtracking;

import java.util.Arrays;

/**
 * Graph M coloring problem using backtracking
 * <p>
 * Learnings #######
 * 1. INPUT== Use adjacency MATRIX over adjacency list for QUICK solving problem within 45 mins
 * 2. INPUT== Keep graph of boolean for sake of ease
 * 3. INPUT== Use IMPLICIT vertices ids [0...N-1] for sake of quick coding
 * 4. INPUT== Use IMPLICIT colors[V] instead of HashMap.
 * 5. PROCESSING== Backtrack for ALL THE VALID choices
 * 6. PROCESSING== Check return value (true--> Don't backtrack and stop processing) to AVOID BACKTRACKING for valid solution.
 * 7. PROCESSING== Specific to this problem-- DON'T RELY UPON TOP SOLUTIONS OVER INTERNET
 * Those are failing for 2nd test cases onwards (8-)
 * I have fixed it by INTRODUCING ADDITIONAL CHECK FOR color of "neighbor's neighbor".
 */
public class GraphColoring {

    /**
     * MAIN METHOD -- Driver program
     *
     * @param args
     */
    public static void main(String[] args) {
        // Generate test data
        int numberOfVertices = 8;
        boolean[][] graph = new boolean[numberOfVertices][numberOfVertices];
        graph[0] = new boolean[]{false, true, false, false, false, false, false, false};
        graph[1] = new boolean[]{true, false, true, false, false, false, false, false};
        graph[2] = new boolean[]{false, true, false, true, false, false, false, false};
        graph[3] = new boolean[]{false, false, true, false, true, true, false, false};
        graph[4] = new boolean[]{false, false, false, true, false, true, true, false};
        graph[5] = new boolean[]{false, false, false, true, true, false, true, false};
        graph[6] = new boolean[]{false, false, false, false, true, true, false, true};
        graph[7] = new boolean[]{false, false, false, false, false, false, true, false};
        //Desired Solution ==>  [0,1,2,0,1,3,2,0]

        // Initialize result to be updated
        int[] colorsSolution = new int[numberOfVertices];
        Arrays.fill(colorsSolution, -1); //Initialize solution with some INVALID value ..-1 indicates Not Colored

        // TEST
        GraphColoring underTest = new GraphColoring();
        underTest.CORE_colorGraphRecursively(graph, numberOfVertices, 0, colorsSolution);
    }

    /**
     * CORE FUNCTION ==> Backtracking way of coloring each vertices
     * 1. Handle base case:
     * if (all vertices are colored):
     * return true
     * 2. FOR EACH Vertex:
     * 1. FOR (Iterate color choices)
     * 2. Check conflicts with Neighbor and neghboard neighbor
     * 3. CHOOSE color if SAFE
     * 3. Recurse for next vertex
     * 4. BACKTRACK if this PARTIAL solution does not work ( returned false from recursive call)
     * 5. DON'T BACKTRACK if internal recursive call returns TRUE
     *
     * @param graph
     * @param numberOfVertices
     * @param vertexId
     * @param solutionColors
     * @return
     */
    public boolean CORE_colorGraphRecursively(final boolean[][] graph,
                                              final int numberOfVertices,
                                              int vertexId,
                                              int[] solutionColors) {
        // BASE cases-- All vertices are colored
        if (vertexId == numberOfVertices) {
            // We got the solution. Let caller know the Success.
            return true;
        }

        // OTHER Cases-- RECURSION AND BACKTRACKING-- EXPLORE CHOICES
        // Choose color for vetex which is safe FOR NOW ( based on past state)
        for (int color = 0; color < numberOfVertices + 1; color++) {
            // CORE >>> CHECK CONFLICT (With Neighbor and neighbor's neighbor)
            if (this.isSafe(vertexId, color, graph, solutionColors)) {
                // CORE >>> MAKE CHOICE by choosing current color for current vertex
                solutionColors[vertexId] = color;
                // CORE >>> EXPLORE other vertices recursively ( DFS way) TBD-- ANy other way ?
                if (CORE_colorGraphRecursively(graph, numberOfVertices, vertexId + 1, solutionColors)) {
                    // SUCCESS-We had found some valid solution DEEP inside the hierarychy..Avoid backtracking in that case
                    return true;
                }
                // CORE >> BACKTRACK CHOICE by resetting color for given vertex
                solutionColors[vertexId] = -1;
            }
        }
        // return false if NONE of the choice from this partial solution resulted into desired final solution
        return false;
    }

    /**
     * Check if:
     * 1. Color conflicts directly with neighbor (Conflict for current vertex)
     * 2. Color conflicts with neighbor's neighbors (Conflict for neighbor vertex)
     * Check neighbor's neighbor too (learning)
     *
     * @param vertex         current vertex
     * @param color          chosen color
     * @param graph
     * @param colorsSolution
     * @return true if safe to use proposed 'color' for 'vertex'
     */
    private boolean isSafe(int vertex, int color, boolean[][] graph, int[] colorsSolution) {
        // Check conflict
        // Get the vertex and get the neighbors (DONE) -- CAN BE REFACTORED (TBD)
        for (int neighbor = 0; neighbor < graph[0].length; neighbor++) {
            // This is neighbor of vertex
            if (graph[vertex][neighbor]) {

                // NOT SAFE == Neighbor color has CONFLICT.
                if (colorsSolution[neighbor] == color) {
                    return false;
                }
                // NOT SAFE == UNSAFE for neighbor (IMPORTANT)
                // CHECK NEIGHBOR'S NEIGHBOR ( FOR VERY OLD UPDATED VERTICES and TWO WAY )
                // LEARNINGS ==> GFG solution DOES not work for this sample graph; it is missing this neighbors neighbor
                // color conflict check.
                for (int neighborNeighbor = 0; neighborNeighbor < graph[0].length; neighborNeighbor++) {
                    if (graph[neighbor][neighborNeighbor] && colorsSolution[neighborNeighbor] == color) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
