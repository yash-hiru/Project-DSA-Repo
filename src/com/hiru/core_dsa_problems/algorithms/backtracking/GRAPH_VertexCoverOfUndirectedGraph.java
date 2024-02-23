package com.hiru.core_dsa_problems.algorithms.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/**
 * VERTEX COVER OF GRAPH ==>
 * .......https://www.geeksforgeeks.org/introduction-and-approximate-solution-for-vertex-cover-problem/
 * .......A vertex cover of an undirected graph is a SUBSET (Hint-Minimize) of its vertices ,such that,
 * .......for every edge (u, v) of the graph, either ‘u’ or ‘v’ is in vertex cover.
 * .......Although the name is Vertex Cover, the set covers all edges of the given graph.
 *
 * ....... FINDING ==> GFG solution does NOT work for bipolar graph ( it should return output as 2 as [vPole1, vPole2] )
 */
public class GRAPH_VertexCoverOfUndirectedGraph {
    private static HashSet<Integer> minimalCoverVertices = new HashSet<>();

    /**
     * Main Driver method
     */
    public static void main(String args[]) {
        ArrayList<Integer>[] graph = new ArrayList[8];
        graph[0] = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        graph[4] = new ArrayList<>(Arrays.asList(0, 5, 6, 7));
        minimalCoverVertices.addAll(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        getVertexCover(graph.length, graph);
        return;
    }

    /**
     * Util Invoker with Initial state
     */
    public static void getVertexCover(final int V, ArrayList<Integer>[] graph) {

        for (int i = 0; i < graph.length; i++) {
            CORE_getVertexCoverUtil_APPROACH_BACKTRACKING(graph, V, i, new HashSet<Integer>(), new HashSet<Integer>());
        }

    }

    /**
     * Recursive Util Invoker with Initial state
     */
    public static void CORE_getVertexCoverUtil_APPROACH_BACKTRACKING(ArrayList<Integer>[] graph,
                                                                     int V,
                                                                     int v,
                                                                     HashSet<Integer> coverVertices,
                                                                     HashSet<Integer> reachableVertices) {
        if (reachableVertices.size() == graph.length) {
            if (coverVertices.size() < minimalCoverVertices.size()) {
                minimalCoverVertices = (HashSet<Integer>) coverVertices.clone();
            }
            // Found some solution ( May not be optimal)
            System.out.println("Found Cover: " + coverVertices);
            return;
        }

        // 1. Visit Cover Vertices
        coverVertices.add(v);

        // 2. Update Reachable vertices
        for (int i = 0; i < graph.length; i++) {
            if (graph[v] != null && graph[v].contains(i)) {
                reachableVertices.add(i);
            }
        }

        // Start exploring non visited vertices node
        for (int i = 0; i < graph.length; i++) {
            if (coverVertices.contains(i) == false) {
                CORE_getVertexCoverUtil_APPROACH_BACKTRACKING(graph, V, i, coverVertices, reachableVertices);
            }
        }
        // Backtrack:
        coverVertices.remove(v);
        for (int i = 0; i < graph.length; i++) {
            if (graph[v] != null && graph[v].contains(i)) {
                reachableVertices.remove(i);
            }
        }

    }

}
