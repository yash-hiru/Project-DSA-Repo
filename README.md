<span style="color:brown">

## 1. Coding Tips

#### Coding Tips

Most of the problem belongs to some category or theme with minor tweaks viz. as Datatype, constraints, conditions,
names.

Identifying the right category saves time, gives us skeleton and also boost confidence since end to end code is ready.

#### Problem Understanding and Finding Pattern

- Understand Problem by asking clarification questions
- Clarify assumptions
- Write examples..Don't get coaching..Be self driven
- Think a loud
- Objective and to the coord answers.
- 45 mins time is precious..DONT waste on irrelevant discussions.

#### Get Buy in for one of the multiple proposed appraoches

1. Translate problem into simpler words (remove unncessary confusing context)

2. See if if has close **resemblance with**  any of the **broader level category**  given below

3. Once you confirm the category of the problem, **implement generic outline/template**  with TODOs within **no more
   than 5 mins**.

#### Why this way ?

1. Avoid unncessary struggle, embarrasement and saves time

2. Sets premise for discussion viz. time complexity.

4. Boost confidence throughout interview due to ready layout.

5. Quickly demonstrates your strong analytical, coding, DSA skills.

6. Makes easier to make incremental changes on top of standard template algo.

#### During Coding Interview (45- 60 mins)

**Time Management**

```
[5 mins] Intro
[5 mins] Read
[10 mins] Discuss, Think Loud, Small Examples
[5 mins] Suggest approaches, Propose and get consensus on Optimal one
[15 mins --Google] [30 mins --Others] Implement at lightning speed(CRUICIAL)
[5 mins] Review, Edge cases, Other suggestions
[5 mins] Read

```

**DOs**

- Discuss approaches with tradeoff
- BE CONFIDENT on PROPOSAL and GET BUY IN for OPTIMAL approach.
- Think on generalizing (e.g. taking K for fixed set of inputs than hardcoding them)
- Trade offs with time-space complexities. Recurrent relation as applicable.
- Implement ONLY agreed upon approach. (uniterrupted 10 mins)
- Dryrun
- Edge case checks
- Discuss time and space complexity analysis
- Scalable and other optimal approaches if any

**Strict DONTs**

- DONT jump to coding WITHOUT consensus
- DONT Waste time on over-communication
- DONT add Code Comments, class and other framework( google)
- DONT choose Long variable names
- DONT Seek hints
- DONT Miss Overflows and validation checks
- DONT do early refactoring ( do it only if you have enough time)
- DONT assume NO PREPROCESSING such as sorting input.
    - **IMPLICIT ordering** if that affects the decision
        - e.g. ORACLE -- Trip-pickup-drop-tip oracle problem.
        - Its better to preprocess input (Sort by pickup in
          **TreeMap** ) and then explore it.
        - Explore next state recursively
          ```
            private static long getMaxEarnings(...,i) {
              for(key in TreeMap) {
                 if(drop(i) <= pickup(i)) {
                    // Recurse
                    getMaxEarnings(...j);
                 }
            }
            ``` 

---

## 1. Theme ==> Dynamic Programming (0-1 Knapsack)

#### Uniqueness

1. Find **Min , Max** , **Count** (Combinations ...Not permutations) or **exists**  outcome with given constraints

2. Usually **array** or **string** problems

3. Overlapping sub-problems ?

4. Optimal Substructure

#### Time Complexity

1. **Exponential** with O(1) space ==> **O (K^N)**

2. **Linear** or **quadratic** with extra O(N) or O(NxM) space ===> **O(N^K) ** k =1,2

#### Problem Description

Similar problems of **0/1 knapsack** (N, wt [], val[], W)

- Inputs, Constraints, Objective
    - Num items: N
    - Available Options ( wt[N] )
    - **Available Values (val[N])
    - Constraint(s) : W is max weight of knapsack
    - Objective: MAX (profit)
- Choices
    - IF NO REPEATATIONS -->  Choose/Dont Choose
    - IF REPEATATIONS --> FOR(options)
    - Aggregate ( current state, [subproblem1, subproblem 2...])
