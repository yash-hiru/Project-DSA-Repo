package com.hiru;

import java.util.LinkedList;

public class DeleteMe {
    public static void main(String args[]) {
        int V = 5;
        LinkedList<Integer>[] adj = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adj[i] = new LinkedList();
        adj[2].add(0);
        adj[3].add(2);
        adj[3].add(1);
        adj[4].add(3);

        bfs(V, adj);
    }

    /**
     * @param visited
     *
     * @return
     */
    public static int allVisited(boolean[] visited) {
        int i = 0;
        for (boolean b : visited) {
            if (!b) {
                return i; // Return first found unvisited node
            }
            i += 1;
        }
        return -1; // All visited
    }

    static void bfs(int V, LinkedList<Integer>[] adj) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[V];

        // Unlike tree BFS(which starts from root), Graph BFS does NOT gurantee cover all the vertices.
        // E.g. Starting from sink node with no outdegree will lead to BFS for only just that node
        // Hence its extremely important to visit ALL the nodes BFS way UNTIL ALL are VISITED.
        int unvisited = 0;
        // -1 indicates that all nodes visited. Exit Main BFS
        while ((unvisited = allVisited(visited)) != -1) {
            // STEP: 1 >>>> PIck unvisited Node
            // STEP 2 >>>> BFS for unvisited node
            // STEP 3 >> Visit Node and Start local BFS by queuing it
            visited[unvisited] = true;
            queue.add(unvisited);

            //STEP 4 >>>> LOCAL BFS  >> Explore Adjacencies and add them to the queue and process all the
            System.out.println("Local BFS for : " + unvisited);
            while (queue.isEmpty() == false) {
                int u = queue.poll();
                System.out.println(u);
                // Get adjcency
                for (int v : adj[u]) {
                    if (visited[v] == false) {
                        visited[v] = true;
                        queue.add(v);
                    }
                }
            }
        }
    }
}
