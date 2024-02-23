package com.hiru.core_dsa_problems.algorithms.backtracking;

import java.util.ArrayList;

/**
 * Detect hamilton Cycle in directed graph
 */
public class GRAPH_DetectHamiltonCycle {

    /**
     * MAIN method
     */
    public static void main(final String[] args) {

        // Prepare input
        int[][] graph = new int[8][8];
        graph[0] = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        graph[1] = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        graph[2] = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        graph[3] = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        graph[4] = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        graph[5] = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        graph[6] = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        graph[7] = new int[]{0, 0, 0, 0, 0, 0, 0, 0};

        TEST_insertEdges(graph, 0, new int[]{1, 7});
        TEST_insertEdges(graph, 1, new int[]{0, 2, 5});
        TEST_insertEdges(graph, 2, new int[]{1, 3, 4, 5});
        TEST_insertEdges(graph, 3, new int[]{2, 4});
        TEST_insertEdges(graph, 4, new int[]{2, 3, 5});
        TEST_insertEdges(graph, 5, new int[]{1, 2, 4, 6});
        TEST_insertEdges(graph, 6, new int[]{5, 7});
        TEST_insertEdges(graph, 7, new int[]{0, 6});

        // TEST
        detectAnyHamiltonCycle(graph);
    }


    /**
     * Core function to kickoff algorithm
     */
    public static void detectAnyHamiltonCycle(int[][] graph) {
        CORE_detectAnyHamiltonCycleRecurse(graph, 0, new boolean[graph.length], new ArrayList<Integer>());
    }

    /**
     * CORE FUNCTION---- Recursive function to detect all the hamilton cycles
     *
     * @param graph readonly parameter- Adjucency matrix representation
     * @param vertex current vertex to be visited
     * @param vertexVisitorState Global state of all the visited vertex at any given time
     * @param path Global state of tracking the path as when node gets visited
     *
     * @return True if cycle exist
     */
    public static boolean CORE_detectAnyHamiltonCycleRecurse(final int[][] graph,
                                                             int vertex,
                                                             boolean[] vertexVisitorState,
                                                             ArrayList<Integer> path) {
        // VALID BASE CASE==  SUCCESS-- Visited all the vertexes and reached to vertex0
        if (vertex == 0 && allVisited(vertexVisitorState)) {
            System.out.println("SUCCESS--- DETECTED HAMILTON CYCLE : [" + path.toString() + "]"); //Optionally add to master list<list>
            return true;
        }

        // VALID BASE CASE==  Vertex already visited before.
        if (vertexVisitorState[vertex]) {
            return false; // Let backtracking kickoff
        }

        // BACKTRACKING LOGIC== Explore choices
        // CHOOSE-- Update the state
        vertexVisitorState[vertex] = true; // Update visited state parameter
        path.add(vertex); // Update Cycle Path state parameter

        // RECURSE for subsequent states
        for (int neighbor = 0; neighbor < graph.length; neighbor++) {
            // If Neighbor
            if (graph[vertex][neighbor] == 1) {
                // Recursively visit the 'neighbor' and pass readonly and state parameters
                CORE_detectAnyHamiltonCycleRecurse(graph, neighbor, vertexVisitorState, path);
            }
        }

        // BACKTRACK-- Restore the state ( Reverse the action performed as part of making choice)
        vertexVisitorState[vertex] = false; // Update visited state parameter
        path.remove(path.size() - 1); // Update Cycle Path state parameter

        return false;
    }

    /**
     * Check if all the vertices are visited
     *
     * @param vertexVisitorState
     *
     * @return True if Yes
     */
    private static boolean allVisited(boolean[] vertexVisitorState) {
        for (boolean visited : vertexVisitorState) {
            if (!visited) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generate test data
     *
     * @param graph
     * @param vertex
     * @param neighbors
     */
    private static void TEST_insertEdges(int[][] graph, int vertex, int[] neighbors) {
        for (int neighbor : neighbors) {
            graph[vertex][neighbor] = 1;
            graph[neighbor][vertex] = 1;
        }
    }

}