- Variable Arguments
    - i ++
    - W - wt[i]
- States
    - Initial State: i== 0
    - Next State: i = i+1
    - Final State: Valid / Invalid
- Base Condition
    - SAD --- If W exceeding after adding i
    - HAPPY --- W matches after adding i
    - HAPPY --- No more weights and W is still not filled

#### Examples

| Sr.No | Problem                                                           | Options  | Value (Impact Objective) | Constraints   | Repeat  | Options           | Num Children | Operator              | Base Case return value                |
| ----- | ----------------------------------------------------------------- | -------- | ------------------------ | ------------- | ------- | ----------------- | ------------ | --------------------- | ------------------------------------- |
| 1     | **0-1 Knapsack**                                                  | wt[N]    | val[]                    | W max weight  | No      | Yes/No            | 2****        | **MAX**(subproblems)  | val[N-1]                              |
| 2     | **Minimum number of Coins to get the V**                          | coins[N] | **1**                    | V value       | **Yes** | **FOR** (options) | **N**        | **MIN**(subproblems)  | 1                                     |
| 3     | **Subset  Sum (If Exist Any subset)**                             | set[N]   | **True/false**           | No Repeations | No      | Yes/No            | 2            | **OR**(subproblems)   | True                                  |
| 4     | **Subset  Sum (Count All such subsets)**                          | set[N]   | **SUM(counts)**          | No Repeations | No      | Yes/No            | 2            | **SUM (subproblems)** | count of all combinations e.g. 1, 2,3 |
| 5.    | **Cut Road to get Max profit**                                    | rod[N]   | **rod[i]**               | rod[N]        | **Yes** | **FOR** (options) | **N**        | **MAX**(subproblems)  | rod[N-1]                              |
| 6.    | Palindrome Partitioning- Find MIN number of palindrome partitions |          |                          |               |         |                   |              |                       |                                       |

#### Common Mistakes

- Index Handling:
    - Avoid overflows wherever you are ++ or -- index
    - Base conditions for bound checks
    - FOR OPTIONS EXPLORATION LOOPS: Pay attention to conditions, Start value for ( j= i+1)
- Base conditions beyond bounds checks:
    - Add other base conditions beyond just bound check - e.g. invalid W in knapsack,
- Datatype and Overflows:
    - Choose long for int+int
    - MAX-1 value before incrementing it by 1. Or check overflow before it occurs.
    - Avoid overflow for INVALID state returns.
- Memo:
    - Pass memo to recursive calls
    - Pay attention to choose between 2D vs 1D.
    - Initialize it with EXTRA (+1) dimension.
    - Choose correct ( -1 if possible) INITIAL VALUE.
    - Add READ block before recursion. WRITE block inside FOR loop or A/B choices.
    - Check MAX_VALUE/ MIN_VALUE after read to avoid overflow
- IF-Else vs IF type of choices:
    - Subsequnce -- C1 or MAX(C2,C3)
    - Substring -- MAX(C1,C2, C3)
- Avoid simplification of conditions/base cases:
    - Ok to have redudency than missing any case in the process of over-simplification/coolness
    - OK to add separate if for separate case since return value per IF block could vary

#### Implementation

1. 0/1 Knapsack

```java
class GFG {

    /**
     *
     * @param wt:     [FIX] weights (Affecting constraints and Objective)
     * @param val:    [FIX] values (Affecting objective)
     * @param N:      [FIX] Size of array
     * @param W:      [Variable] Reducing in recursive trees. 0 for valid leaf node.
     * @param i:      [Variable] Increasing in recursive tree forYes/No choice.
    [Optional] For problems when REPEATATIONS are ALLOWED
     * @return: MAX value for given call.
    MAX value for root level( 0 index) problem.
     */
    int knapsack(final int[] wt,
                 final int[] val,
                 final int N,
                 int W, // Reducing parameter
                 int i) {
        // Base case
        if (i == size) {
            return 0;
        }

        // Base case -- Constraints SKIP current and try next)
        if (wt[i] > W) {
            // Skip this one and try next item
            return knapsack(prof, wt, size, W, i + 1); // LEARNING --- DON'T return 0 BLINDLY.
        }

        // Memoization will go here

        // Explore choices (YES/NO)
        return Math.max(
                prof[i] + knapsack(prof, wt, size, W - wt[i], i + 1), //Choice1 -- Put item
                knapsack(prof, wt, size, W, i + 1) // Choice2-- Don't put item
        );
    }
}
```

