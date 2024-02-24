package com.hiru.core_dsa_problems.datastructures.graph;

import java.util.LinkedList;
import java.util.Queue;

public class PATH_1_ShortestPath_SinglePair {
    public static void main(String[] args) {
        int graph[][] = new int[5][5];
        graph[0] = new int[]{0, 1, 0, 1, 0};
        graph[1] = new int[]{1, 0, 1, 1, 0};
        graph[2] = new int[]{0, 1, 0, 1, 0};
        graph[3] = new int[]{1, 1, 0, 0, 1};
        graph[4] = new int[]{0, 1, 1, 0, 0};

        // Outputs (3 is min): 0--1--3--4 (3) vs 0--1--2--3--4(4)
        System.out.println("SinglePairShortestPath Way to find route between <0,4>: " +
                singlePairShortestPathBFS(graph, 0, 4, "--" + 0, new boolean[graph.length]));
    }

    /**
     * Find Single Source shortest path to Dest. BFS or DFS
     *
     * Considerations >>>>>>>>>
     * 1. OVERALL LAYOUT ==> BFS with queue layout. Initial state with 0 vertex and while() loop. LinkedList as Queue
     * 2. LOCAL MINIMA ==> Keep track of LOCAL LENGTH (Solution)
     * 3. CONSTRAINTS ==> Only choose NON VISITED and ADJ nodes in subsequent processing
     * 4. TRACKING ==> 1)Mark nodes visited. 2)Update local Minima. 3)Add to Queue
     * 4. OPTIMAL SUB-STRUCTURE ==> Choose MIN of LOCAL LENGTH from adj vertices
     * 5. GLOBAL MINIMA and SUB-PROBLEMS ==> Include it in GLOBAL LENGTH
     * 6. RETURN ==> length -- ONLY IF when you were able to REACH DEST ....Else return -1 or Integer.MAX_VALUE(NO PATH)
     *
     * Related Problems >>>>>>>>
     * 1. Single Pair Shortest Path ( to all the vertices -Dijkstra Algorithm)
     * 2. All Pair shortest path. (Extension to Dijkstra algorithm)
     */
    public static int singlePairShortestPathBFS(final int[][] graph, int src, final int dest, String path, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();

        // PRE-PROCESSING ====> Start with first node
        // Start with first node.Visit and explore
        queue.add(src);
        visited[src] = true;

        int length = Integer.MAX_VALUE;
        boolean foundPath = false;

        // MID-PROCESSING ====> Explore all the levels and nodes
        // Iterate level by level
        while (queue.isEmpty() == false) {
            int s = queue.poll();
            // BASE CASE -- Happy case ( Reached to end ..return length)
            if (s == dest) {
                foundPath = true;
                break; // Avoid mistake of returning 0 since its NOT recursive method.
            }
            // OTHER CASE -- Explore adj of s node and find the min() length
            // Get Min cost of adj visit in current iteration
            int localLength = Integer.MAX_VALUE;
            for (int adj = 0; adj < graph.length; adj++) {
                // Found non visited neighbor
                if (graph[s][adj] == 1 && visited[adj] == false) {
                    // Calculate (length = current dist + recursive path)
                    localLength = Math.min(localLength, graph[s][adj]);
                    visited[adj] = true; // mark as visited
                    queue.add(adj);
                }
            }
            // Updates after current adj(s) processing
            if (localLength < Integer.MAX_VALUE) {
                if (length == Integer.MAX_VALUE) {
                    length = localLength; // First round
                } else {
                    length += localLength; // SubSequent rounds
                }
            } else {
                // return Integer.MAX_VALUE
            }
            // If localLength is MAX_VALUE which means we DID not reach end solution
            // return MAX_VALUE
        }

        // POSTPROCESSING ====> Check if path is present ..If yes we got MIN path?
        if (foundPath)
            return length;
        else
            return Integer.MAX_VALUE;
    }
}
