## Representations

### 1. Adjacency Matrix Representation

- If MORE edges and LESS vertices

#### Data Structure

```java

class Graph {
    int V = 4; // Number of vertices
    int[][] directedGraph = new int[V][V];
    // Undirected graph will be SYMMETRICAL
}
```

#### Edge Representations

```java
void outline()
        {
// Directed Non Weighted
        graph[i][j]=1;
        graph[i][j]=0;

// Directed Non Weighted
        graph[i][j]=true;
        graph[i][j]=false;

// Directed Weighted
        graph[i][j]=5;
        graph[i][j]=0;

// UN-Directed Weighted
        graph[i][j]=1;
        graph[j][i]=1;
        graph[i][j]=0;
        }

```

#### Visited Vertices tracking

```java
class Graph {
    boolean[][] visited = new int[V][V];
    // Undirected graph will be SYMMETRICAL
}
```

### Adjacency List Representation

- If LESS edges and MORE vertices

#### Data Structure

```java
class Graph {
    int V = 4; //Num vertices
    ArrayList<ArrayList<Integer>> edges; // List of edges for ALL vertices
}
```

#### Edge Representation

```java
class Graph {
    private int V = 4; //Num vertices
    private ArrayList<ArrayList<Integer>> edges;

    public boolean isEdge(int i, int j) {
        // Refer 2 nested lists (1. edges superset and 2. vertex neighbors)
        return edges.get(i).contains(j);
    }

    public ArrayList<Integer> getNeighbors(int i) {
        // return inner list for i
        return edges.get(i);
    }

}
```

---

## Graph Traversal

### Depth First Search (DFS)

#### Basics

- PRINT(V0) => DFS(V1)..DFS(Vn)
- Recursive Way
- DFS with constraints and visited node tracking is typical DP problem solving way

#### Applications:

**1. Detect Cycle**

- Specialized DFS with tracking of visited nodes
- When we see a back edge or revisit the same vertex
- Specialized use case: Hamilton Cycle ( Cycle covering all vertices)

**2. Path Finding**

- Specialized DFS
- Start from *vStart*
- Use STACK to keep track of PATH
- Print Stack contents upon finding destination *vEnd*