2. Min Cuts Palindrome Partitioning (Complex Variant of Knapsack..MUST REMEMBER)

```java
class GFG {
    // Function to find the minimum number of cuts needed
    // for palindrome partitioning
    static int minPalPartition(String str, int i, int j) {
        // Base case: If the substring is empty or a
        // palindrome, no cuts needed
        if (i >= j || isPalindrome(str, i, j))
            return 0;

        int minCuts = Integer.MAX_VALUE;

        // Iterate through all possible partitions and find
        // the minimum cuts needed
        for (int k = i; k < j; k++) {
            int cuts = minPalPartition(str, i, k)
                    + minPalPartition(str, k + 1, j) + 1;
            minCuts = Math.min(minCuts, cuts);
        }

        return minCuts;
    }
}

```

---

## 2. Theme ==> Dynamic Programming(SubSequence, Palindrome, EditDist, Jumps)

#### Uniqueness

1. Min/Max/Longest/Smallest or count objectives
2. String/Array as inputs
3. Index arithmetic with 2/3/4 choices
4. Simpler than Knapsack BUT corner cases are tricky

#### Time Complexity

Same as knapsack.

#### Examples

1. Longest Increasing SubSequence (2 choice)
    - Match vs NoMatch ```MAX [1+(i+1), (i+1)]```
2. Longest Common Subsequence (1 vs 2 choices)
    - Match ```1 +(i+1, j+1)```
    - NoMatch ```MAX [(i, j+1), (i+1, j)]```
3. Longest Common STRING (3 vs 2 choices)
    - Match ```MAX [1 +(i+1, j+1),  (i+1, j),  (i, j+1)]```
    - NoMatch ```MAX [(i+1, j),  (i, j+1)]```
4. Longest Palindrome Subsequence (1 vs 2 choices)
    - Match ```2 +(i+1, j-1) ```
    - NoMatch ```MAX [(i+1, j-1),  (i, j-1)]```
5. Longest Palindrome SubSTRING (3 vs 2 choices)
    - Match ```MAX [2 +(i+1, j-1),  (i+1, j-1),  (i, j-1)]```
    - NoMatch ```MAX [ (i+1, j-1),  (i, j-1)]```
6. Edit distance ( 1 vs 3 choices)
    - Match ```0+(i+1, j+1)```
    - NoMatch ```1 + MAX [(i+1, j+1) (i+1, j) (i, j+1)]```

#### Implementation

1. Longest Palindrome Sub-STRING (Special)

```java
package com.hiru;

public class DeleteMe {
    public static void main(String args[]) {
        String str = "ZZAABAACFRTACA";
        //String str = "DDDAABAACRRRRRCZZ";
        int[][] memo = new int[str.length()][str.length()];
        System.out.println(longestPalindromeString(str, 0, str.length() - 1, memo));
        return;
    }

    static int longestPalindromeString(String str, int s, int e, int[][] memo) {
        if (e < s || s == str.length() || e == -1) {
            return 0; // No palindrome further
        }

        if (e == s) {
            // Odd palindrome mid
            memo[s][e] = 1;
            return memo[s][e];
        }

        // Explore choices
        int sol1 = -2, sol2 = -1, sol3 = -1;
        if (str.charAt(s) == str.charAt(e)) {
            if (s + 1 < str.length() && e - 1 >= 0) {
                sol1 = memo[s + 1][e - 1] = 2 + longestPalindromeString(str, s + 1, e - 1, memo);
            }
        }
        //Option2-3
        if (s + 1 < str.length()) {
            sol2 = memo[s + 1][e] = longestPalindromeString(str, s + 1, e, memo);
        }
        if (e - 1 >= 0) {
            sol3 = memo[s][e - 1] = longestPalindromeString(str, s, e - 1, memo);
        }

        return Math.max(sol1,
                Math.max(sol2,
                        sol3)
        );
    }
}


```

