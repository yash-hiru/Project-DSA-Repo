package com.hiru.dsa.java.datastructures.graph;

import java.util.Arrays;

/**
 * CHECK IF GRAPH IS BIPARTILE
 * GFG >>> https://www.geeksforgeeks.org/bipartite-graph/amp/
 */
public class _TODO_CheckIfBipartile {
    private static int minColor = Integer.MAX_VALUE;

    public static void main(String args[]) {
        // bipartile
        boolean[][] bipartileGraph = new boolean[5][5];
        bipartileGraph[0] = new boolean[]{false, false, true, false, true};
        bipartileGraph[1] = new boolean[]{false, false, true, false, false};
        bipartileGraph[2] = new boolean[]{true, true, false, false, false};
        bipartileGraph[3] = new boolean[]{false, false, true, false, true};
        bipartileGraph[4] = new boolean[]{true, false, false, true, false};

        // Initialize result to be updated
        int[] colorsSolution = new int[5];
        Arrays.fill(colorsSolution, -1); //Initialize solution with some INVALID value ..-1 indicates Not Colored

        CORE_graphMinColor(bipartileGraph, 5, 0, colorsSolution);
        System.out.println("Colored graph using: " + Arrays.toString(colorsSolution));


        if (minColor + 1 == 2) {
            System.out.println("Graph is BIPARTILE");
        } else {
            System.out.println("Graph is NOT BIPARTILE");
        }
    }

    public static void CORE_graphMinColor(final boolean[][] graph,
                                          final int numberOfVertices,
                                          int vertexId,
                                          int[] colorsSolution) {
        // BASE cases-- All vertices are colored
        if (vertexId == numberOfVertices) {
            System.out.println("Color Solution: " + Arrays.toString(colorsSolution));
            // We got the solution. Let caller know the Success.
            // Find the min colors solution count.
            int maxCOlorId = 0;
            for (int i = 0; i < colorsSolution.length; i++) {
                maxCOlorId = Math.max(maxCOlorId, colorsSolution[i]);
            }
            minColor = Math.min(maxCOlorId, minColor);
            return;
        }

        // OTHER Cases-- RECURSION AND BACKTRACKING-- EXPLORE CHOICES
        // Choose color for vetex which is safe FOR NOW ( based on past state)
        for (int color = 0; color < 2; color++) {
            // CORE >>> CHECK CONFLICT (With Neighbor and neighbor's neighbor)
            if (isSafe(vertexId, color, graph, colorsSolution)) {
                // CORE >>> MAKE CHOICE by choosing current color for current vertex
                colorsSolution[vertexId] = color;
                // CORE >>> EXPLORE other vertices recursively ( DFS way) TBD-- ANy other way ?
                CORE_graphMinColor(graph, numberOfVertices, vertexId + 1, colorsSolution);
                // CORE >> BACKTRACK CHOICE by resetting color for given vertex
                colorsSolution[vertexId] = -1;
            }
        }
        // return false if NONE of the choice from this partial solution resulted into desired final solution
        return;
    }

    private static boolean isSafe(int vertex, int color, boolean[][] graph, int[] colorsSolution) {
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
