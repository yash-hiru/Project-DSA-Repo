## ============= Common Coding Patterns ==============

### Background

Most of the problem belongs to some category or theme with minor tweaks viz. as Datatype, constraints, conditions,
names.

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

3. Once you confirm the category of the problem, **implement generic outline/template**  with TODOs within **no more
   than 5 mins**.

#### How this is useful ?

1. Helps to avoid unncessary struggle, embarrasement and hence time wastage during tight timelines (45 mins coding
   round)

2. Sets premise for further changes from generic to specific

3. Sets premise of time complexity and space complexity discussions

4. Boost confidence throughout interview

5. Reflects your strong analytical, coding, DSA skills

6. Gives more insights into DELTA changes required to solve given specific problem with minor tweaks.

## ===================== Themes =====================

---

### Dynamic Programming: 0-1 Knapsack (Sequencial Flows)

##### Unique Patterns

1. Find **Min , Max** , **Count** (Combinations ...Not permutations) or **exists**  outcome with given constraints

2. Usually **array** or **string** problems

3. Overlapping sub-problems ?

4. Optimal Substructure

##### Time Complexity

1. **Exponential** with O(1) space ==> **O (K^N)**

2. **Linear** or **quadratic** with extra O(N) or O(NxM) space ===> **O(N^K) ** k =1,2

##### Problem Description

Similar problems of **0/1 knapsack** (N, wt [], val[], W)

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

| Sr.No | Problem                                                           | Options  | Value (Impact Objective) | Constraints   | Repeat  | Options           | Num Children | Operator              | Base Case return value                |
| ----- | ----------------------------------------------------------------- | -------- | ------------------------ | ------------- | ------- | ----------------- | ------------ | --------------------- | ------------------------------------- |
| 1     | **0-1 Knapsack**                                                  | wt[N]    | val[]                    | W max weight  | No      | Yes/No            | 2****        | **MAX**(subproblems)  | val[N-1]                              |
| 2     | **Minimum number of Coins to get the V**                          | coins[N] | **1**                    | V value       | **Yes** | **FOR** (options) | **N**        | **MIN**(subproblems)  | 1                                     |
| 3     | **Subset  Sum (If Exist Any subset)**                             | set[N]   | **True/false**           | No Repeations | No      | Yes/No            | 2            | **OR**(subproblems)   | True                                  |
| 4     | **Subset  Sum (Count All such subsets)**                          | set[N]   | **SUM(counts)**          | No Repeations | No      | Yes/No            | 2            | **SUM (subproblems)** | count of all combinations e.g. 1, 2,3 |
| 5.    | **Cut Road to get Max profit**                                    | rod[N]   | **rod[i]**               | rod[N]        | **Yes** | **FOR** (options) | **N**        | **MAX**(subproblems)  | rod[N-1]                              |
| 6.    | Palindrome Partitioning- Find MIN number of palindrome partitions |          |                          |               |         |                   |              |                       |                                       |

##### Points to ponder !!!

- **Index Handling:**
    - Avoid overflows wherever you are ++ or -- index
    - Base conditions for bound checks
    - FOR OPTIONS EXPLORATION LOOPS: Pay attention to conditions, Start value for ( j= i+1)
- **Base conditions beyond bounds checks**:
    - Add other base conditions beyond just bound check - e.g. invalid W in knapsack,
- **Datatype and Overflows**
    - Choose long for int+int
    - MAX-1 value before incrementing it by 1. Or check overflow before it occurs.
    - Avoid overflow for INVALID state returns.
- **Memo:**
    - Pass memo to recursive calls
    - Pay attention to choose between 2D vs 1D.
    - Initialize it with EXTRA (+1) dimension.
    - Choose correct ( -1 if possible) INITIAL VALUE.
    - Add READ block before recursion. WRITE block inside FOR loop or A/B choices.
    - Check MAX_VALUE/ MIN_VALUE after read to avoid overflow
- **IF-Else vs IF type of choices**
    - Subsequnce -- C1 or MAX(C2,C3)
    - Substring -- MAX(C1,C2, C3)
- **Avoid simplification of conditions/base cases**
    - Ok to have redudency than missing any case in the process of over-simplification/coolness
    - OK to add separate if for separate case since return value per IF block could vary

##### Implementation

**0/1 Knapsack**

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

**Min Cuts Palindrome Partitioning**
(IMPT-- MUST remember this)

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

### Theme2:  Dynamic Programming Longest INCREASING Sub-sequence/Palindrome/Maze Path/Edit Distance

##### Unique Patterns

1. Min/Max/Longest/Smallest or count objectives
2. String/Array as inputs
3. Index arithmetic with 2/3/4 choices
4. Simpler than Knapsack BUT corner cases are tricky

##### Time Complexity

1. **Exponential** with O(1) space ==> **O (K^N)**

2. **Linear** or **quadratic** with extra O(N) or O(NxM) space ===> **O(N^K) ** k =1,2

##### Examples

- Longest Increasing SubSequence (2 choice)
    - Match vs NoMatch ```MAX [1+(i+1), (i+1)]```
- Longest Common Subsequence (1 vs 2 choices)
    - Match ```1 +(i+1, j+1)```
    - NoMatch ```MAX [(i, j+1), (i+1, j)]```
- Longest Common STRING (3 vs 2 choices)
    - Match ```MAX [1 +(i+1, j+1),  (i+1, j),  (i, j+1)]```
    - NoMatch ```MAX [(i+1, j),  (i, j+1)]```
- Longest Palindrome Subsequence (1 vs 2 choices)
    - Match ```2 +(i+1, j-1) ```
    - NoMatch ```MAX [(i+1, j-1),  (i, j-1)]```
- Longest Palindrome SubSTRING (3 vs 2 choices)
    - Match ```MAX [2 +(i+1, j-1),  (i+1, j-1),  (i, j-1)]```
    - NoMatch ```MAX [ (i+1, j-1),  (i, j-1)]```
- Edit distance ( 1 vs 3 choices)
    - Match```0+(i+1, j+1)```
    - NoMatch```MAX [(i+1, j+1) (i+1, j) (i, j+1)]```

##### Implementation

**Longest Palindrome Sub-STRING**

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

### Backtracking Patterns (Arrays)

### Maze Patterns (Arrays)

### Graph Patterns

#### BFS Skeleton

#### DFS Skeleton

### BST Patterns