---

## 3. Theme ==> Graphs

#### ----- BFS (directed and undirected) -----

**Points to Ponder:**

- Representation:
  ```java
  class GFG {
      int V;
      List<Integer>[] adj = new LinkedList<Integer>[V];
  }
  ```
- For directed Graph:
    - Unlike tree BFS(which starts from root), Graph BFS does NOT gurantee cover all the vertices.
    - E.g. Starting from sink node with no outdegree will lead to BFS for only just that node
- For Undirected Graph:
    - Start from all the unvisited nodes if there are more than one components (islands)
- Queue:
    - ```LinkedList<Integer>``` as Queue
    - Useful methods: ```isEmtpty``` , ```add``` and ```poll```

**Implementation:**

1. Directed and Undirected BFS appraoches

```java
class GFG {

    /**
     * BFS for any DIRECTED graph..Even Null Graph too.
     * UTIL method ==> allVisited(visited)) returns -1 if ALL visited or return any unvisited node index.
     *
     * @param V Number of Nodes of graph
     * @param adj List<Integer> [] adjacency List of graph
     */
    static void bfsForDirected(int V, LinkedList<Integer>[] adj) {
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
                //STEP 4.1 >>>> Loop through adjancent nodes of u
                for (int v : adj[u]) {
                    //STEP 4.2 >>>> Visit and add unvisited nodes.
                    if (visited[v] == false) {
                        visited[v] = true;
                        queue.add(v);
                    }
                }
            }
        }
    }


    /**
     * BFS for any CONNECTED UNDIRECTED graph. (Single component)
     *
     * @param V Number of Nodes of graph
     * @param adj List<Integer> [] adjacency List of graph
     */
    static void bfs(int V, LinkedList<Integer>[] adj) {
        LinkedList<Integer> queue = new LinkedList<>();
        boolean visited[] = new boolean[V];

        // Start from ANY vertex since its UNDIRECTED CONNECTED graph.
        int startVertex = 3;
        visited[startVertex] = true;
        queue.add(startVertex);

        // BFS  >> Explore Adjacencies and add them to the queue and process all the
        while (queue.isEmpty() == false) {
            int u = queue.poll();
            System.out.println(u);
            //STEP 4.1 >>>> Loop through adjancent nodes of u
            for (int v : adj[u]) {
                //STEP 4.2 >>>> Visit and add unvisited nodes.
                if (visited[v] == false) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
    }
}
```

#### ----- Paths and Connectivity ----

**Implementations:**

1. Transitive Closure: (Adj Matrix way)

```java
class Graph {
    /**
     * Time complexity -- O(N3) 
     */
    int[][] transitiveClosure() {
        int reach[][] = new int[V][V]; // STEP1: Additional output data structure (reach[][] = Path exist=1, path no exist=0)
        // STEP2: O(N^3) combinations of 3 vertices hence 3 for loops
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                for (int k = 0; k < V; k++) {
                    // STEP3: Update Transitive connectivity/Path from i to j
                    // If {i,j} path exist OR {i,k} and {k,j} path exists ? ==> update {i,j} = 1 (Some path exist from i to j) 
                    reach[i][j] =
                            (reach[i][j] != 0) ||
                                    ((reach[i][k] != 0) && (reach[k][j] != 0)) ? 1 : 0;
                }
            }
        }
        // STEP4: Return the output
        return reach; // return transitive closure
    }
}

```

2. Find Connected Components/Islands in Undirected Graph

```java
class GFG {
    /**
     * Find Number of Islands in Maze/Grid/Graph
     * @param maze
     * @return num islands
     */
    int getNumIslands(int[][] maze) {
        int numberOfIslands = 0;
        // For each Vertex (i,j)
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if (maze[i][j] == 1 && visited[i][j] == false) {
                    // DO BFS/DFS ( Explore U-D-L-R for maze and adj for graph)
                    BFS(maze, i, j); // (OR DFS would also work)
                }
            }
        }
    }
}

```

