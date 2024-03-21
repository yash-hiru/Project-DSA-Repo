package com.hiru.dsa.java.datastructures.graph;

import java.util.LinkedList;

public class MaxFlow {

    public static void main(String args[]) {
        int[][] graph = new int[6][6];
        graph[0] = new int[]{0, 11, 12, 0, 0, 0};
            graph[1] = new int[]{0, 0, 12, 0, 0, 0};
        graph[2] = new int[]{0, 1, 0, 0, 11, 0};
        graph[3] = new int[]{0, 0, 0, 0, 0, 19};
        graph[4] = new int[]{0, 0, 0, 7, 0, 4};
        graph[5] = new int[]{0, 0, 0, 0, 0, 0};

        System.out.println(maxFlow(graph, 0, graph.length - 1));
    }

    public static int maxFlow(int[][] graph, int i, int sink) {
        if(i==0) {
            return 0;
        }
        if (i == sink) {
            return 0;
        }
        // Check if inflow <= outflow
        if(checkFlow(graph, i)) {

        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(i);
        while (queue.isEmpty() == false) {
            // Visit
            int u = queue.poll();
            int flow = 0;
            for (int v : graph[u]) {
                if (graph[u][v] > 0) {
                    flow = Math.max()
                }
            }
        }
    }

    private static boolean checkFlow(int[][] graph, int i) {

    }
}
