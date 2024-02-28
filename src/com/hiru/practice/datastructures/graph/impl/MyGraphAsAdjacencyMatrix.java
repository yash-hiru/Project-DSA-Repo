package com.hiru.practice.datastructures.graph.impl;

import com.hiru.practice.util.MyLogger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Adjacency Matrix representation of Directed Graph
 */

public class MyGraphAsAdjacencyMatrix {
    private final int numberOfVertices;
    // Fields
    private int[][] graph;

    //ctor
    public MyGraphAsAdjacencyMatrix(final int numberOfVertices) {
        this.numberOfVertices = numberOfVertices;
        this.graph = new int[numberOfVertices][numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            Arrays.fill(graph[i], -1);
        }
    }

    /**
     * O[1] Lookup for detecting and returning weight of edge for <source,destination>
     * Return value of -1 indicates NO edge.
     * Return 1 for <X,X> type of requests
     * </source,destination>
     *
     * @param source
     * @param destination
     *
     * @return
     */
    public int getEdge(int source, int destination) {
        // validate
        if (source < 0 || source >= numberOfVertices
                || destination < 0 || destination >= numberOfVertices) {
            throw new RuntimeException("Invalid Inputs");
        }
        // return weight
        return graph[source][destination];
    }

    /**
     * Set or add edge to graph in O[1] time; Override existing value.
     * </source,destination>
     *
     * @param source
     * @param destination
     *
     * @return
     * @ param weight
     */
    public void setEdge(int source, int destination, int weight) {
        // validate
        if (source < 0 || source >= numberOfVertices
                || destination < 0 || destination >= numberOfVertices) {
            throw new RuntimeException("Invalid Inputs");
        }
        // return weight (By default consider it as UNDIRECTED GRAPH)
        graph[source][destination] = weight;
        graph[destination][source] = weight;
    }

    /**
     * Get list of all the neighbours from source index vertex.
     *
     * @return int[]
     */
    public List<Integer> getNeighbours(int source) {
        if (source < 0 || source >= numberOfVertices) {
            throw new RuntimeException("Invalid Inputs");
        }
        List<Integer> neighbors = new ArrayList<>();
        for (int i = 0; i < numberOfVertices; i++) {
            if (graph[source][i] != -1 && i != source) {
                neighbors.add(i);
            }
        }
        return neighbors;
    }


    /**
     * Get the weight from path (total weight) from source to destination.
     * return -1 if no direct/indirect path exist.
     * return total_weight if SOME path exist
     * This can be optimized to return 1. Minimal Cost 2. Minimal Path 3. All possible paths...
     * @param source
     * @param destination
     */
    //    public abstract void getPath(int source, int destination);

    /**
     * Print graph
     */
    public void print() {
        System.out.println("========== Graph As 2D matrix");
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.println("Neighbours of [" + i + "]" + Arrays.toString(graph[i]));
        }
    }

    public static void main(String args[]) {
        MyGraphAsAdjacencyMatrix graph = new MyGraphAsAdjacencyMatrix(4);
        graph.print();

        //Insert edge
        graph.setEdge(1, 0, 10);
        graph.setEdge(1, 2, 20);
        graph.setEdge(1, 3, 30);
        graph.print();

        MyLogger.info("graph.getEdge(1,2):  " + graph.getEdge(1, 2));
        MyLogger.info("graph.getNeighbours(1):  " + graph.getNeighbours(1).toString());
    }
}