3. Detect Cycle in graph

```java
class GFG {
    /**
     * Check cycles originating from ALL nodes DFS way
     * @return
     */
    boolean isCyclic() {
        // STEP 1 >>> Intialize Boolean OBJECT array for visited node tracking
        Boolean visited[] = new Boolean[V]; // Pass by reference
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // STEP 2 >>> Call the recursive helper function to detect cycle in different DFS trees
        for (int u = 0; u < V; u++) {
            // STEP 3 >>> Don't recur for u if already visited
            if (!visited[u])
                if (BFS_isCyclicUtil(u, visited, -1))
                    return true; // STEP 4 >>> Return true if we find cycle in this subtree 
        }
        return false; // STEP 5: Return false if we DID NOT find ANY cycle from ANY node
    }

    //////////////////////////////////////////////////////////////////

    /**
     * Detect cycle from each of the start node recursively
     */
    boolean BFS_isCyclicUtil(int u, Boolean visited[],
                             int parent) {
        // Mark the current node as visited
        visited[u] = true;
        Integer i;
        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> it = adj[u].iterator();
        while (it.hasNext()) {
            i = it.next();
            // NON VISITED ADJ --> Recur 
            if (!visited[i]) {
                if (BFS_isCyclicUtil(i, visited, u))
                    return true;
            }
            // VISITED ADJ -->  
            else {
                // If an adjacent is visited and not parent of current vertex, 
                // then there is a cycle.
                if (i != parent)
                    return true;
            }
        }
        return false;
    }
}

```

#### ----- Topological Sorting -----

- Extremely important for compiler, dependency and job sequencing
- Pseudocode:
-

```java
class GFG {
    /////////////////////// (Driver code)
    public void topologicalSort(int V, List<List> adj) {
        // STEP1: Assume unconnected graph hence multiple FOR(DFS)
        boolean[] visited = new boolean[V];
        Stack stack = new Stack(); // java.utils.colections
        // STEP2: Push the element in PECULIER order (LIFO-DFS) to stack
        for (int u = 0; u < V; u++) {
            DFS_topologicalSort_UTIL(V, adj, visited, stack, u);
        }
        // STEP3: Print elements of stack, thats it (LIFO-- Deepest child node will get printed first) (Works for islands too)
    }

    ///////////////////////// DFS (Tweaked)
    public void DFS_topologicalSort_UTIL(int V, List<List> adj, boolean[] visited, Stack stack, int u) {
        // STEP1: Visit element
        visited[u] = true;

        // STEP2: DFS adj nodes (
        for (int v = 0; v < adj.size(); v++) {
            DFS_topologicalSort_UTIL(V, adj, visited, stack, v);
        }
        // STEP3: Push current node to Stack(after adj)
        stack.push(u);
    }
}
```

Todo

#### ----- Flows and Fills -----

Todo

#### ----- Spanning Trees, Connect Multiple, Shortest paths -----

Todo

#### ----- Coloring -----

Todo

---

## 4. Theme ==> Algorithms (Backtracking)

#### Uniqueness

- Typical question: Find Permutations (Not combinations)
- Types:
    - Decision ==> ```See if``` solution exist
    - Optimization ==> ```Find Optimal``` Solution (Any)
    - Enumeration ==> ```List all``` the solutions
- Not solvable by DP and greedy
- Incrementatlly build candidate using DFS and take following decision on partial candidate
    - SUCCESS (Found Solution-Leaf node)
    - ABANDON (Backtrack)
- Aka. Optimal Brute force

#### Time Complexity

- DP < Backtracking < BruteForce
- O(N^k) **<** O(k^N) / O(N!) **<** O(N^N)

#### General Outline (Pseudocode)

