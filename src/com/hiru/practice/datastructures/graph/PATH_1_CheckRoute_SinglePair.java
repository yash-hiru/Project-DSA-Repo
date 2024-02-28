package com.hiru.practice.datastructures.graph;

import java.util.LinkedList;
import java.util.Queue;

public class PATH_1_CheckRoute_SinglePair {

    public static void main(String[] args) {
        int graph[][] = new int[5][5];
        graph[0] = new int[]{0, 1, 0, 1, 0};
        graph[1] = new int[]{1, 0, 1, 1, 1};
        graph[2] = new int[]{0, 1, 0, 0, 1};
        graph[3] = new int[]{1, 1, 0, 0, 0};
        graph[4] = new int[]{0, 1, 1, 0, 0};

        System.out.println("DepthFirstSearch Way to find route between <0,4>: " + checkRouteDFS(graph, 0, 4, "--" + 0, new boolean[graph.length]));
        System.out.println("BreadthFirstSearch Way to find route between <0,4>: " + checkRouteBFS(graph, 0, 4, "--" + 0));

    }

    /**
     * Follow typical DFS. Recursively call same function by changing 'src'
     * if Adj nodes then invoke same for <adj, dest> pair
     * At some point, you will find some <src, dest> == 1, this indicates, you are one step closer
     * to dest and src at that stage is connected to dest.
     *
     * LEARNING ==> Track visited nodes to avoid exploring them again.
     */
    public static boolean checkRouteDFS(final int[][] graph, int src, final int dest, String path, boolean[] visited) {
        // Base case ===> Last stage where given src is connected to dest
        if (graph[src][dest] == 1) {
            System.out.println("DFS Path: " + path + "--" + dest);
            return true;
        }

        // RECURSE ===> Follow same process for adj nodes. treat adj as next src
        boolean hasPath = false;
        for (int adj = 0; adj < graph.length; adj++) {
            // Make 'adj' as 'src'
            // Make sure that, hasPath always return true
            if (graph[src][adj] == 1 && visited[adj] == false) {
                hasPath = hasPath || checkRouteDFS(graph, adj, dest, path + "--" + adj, visited);
                if (hasPath)
                    return true;
            }
        }
        // You came here with no LUCK. No PATH buddy.
        return false;
    }

    /**
     * BFS using queue.
     * Start from 'src'
     * Queue 'adj' nodes and see if 'adj' equals 'dest'
     * return true if adj == dest
     * for other cases, return false
     */
    public static boolean checkRouteBFS(final int[][] graph, int src, final int dest, String path) {
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> queue = new LinkedList<>();
        // Start with src
        queue.add(src);
        visited[src] = true;

        while (false == queue.isEmpty()) {
            //  queue.poll() returns OR adjacent nodes of previous lower levels
            src = queue.poll();

            // Get Adjacent Nodes of src
            for (int adj = 0; adj < graph.length; adj++) {
                // Check if you are already reached dest.Only for adj node for last stage
                if (adj == dest) {
                    // (adj is dest now)
                    return true; // Return Immediately
                }

                // Only for adj nodes
                if (graph[src][adj] == 1) {
                    if (visited[adj] == false) {
                        visited[adj] = true; // Traverse by marking adj node as visited
                        queue.add(adj); // enque() adj
                    }
                }
            }
        }
        // You came here with no LUCK. No PATH buddy.
        return false;
    }
}
