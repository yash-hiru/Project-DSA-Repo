package com.hiru.dsa.java.datastructures.matrix;

import java.util.ArrayList;

/**
 * INCOMPLTE-- Its harder than travelling salesman (Its harder problem)
 * Why ?
 * 1. Its not ADJ_MAT graph, its maze
 * 2. You need to start from cities unlike some node from ADJ_MAT
 *
 * Proposal: Find the min path pairwise. (OPEN QUESTION -- What if there are 2 min paths) TRICKY
 * Post process min path -- Mark 1 as 2
 * Find next city path
 * This approach WONT give optimal MIN solution
 */
public class __TBD__BACKTRACKING_ElectricityConnection {

    public static void main(String[] args) {

        // Input prep (1=Road, 0=Farm, -1 City)
        int[][] maze = new int[5][4];
        maze[0] = new int[]{0, 1, 1, -1, 0};
        maze[1] = new int[]{0, 0, 1, 1, 1};
        maze[2] = new int[]{0, 1, -1, 1, 1};
        maze[3] = new int[]{1, 1, 1, 0, 0};
        maze[4] = new int[]{-1, 1, 0, 1, 1};

        ArrayList<Coordinate> cities = new ArrayList<>();
        cities.add(new Coordinate(0, 3));
        cities.add(new Coordinate(2, 2));
        cities.add(new Coordinate(4, 0));

        for (int i = 0; i < cities.size() - 1; i++) {
            connectTwoCitites(maze, 5, 4, cities.get(i), cities.get(i + 1));
        }
        return;
    }

    static void connectTwoCitites(int[][] maze, int xMax, int yMax, Coordinate c1, Coordinate c2) {
        if (maze[c1.x][c1.y] == -1) {
            maze[c1.x][c1.y] = -2; // Connected City
        }
        if (maze[c2.x][c2.y] == -1) {
            maze[c2.x][c2.y] = -2; // Connected City
        }

        // 4 Initial States to be explored recursively
        if (c1.x - 1 >= 0)
            explore(maze, xMax, yMax, c1.x - 1, c1.y, c2);
        if (c1.x + 1 < xMax)
            explore(maze, xMax, yMax, c1.x + 1, c1.y, c2);
        if (c1.y - 1 >= 0)
            explore(maze, xMax, yMax, c1.x, c1.y - 1, c2);
        if (c1.y + 1 < yMax)
            explore(maze, xMax, yMax, c1.x, c1.y + 1, c2);
    }

    static void explore(int[][] maze, int xMax, int yMax, int c1x, int c1y, Coordinate c2) {
        ArrayList<Coordinate> path = new ArrayList<>();
        ArrayList<Coordinate> minPath = new ArrayList<>();
        boolean[][] visited = new boolean[xMax][yMax];
        connectTwoCititesUtils(maze, visited, 5, 4, c2, c1x, c1y, path, Integer.MAX_VALUE, minPath);
        fillPath(maze, minPath);
        // reset
        path = new ArrayList<>();
        minPath = new ArrayList<>();
    }

    static void fillPath(int[][] maze, ArrayList<Coordinate> minPath) {
        for (Coordinate c : minPath) {
            maze[c.x][c.y] = 100; // Electricity
        }
    }

    static void connectTwoCititesUtils(int[][] maze, boolean[][] visited, int xMax, int yMax, Coordinate c2, int i, int j, ArrayList<Coordinate> path, int min, ArrayList<Coordinate> minPath) {
        // Case1 -- valid solution (Either the city or electricity line)
        if ((i == c2.x && j == c2.y) || maze[i][j] == 100) {
            if (min > path.size()) {
                min = path.size();
                minPath.clear();
                for (Coordinate c : path) {
                    minPath.add(c);
                }
            }
            return;
        }
        // Road ? Add Road and explore all 4 possible options
        if (maze[i][j] == 1 && visited[i][j] == false) {
            // MAKE CHOICE
            path.add(new Coordinate(i, j));
            visited[i][j] = true;

            if (i - 1 >= 0) {
                connectTwoCititesUtils(maze, visited, xMax, yMax, c2, i - 1, j, path, min, minPath);
            }

            if (i + 1 < xMax) {
                connectTwoCititesUtils(maze, visited, xMax, yMax, c2, i + 1, j, path, min, minPath);
            }

            if (j - 1 >= 0) {
                connectTwoCititesUtils(maze, visited, xMax, yMax, c2, i, j - 1, path, min, minPath);
            }
            if (j + 1 < yMax) {
                connectTwoCititesUtils(maze, visited, xMax, yMax, c2, i, j + 1, path, min, minPath);
            }
            // MAKE CHOICE -- BACKTRACK
            path.remove(path.size() - 1);
            visited[i][j] = false;
        }
    }

    static class Coordinate {
        int x;
        int y;

        Coordinate(int i, int j) {
            x = i;
            y = j;
        }
    }
}