```java
class GFG {
    void FIND_SOLUTIONS(CURRENT_STATE, IN_OUT_PARAMETERS) {
        //---------------- Exit case or base case
        if (VALID_SOLUTION(IN_OUT_PARAMETERS)) {
            // --- Some recursive path ends to DESIRED STATE
            STORE / RETURN True
            return;
        }
        //---------------- Iterate over all the choices
        for (CHOICE in ALL_CHOICES) {
            // Filter out valid choice before recursive call for next state 
            if (VALID(CHOICE)) {
                //----- We have found some valid choice for next state (phase)
                APPLY(CURRENT_STATE, CHOICE);
                //----- Confirm this choice assuming next state would lead to final solution
                FIND_SOLUTIONS(NEXT_STATE, IN_OUT_PARAMETERS);
                //----- Desicion Problem: Return immediately if we know at least 1 solution was found.
                //----- Backtrack if we dont see next state leading to the solution
                // [***Optinal IF***] -- Not applicable for lets say subsets
                if (IF subproblem lead to INVALID/UNDESIRED state){
                    // ----- Abandon the subproblem 
                    // ----- we undo certain assignments of values to variables to reassign them to other possible values, see if those lead to a valid solution.
                    BACKTRACK(remove(CURRENT_STATE, CHOICE))
                    return;
                }
            }
        }
    }
}
```

#### Known Problems (Remember list)

Maze:

- Longest Possible Route in a Matrix with Hurdles
- Rat in maze
- Chess N Queen
- Chess Knight tour
- Suduko
- Work Break

Arrays:

- Partition of a set into K subsets with equal sum
- Combination Sum
- Palindrome Partitioning
- All subsets
- All permutations of string
- All permutations of Phone Letter digits

Graph:

- M-coloring problem
- Find hamilton Cycles (If exist, count and print all)
- Print all longest common sub-sequences in lexicographical order

---

## 5. Theme ==> Data Structures (Trees)

#### 1. Mode of Communication:

1. Return value from the child
2. Passed value from parent and older ancestors
3. IN/OUT parameter to keep track of node's path and state

#### 2. Traversal (Choose it wisely):

1. PreOrder: If you want to do Something "FIRST" before trying out left and right subtree
2. Inorder: For sorting and L--R traversal
3. Reverse Inorder -- For K largest Element
4. PostOrder: If you want to explore LEFT and RIGHT and take decision for ROOT

#### 3. Useful DataStructures:

1. ArrayList or ArrayList<ArrayList> (Path tracking with backtracking technique)
2. Stack: If you need to process the state in LIFO manner
3. Queue: For level order traversal
4. Multiple stacks: Zig zag

#### 4. Nested traversals

**Yes** you would need in in most of the problems
(if you want to take decision on PREVISITED subtree based on other subtree's/Parent's return state.)
e.g. ```BST_BurnTree```

#### 5. Interesting Problems

* Burn Tree ([GFG Link](https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/amp/))

