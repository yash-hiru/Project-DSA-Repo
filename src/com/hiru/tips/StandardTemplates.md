## ======== Common Coding Patterns =======

### Background

Most of the problem belongs to some category or theme with minor tweaks viz. as Datatype, constraints, conditions, names.

Identifying the right category saves time, gives us skeleton and also boost confidence since end to end code is ready.

### How to approach

#### Groundwork

1. Read problem carefully

2. Identify datatype ( simpler is better)

3. Pefer simpler datatypes

4. Identify the multiple entities and relationships

#### Find matching category (For optimal solution you propose and buy in)

1. Translate problem into simpler words (remove unncessary confusing context)

2. See if if has close **resemblance with**  any of the **broader level category**  given below

3. Once you confirm the category of the problem, **implement generic outline/template**  with TODOs within **no more than 5 mins**. 

#### How this is useful ?

1. Helps to avoid unncessary struggle, embarrasement and hence time wastage during tight timelines (45 mins coding round)

2. Sets premise for further changes from generic to specific

3. Sets premise of time complexity and space complexity discussions

4. Boost confidence throughout interview

5. Reflects your strong analytical, coding, DSA skills

6. Gives more insights into DELTA changes required to solve given specific problem with minor tweaks.

---

## Themes

### Dynamic Programming- 0-1 Knapsack

##### Unique Patterns

1. Find **Min , Max** , **Count** (Combinations ...Not permutations) or **exists**  outcome with given constraints

2. Usually **array** or **string** problems

3. Overlapping subproblems ?

4. Optimal Substructure

##### Time Complexity

1. **Exponential** with O(1) space ==> **O (K^N)**

2. **Linear** or **quadratic** with extra O(N) or O(NxM) space ===> **O(N^K) ** k =1,2 

##### Problem Description

Similar problems of **0/1 knapsack** (N, wt [],  val[],  W)

- **Inputs, Constraints, Objective >>>>**:
  
  - **Num items:** N
  
  - **Available Options** ( wt[N] )
  
  - **Available Values** (val[N])
  
  - **Constraint(s)** : W is max weight of knapsack
  
  - **Objective:** MAX (profit)

- **Choices >>>>**
  
  - Choose/Dont Choose
  
  - Aggregate ( current state, [subproblem1, subproblem 2...])

- **Variable Arguments >>>>**
  
  - i ++
  
  - W - wt[i]

- **States >>>>**
  
  - Initial State: i== 0
  
  - Next State: i = i+1
  
  - Final State: Valid / Invalid 

- **Base Condition >>>>**
  
  - SAD --- If W exceeding after adding i
  
  - HAPPY --- W matches after adding i
  
  - HAPPY --- No more weights and W is still not filled

##### Examples

| Sr.No | Problem                                  | Options  | Value (Impact Objective) | Constraints   | Repeat  | Options           | Num Children | Operator              | Base Case return value                |
| ----- | ---------------------------------------- | -------- | ------------------------ | ------------- | ------- | ----------------- | ------------ | --------------------- | ------------------------------------- |
| 1     | **0-1 Knapsack**                         | wt[N]    | val[]                    | W max weight  | No      | Yes/No            | 2****        | **MAX**(subproblems)  | val[N-1]                              |
| 2     | **Minimum number of Coins to get the V** | coins[N] | **1**                    | V value       | **Yes** | **FOR** (options) | **N**        | **MIN**(subproblems)  | 1                                     |
| 3     | **Subset  Sum (If Exist Any subset)**    | set[N]   | **True/false**           | No Repeations | No      | Yes/No            | 2            | **OR**(subproblems)   | True                                  |
| 4     | **Subset  Sum (Count All such subsets)** | set[N]   | **SUM(counts)**          | No Repeations | No      | Yes/No            | 2            | **SUM (subproblems)** | count of all combinations e.g. 1, 2,3 |
| 5.    | **Cut Road to get Max profit**           | rod[N]   | **rod[i]**               | rod[N]        | **Yes** | **FOR** (options) | **N**        | **MAX**(subproblems)  | rod[N-1]                              |

##### Implementation

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
     * @return:       MAX value for given call.
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

        // TODO-- Memoization will go here

        // Explore choices (YES/NO)
        return Math.max(
                prof[i] + knapsack(prof, wt, size, W - wt[i], i + 1), //Choice1 -- Put item
                knapsack(prof, wt, size, W, i + 1) // Choice2-- Don't put item
        );
    }
}
```

#### Find MAX Max, Longest,Jumps, Cho (same as Min)

#### 

### Backtracking Patterns (Arrays)

### Maze Patterns (Arrays)

### Graph Patterns

#### BFS Skeleton

#### DFS Skeleton

### BST Patterns