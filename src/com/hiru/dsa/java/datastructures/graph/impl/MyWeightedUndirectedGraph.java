package com.hiru.dsa.java.datastructures.graph.impl;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * IMPORTANT -- Weighted Undirected Graph
 * Graph operations using disjoint sets Disjoint set graphs
 * OPERATIONS===
 * 1. Detect Loops in undirected weighted graph
 * 2. Find Minimum cost spanning tree
 * 3. Represent spanning tree (NOT ESSENTIALLY BINARY TREE OR BST) as array int [] parent = {-5, 1, 2, 1, -2, 3, 5}
 * Number at index i indicates parent vertex of given vertex i
 * -ve number indicates root or super parent node. ABS of -ve value means number of children
 * Higher the degree of root, faster will be the lookup time.
 */
public class MyWeightedUndirectedGraph {
    ///////////////////////////////////////////////////
    ArrayList<Edge> edges = new ArrayList<>(); // Better to maintain this for special

    ///////////////////////////////////////////////////
    public static class Edge {
        int startVertex;
        int endVertex;
        int weight;

        public Edge(int startVertex, int endVertex, int weight) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.weight = weight;
        }

        public int getStartVertex() {
            return startVertex;
        }

        public int getEndVertex() {
            return endVertex;
        }

        public int getWeight() {
            return weight;
        }
    }

    ///////////////////////////////////////////////////
    public MyWeightedUndirectedGraph(int numberOfVertices) {
    }

    public void addEdge(int startVertex, int endVertex, int weight) {
        // Add edge to collection
        edges.add(new Edge(startVertex, endVertex, weight));
    }

    public ArrayList<Edge> getEdges() {
        return this.edges;
    }


    ///////////////////////////////////////////////////
    public void detectCycles() {
        // Maintain list of disjoint sets and update them as and when we
        // find new edge
        ArrayList<HashSet> disjointSets = new ArrayList<HashSet>();

        // Process EACH edge one by one and Create disjoint sets
        for (Edge currentEdge : edges) {
            System.out.println("INFO: Edge : " + currentEdge.getStartVertex() + "--" + currentEdge.getEndVertex());
            // Get the edge and create/update existing disjoint set
            HashSet set1 = null;
            HashSet set2 = null;
            for (HashSet set : disjointSets) {
                if (set.contains(currentEdge.getStartVertex())) {
                    // Only start vertex
                    set1 = set;
                }
                if (set.contains(currentEdge.getEndVertex())) {
                    // Only start vertex
                    set2 = set;
                }
            }
            // CASE1--Not found ..Both null ( Create new set)
            // CASE2-- Found both sets (Separate-->Combine... Same--> Loop Detected)
            // CASE3-- Found only one set (Merge non found element to that set)
            // Check if we found EXISTING sets ?
            if (set1 != null && set2 != null) {
                if (set1 == set2) {
                    System.out.println(" CYCLES DETECTED !!!!!!!!!!!!!!!");
                } else {
                    // MERGE set2 into set1
                    set1.addAll(set2);
                    // REMOVE smaller set from list of sets
                    disjointSets.remove(set2);
                }
            } else if (set1 == null && set2 == null) {
                HashSet<Integer> newSet = new HashSet<>();
                newSet.add(currentEdge.getStartVertex());
                newSet.add(currentEdge.getEndVertex());
                disjointSets.add(newSet);
            } else {
                if (set1 == null) {
                    set2.add(currentEdge.getStartVertex());
                }
                if (set2 == null) {
                    set1.add(currentEdge.getEndVertex());
                }
            }
            System.out.println("Disjoint Sets==========");
            System.out.println(disjointSets.toString());
        }
    }

    ////////////////////////////////////////// TODO-- Implement it when possible
    public void getMinimumCostSpanningTree(ArrayList<Edge> edges) {
        // Follow same disjoint sets method
        // Sort edges by weights and keep on choose minimum weight edge (GREEDY APPROACH -- STICK TO SAME DECISION DURING ALL STEPS)
        // Maintain parent tracking for all the vertexes you visit 'int [] parent'
        // As part of combining two sets, make sure you update parents correctly
        // If you find loop (Edge from same disjoint set), IGNORE it since both vertices are ALREADY PART OF ONGOING TREE
        // Finish upon processing all edges
    }

    ///////////////////////////////////////////////////
    public static void main(String[] args) {
        MyWeightedUndirectedGraph graph = new MyWeightedUndirectedGraph(10);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 5);
        graph.addEdge(5, 6, 9);
        graph.addEdge(7, 8, 4);
        graph.addEdge(2, 5, 6);

        graph.addEdge(1, 2, 1);
        graph.addEdge(3, 4, 2);
        graph.addEdge(5, 7, 3);
        graph.addEdge(6, 8, 8);

        graph.detectCycles();
    }
}