**3. Topological Sorting**
![Alt text](https://media.geeksforgeeks.org/wp-content/uploads/20231016113524/example.png)

- Output (sample graph): ```5 4 2 3 1 0```

- Modified DFS using STACK... DON'T Print IMMEDIATELY
- ALGORITHM ==>
    1. CALL topological sorting for all its ADJACENT vertices ( and their adjacent vertices ..and son on)
    2. PUSH current vertex only after step#1.
    3. *By this time adjacent vertices are already pushed in stack before
       current one.*
    4. Finally, PRINT the contents of the STACK.

- IMPLEMENTATION ==>
  ```java
  class TopologicalSort {
            
     //---------------------------------- Driver
     public static void main() {
        topologicalSort(adjListgraph, V, visitedArr); 
     }
            
     //---------------------------------- Initial State
     public void topologicalSort(List<List> adjListgraph, boolean[] visitedArr) {
        // Initial state with UTIL invocation for ALL vertices
        for (int i = 0; i < V; i++)
          Stack stack = new Stack(); // STACK for Util
          if (visited[i] == false)
              topologicalSortUtil(i, adjListgraph, visitedArr, stack);
     }
            
     //---------------------------------- Recursive util
     public void topologicalSort(int u, List<List<Integer>> adjLists, boolean[] visitedArr, Stack stack)
        // Visit 
        visitedArr[i] = true;
           
        // for(int v: adjLists.get(u)){
           topologicalSort(v,....);
        }
           
        // Push to stack
          stack.push(u);
     }
  }
  ```
- Applications:
    - Scheduling
    - Data dependency
    - Logic synthesis
    - Evaluation of formulas
    - Resolve Compiler and Linker dependency

**4. Bipartite Graph Detection**

- ![Alt text](https://media.geeksforgeeks.org/wp-content/uploads/bipartitegraph-1.jpg)
- ALGORITHM ==>

```
- Use a color[2] array which stores 0 or 1 for every node which denotes opposite colors.
- CALL DFS from ANY node.
- IF (node u has NOT been VISITED previously)
  THEN 
      1. assign !color[v] to color[u]
      2. call DFS again to visit nodes connected to u.
- IF at any point ( color[u]  == color[v])
  THEN
    return FALSE (GRAPH is NOT BIPARTITE)
- MODIFY the DFS function such that it RETURN a BOOLEAN value at the end.
```

- Tip: Reverse 0 and 1 by ``` reversed = 1 - old```

**5. Detect Strongly Connected Graph**

- ![Alt text](https://media.geeksforgeeks.org/wp-content/uploads/20230731143017/exampledrawio.png "Strongly Connected Component")
- Definition: If there is a PATH(Not necessarily Edge) from EACH VERTEX in the graph TO EVERY OTHER VERTEX.
- ALGORITHM ( Brute Force Approach to find Stronly Connected Component(s)) ==>

```java
FOR each vertex i(which is NOT a part of any strongly component)
        FIND the VERTICES which will be the part of strongly connected component containing vertex i.Two vertex i and j will be in the same strongly connected component
        IF they there is a DIRECTED PATH from vertex i to vertex j and VICE-VERSA.
```

- [Implementation](https://www.geeksforgeeks.org/strongly-connected-components/)

**6. Solving puzzle with only one solution(e.g. Maze DP problems)**

- DFS can be adapted to find all solutions to a maze ,
  by only including nodes on the current path in the visited set.

**7. Web Crawlers**

- Depth-first search can be used in the implementation of web crawlers
  to explore the links on a website.

**8. Maze Generation**

- Generation of random Maze

**9. Backtracking**

- Backtracking- https://www.geeksforgeeks.org/introduction-to-backtracking-data-structure-and-algorithm-tutorials/
- Depth-first search can be used in backtracking algorithms.
- CHOOSE(i) ==> visited[i] = true
- BACKTRACK(i) ==> visited[i] = false

#### Pros of DFS:

- Linear memory requirement
- O(N*M) time complity
- Uses less memory since it only use storage for current path nodes ( less search space)

#### Cons of DFS:

- It may ENDUP STUCK in LEFTMOST down FOREVER
- Not guarantted to find solution ( HOW ??)
- And there is no guarantee to find a minimal solution, if more than one solution. (HOW ?)

### Breadth First Search (BFS)

#### Basics

![Alt Text](https://media.geeksforgeeks.org/wp-content/uploads/20221221015614/1-768.png)

- [Reference](https://www.geeksforgeeks.org/breadth-first-search-or-bfs-for-a-graph/)
- Starting from the root, all the nodes at a particular level are visited first
  and then the nodes of the next level are
  traversed till all the nodes are visited.
-

#### Applications

**1. Shortest Path and Minimum Spanning Tree for unweighted graph: (DFS or BFS)**

In an unweighted graph, the shortest path is the path with the least number of edges. With Breadth First, we always
reach a vertex from a given source using the minimum number of edges.
Also, in the case of unweighted graphs, any
spanning tree is Minimum Spanning Tree
and we can use either Depth or Breadth first traversal for finding a spanning
tree.

**2. Minimum Spanning Tree for weighted graphs:**

We can also find Minimum Spanning Tree for weighted graphs using BFT, but the condition is that the weight should be
non-negative and the same for each pair of vertices.

**3. Peer-to-Peer Networks:**

In Peer-to-Peer Networks like BitTorrent, Breadth First Search is used to find all neighbor nodes.

**4. Crawlers in Search Engines:**

Crawlers build an index using Breadth First. The idea is to start from the source page and follow all links from the
source and keep doing the same. Depth First Traversal can also be used for crawlers, but the advantage of Breadth First
Traversal is, the depth or levels of the built tree can be limited.

**5. Social Networking Websites:**

In social networks, we can find people within a given distance ‘k’ from a person using
Breadth First Search till ‘k’ levels.

**6. GPS Navigation systems:**

Breadth First Search is used to find all neighboring locations.

**7. Broadcasting in Network:**

In networks, a broadcasted packet follows Breadth First Search to reach all nodes.

**8. In Garbage Collection:**

Breadth First Search is used in copying garbage collection using Cheney’s algorithm. Breadth
First Search is preferred over Depth First Search because of a better locality of reference.

**9. Cycle detection in undirected graph (DFS or BFS):**

In undirected graphs, either Breadth First Search or Depth First Search can be
used to detect a cycle. We can use BFS to detect cycle in a directed graph also.

**10. Ford–Fulkerson algorithm:**

In Ford – Fulkerson algorithm, we can either use Breadth First or Depth First Traversal to
find the maximum flow. Breadth First Traversal is preferred as it reduces the worst-case time complexity to O(VE2).

**11. To test if a graph is Bipartite: (BFS or DFS)**

We can either use Breadth First or Depth First Traversal.

**12. Path Finding(DFS or BFS):**

We can either use Breadth First or Depth First Traversal to find if there is a path between two
vertices.

**13. Finding all nodes within one connected component: (DFS or BFS)**
We can either use Breadth First or Depth First Traversal to find
all nodes reachable from a given node.

**14. AI--Best Move Detection:**
In AI, BFS is used in traversing a game tree to find the best move.

**15. Network Security:**

In the field of network security, BFS is used in traversing a network to find all the devices
connected to it.

**16. Connected Component:**

We can find all connected components in an undirected graph.

**17. Topological sorting:(BFS or DFS)**

BFS can also be used to find a topological ordering of the nodes in a directed acyclic graph (DAG).

**18. Image processing:**

BFS can be used to flood-fill an image with a particular color or to find connected components of
pixels.

**19. Recommender systems:**

BFS can be used to find similar items in a large dataset by traversing the items’ connections
in a similarity graph.

**20. Other usages:**

Many algorithms like Prim’s Minimum Spanning Tree and Dijkstra’s Single Source Shortest Path use
structures similar to Breadth First Search.

#### Pros

- BFS will **never get trapped** exploring the useful path forever.
- **If** there is a **solution**, BFS will **definitely find** it.
- If there is **more than one solution** then BFS **can find the minimal one that requires less number of steps**.
- Low storage requirement – linear with depth.
- **Easily programmable**.
-

#### Cons

- The main drawback of BFS is its **memory requirement**.
  Since **each level of the graph must be saved** in order **to generate the next level**

---

## Graph Types

- **Null Graph**
    - Graph with NO EDGES
- **Undirected Graph**
    - <i,j> = 1 then <j,i> = 1
    - Popular choice for problem solving

- **Bipartisan Graph**
    - TWO groups of vertices
    - NO edge among SAME group vertices
    - Requires AT MOST **2 COLORS**

- **Cycle Graph (Even/Odd number of vertices)**
    - Max 1 IN and 1 OUT degree
    - Even ==> MIN **2 COLORS**
    - Odd ==> MIN **3 COLORS**

- **Cyclic Graph (Even/Odd number of vertices)**
    - At least ONE cycle.

- **Topological DAG**
    - DAG
    - At least ONE NODE with NO IN degree
    - Use cases: Dependency Resolution.

- **Multi-Level Graph**
    - Greedy way for MIN/MAX shortest path could be used
    - Constraint:  L(i) --ONLY EDGES -- L(i+1)

- **Island Pattern**
    - Unconnected Components
    - Requires MULTIPLE DFS/BFS to traverse all vertices

- **Planarity**
    - If it can be DRAWN ON a PLANE WITHOUT any edges crossing each other.

- **Tree Graph**
    - More restrictive Graph
    - Single start ( NO IN degree) vertex
    - ONLY ONE path from any vertex i to j
    - Connected and DAG
    - IN Degree of each node = 1 ( Single Parent)

---

## Other Aspects

### May or May not a Graph !

- Maze
- Chess
- Snake Ladder
- Matrix

### Time Complexities of Operations

- All Operations =>  O(V+E)
- Add Edge =>       Adj Matrix O(1)   ... Adj List O(1)
- Remove Edge =>    Adj Matrix O(1)   ... Adj List O(1)
- Init =>           Adj Matrix O(V*V) ... Adj List O(V)
- Transpose ( Reverse all edges ) =>    O(V + E)

### Tips

<TODO>
### References

- [ALL Learning Resources and problems => Graph DS and Algo](https://www.geeksforgeeks.org/graph-data-structure-and-algorithms/?ref=header_search)
- [Top-50-graph-coding-problems-for-interviews](https://www.geeksforgeeks.org/top-50-graph-coding-problems-for-interviews/amp/)



