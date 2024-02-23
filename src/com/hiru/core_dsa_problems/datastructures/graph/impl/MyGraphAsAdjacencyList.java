package com.hiru.core_dsa_problems.datastructures.graph.impl;

import com.hiru.core_dsa_problems._commons.MyLogger;

import java.util.ArrayList;

/**
 * This is adjacency List representation of graph.
 * This DOES NOT take into account NEW VERTEX addition.
 * If you really need that, then consider representing
 * Graph as below:
 * [TODO- Just tag]
 * class WeightedGraph
 * {
 * static class Vertex {
 * String Name;
 * String someProperty;
 * }
 * static class Edge {
 * Vertex source;
 * Vertex Destination;
 * int weight;
 * String nameTag;
 * String someOther property;
 * }
 * HashMap<Vertex, List<Edge> graph; // All keys will get all Vertices..All values will give all edges
 * }
 * <p>
 * However we will use SIMPLIED adjacency list representation for weighted directed graph as below
 * Properties == Fixed number of vertices, No Weights, Directed
 */
public class MyGraphAsAdjacencyList {

    /////////////////// Fields //////////////////
    // Immutable Number of vertices.
    int numberOfVertices;

    // Implicit range of ID/index for vertices would be [0 to N-1]
    private int vertices;

    // List of lists for [0 to N-1] vertices
    private ArrayList<ArrayList<Integer>> adjacencyLists = new ArrayList<ArrayList<Integer>>();

    // TODO: We can have same adjacency list for weights and keep both lists in sync OR create edge object.

    //////////////////////////////// Basic functions ///////////////////////////////////////////////////
    public MyGraphAsAdjacencyList(int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        for (int i = 0; i < numberOfVertices; i++) {
            // This implicitly initializes Vertices too becoz those are in simplified (index) representation form.
            adjacencyLists.add(new ArrayList<>()); //Initialize adjacency list for each vertex
        }
    }

    public void addEdge(int source, int destination) {
        adjacencyLists.get(source).add(destination); //Override existing
    }

    public void addEdges(int source, int[] destinations) {
        for (int destination : destinations) {
            adjacencyLists.get(source).add(destination); //Override existing
        }
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }

    public ArrayList<Integer> getNeighborsOf(int source) {
        return adjacencyLists.get(source);
    }

    /////////////////////////// Complex functions ////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * PRACTICE_depthhFirstTraversal ( Time Complexity O(V+E))
     * Visit the node and DONT visit neighbors directly..Rather RECURSE directly.
     * <p>
     * This would work only for the connected undirected graph
     * For unconnected and directed graphs prefer to invoke dfs from ALL the vertices
     *
     * @param visited
     * @param vertex
     */
    public void PRACTICE_depthFirstTraversal(boolean[] visited, int vertex) {
        // Conditional recurse would end at some stage
        if (visited[vertex] == false) {
            visited[vertex] = true;
            MyLogger.info("DFS: Visiting ----: " + vertex);
            // Explore the neighbors
            ArrayList<Integer> neighbors = adjacencyLists.get(vertex);
            for (int index = 0; index < neighbors.size(); index++) {
                int neighborVertex = neighbors.get(index);
                PRACTICE_depthFirstTraversal(visited, neighborVertex);
            }
        } else {
            MyLogger.warn("DFS: Already Visited ----: " + vertex);
        }
    }

    /**
     * PRACTICE_breadthFirstTraversal ( Time Complexity O(V+E))
     * Visit the node AND ITS NEIGHBORS then recursively invoke the BFS on the neighbors.
     * <p>
     * This would work only for the connected undirected graph
     * For unconnected and directed graphs prefer to invoke dfs from ALL the vertices
     *
     * @param visited
     * @param vertex
     */
    public void PRACTICE_breadthFirstTraversal(boolean[] visited, int vertex) {
        // Conditional recurse would end at some stage
        if (visited[vertex] == false) {
            visited[vertex] = true;
            MyLogger.info("DFS: Visiting ----: " + vertex);
            // Explore the neighbors
            ArrayList<Integer> neighbors = adjacencyLists.get(vertex);
            for (int index = 0; index < neighbors.size(); index++) {
                int neighborVertex = neighbors.get(index);
                PRACTICE_breadthFirstTraversal(visited, neighborVertex);
            }
        } else {
            MyLogger.warn("DFS: Already Visited ----: " + vertex);
        }
    }


    //////////////////////////////////////////////////////////////////////////
    // MAIN: Driver Program
    //////////////////////////////////////////////////////////////////////////
    public static void main(String args[]) {
        MyGraphAsAdjacencyList graph = new MyGraphAsAdjacencyList(5);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 0);
        graph.addEdge(3, 0);
        graph.addEdge(3, 2);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);
        graph.addEdge(4, 2);

        /////////////////// Traverse PRACTICE_depthhFirstTraversal
        boolean[] visited = new boolean[graph.numberOfVertices];
        for (int v = 0; v < graph.numberOfVertices; v++) {
            graph.PRACTICE_depthFirstTraversal(visited, v);
        }
    }
}