```java
public class BST_BurnTree {

    public static void main(String args[]) {
        MyBinarySearchTree bst = new MyBinarySearchTree();
        bst.insertAll(new int[]{10, 5, 4, 7, 20, 16, 15, 17, 22, 24});
        burn(bst.root, 16);
    }

    /**
     * 1. Preorder traversal
     * 2. Special treatment for found node
     * 3. How to spread to parent (communicate with parent and siblings)
     * 4. How to spread further (Multiple preOrder VISITS for SOME nodes)
     * GFG : https://www.geeksforgeeks.org/burn-the-binary-tree-starting-from-the-target-node/amp/
     * My solution is simpler and space+time efficient than GFG
     *
     * @return True(burned) false(did not find hence did not fired tree)
     */

    public static boolean burn(MyNode node, int s) {
        //====== Preorder-- Invalid Case Check ======//
        if (node == null) {
            return false;
        }
        MyLogger.info("Visit: " + node.getData()); // VISIT TYPE1 -- Node was visited in find operation (FOR ALL)
        //====== Preorder-- Check and Visit ======//
        if (node.getData() == s) {
            // Found the trigger; Burn it and its both subtrees
            // Part1-- Burn this node and L,R subtrees
            burnDown(node);
            // Let immediate caller know that fire has started
            return true;
        }

        //====== Preorder-- LEFT Recurse ======//
        if (burn(node.getLeft(), s)) {
            // Fire came from immediate left since it returned true
            //Burn self
            MyLogger.info("Burn: " + node.getData());
            // Burn RIGHT subtree and return true(TRUE means FIRE)
            burnDown(node.getRight());
            return true;
        }

        //====== Preorder-- RIGHT Recurse ======//
        if (burn(node.getRight(), s)) {
            // Fire came from immediate right since it returned true
            //Burn self
            MyLogger.info("Burn: " + node.getData());
            // Burn LEFT subtree and return true(TRUE means FIRE)
            burnDown(node.getLeft());
            return true;
        }
        // Trigger was not found anywhere; Just return false ( No need to burn this tree)
        return false;
    }

    /**
     * Another Preorder function for special operation
     * @param node Subtree root to be processed
     */
    private static void burnDown(MyNode node) {
        if (node == null) {
            return;
        }
        MyLogger.info("Visit(Again): " + node.getData()); // VISIT TYPE2 -- Node was visited AGAIN for burning (FOR FEW)
        //Preorder (root, left, right)
        MyLogger.info("Burn: " + node.getData());
        burnDown(node.getLeft());
        burnDown(node.getRight());
    }
}

```

#### BST

- Use recursion
- InOrder, PreOrder, PostOrder DFS
- BFS using queue.
- Decisions==> based on input and output returned by subtree calls
- Pass parameters by val/ref to children calls
- Split problems into parts (e.g. Border traversal)

#### BT

- Traverse using recursive DFS or queue based BFS.
- Decisions==> based on input and output returned by subtree calls
- Pass parameters by val/ref to children calls
- Split problems into parts (e.g. Border traversal)

#### TRIE

- ```Trie Node is ARRAY of 26 TRIE nodes.....and....tree level==string char index```
- Distinct element finding
- Optimal lookup
- Node Structure:
    ```java
    class Node {
        Node[] keys = new Node[26]; //Alphabets    
    }
- ```ch = str.charAt(i)``` <-->  ```trieLevel(i).key[ch-'a']```

#### Heap

- Array implementation is common
- ```iLeftChild = 2*iParent + 1``` and ```iRightChild = 2*iParent*2```
- ```iParent = floor(iChild/2)```
- Min-Max heap types
- Common use cases:
    - min+max heap for median of infinite stream
    - Heap for K-largest

---

## 6. Theme ==> Arrays and Strings

##### Points to ponder

**Index:**

- **Loops**: Forget to increment index, index value after full loop
    - BFS loop: ```Check element if visited before before queueing or recursing```
- **Index arithmetic**:  -- and ++ and bounds
- **Optimization**: Inputs Index bounds checks before exploring directions
- **2D index**: We need it in queue, passing across calls, store in list
    - Have this class handy for maze/matrix/graph problems.
      ```java
        static class Point{
            public Point(int x, int y) {
                this.x =x;
                this.y = y;
            }
            int x;
            int y;
        }
        ```

**DS/Maze:**

- Parameters: ```<arr, mm, nn, i, j>```
- Directions:
    - Replicated logic with slight for and if conditions
    - Max directions: **4 or 8**
- Graph (Maze):
    - Graph is NOT necessarily maze but maze is graph; Hence graph problems are applicable for MAZE too.
    - Graph Adj matrix is DIFFERENT than maze representation
    - In maze, graph has by default MIN 3 or MAX 8 adj nodes with 1 as default implicit distance.

---

## 6. Theme ==> Queue

##### Points to ponder

- Inbuilt Queue: ```LinkedList<Integer> queue = new LinkedList<>();```:
    - Enque/ADD: ```list.add(e);```
    - Deque/POLL: ```int e = list.poll();```
    - PEEK (Gets but DONT removes): ```int e = list.peek(e);```

--- 

# Internal Repositories

- [Topic Wise Notes](https://github.com/yash-hiru/Project-DSA-Repo/tree/main/src/com/hiru/dsa/md)
  </span>