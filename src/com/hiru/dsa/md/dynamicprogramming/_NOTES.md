## Dynamic Programming

---

### Overview

#### Background

1. **Recursion is parent** wheras **DP, Divide and Conquer and Dynamic Programming** are **children**
2. Recursion with memory
3. Best of both worlds **Accuracy of Brute force** and **Efficiency of Greedy**

#### Use Cases

1. Searching **Finding optimal solution(s)**
2. Count **Total number of Solutions(Need not to optimal) Solutions** to given problem.

```DP is NOT restricted to optimization problems. ```

---

### How to Approach ?

#### Identification of DP problem ==>

- Requirements (All requirements should match):
    - [MUST HAVE] **Overlapping sub-problems** -- Solving same subtask time and again
    - [NICE TO HAVE] **Optimal Substructure** -- Optimal solution to a problem is related to an optimal solution to a
      smaller problem
      within the same problem definition.
    - [MUST HAVE]**At least one solution exist** which could not solved using Greedy way.
    - [MUST HAVE]**Greedy way not possible** Prefer greedy before DP if possible.
    - [MUST HAVE] Problem time complexity **can not be reduced without using O(N) space**

#### Ground Work ==>

We have established that **This IS A DP problem**. Now start solving it.
Start with defining and elaborating these concepts:

**Define Objective** :

- ```Fact (mostly number)``` to be maximized/minimized

**Identify Inputs**: Inputs to algorithm

1. Immutable(Constant Inputs)
2. Mutable(Output parameter-result)
3. Converging(increase/decrease) input across diffrent states (objective)

**Identify States**:

```Processing 1 input at 1 time in 1 recursive call is the way forward ```

- **Current State**: ```Recursive/Iterative call``` for ```current``` snapshot of mutable and immutable parameters
- **Next State**: ```Sub-problems```(with their own set of input params) emerging from ```each``` of ```choices```
  made at current choice.
- **Base case**: State ```without``` any further sub-problems. Recursion ```ends``` here;
  iteration ```starts/ends``` here.

**Identify choices during each state**:

```1. Binary/Ternary/Finite choices.```

- Filter out Invalid choices beforehand
- Solve choice C1, C2, C3...as sub-problems recursively. Make sure to modify inputs to these subproblems.
- Choose either of sub-solution based on criteria viz. MIN/MAX/etc.
- e.g.
    - MAX (val[C1] + Func(C1), val[C2] + Func(C2))
    - 1+ MIN(Func(C1) ,Func(C2))

```2. Variable set of choices:```

- Follow same logic but in for loop ( including filters)
- Define optimal outcome outside for loop and revise it as needed
- Exit for loop and return the optimal outcome for this call
- **Sub Problem(Nested states)**: Recursive calls for next set of inputs. ```Same problem definition``` emerging with
  same goal but for ```reduced input```.
- **Single Problem** -- *Unique* combination of *mutable* input arguments
- **Memoize** -- Dont solve *repeated/overlapping sub-problems*. Cache the result instead.

---

### APPROACH ==> PLAIN RECURSIVE (DFS WAY)

#### Pre-requisite ==>

- Define problem inputs
- Identify stages, transition, choices for each state
- Just follow underlined pseudocode.
-

#### Typical Pseudocode ==>

```
/**
Returning number is easy HOWEVER returning actual path/list/array of outout 
IS TRICKY. Make sure that you are VARY of recursion and output parameters.
WE MIGHT need something like BACKTRACKING to keep track of E2E VALID Recursive PATH.
*/

-------------------------------------------------------------------
DP_SOLUTION_UTIL(inputA, inputB, Output, recursivePathOutput) {


   // VALID Base case----------- (Smallest subproblem)
   if (...)
   {
      // This NEED NOT TO BE optimal solution
      // Add to GLOBAL LIST and check the optimal solution size
      return 0;
   }
   
   
   // INVALID Base case-----------  (Smallest subproblem)
   if (...)
   {
      // Return the WORST option value.
      return Integer.MAX_VALUE;
   }
   
   
   //------------ EXPLORE ALL VALID CHOICES
   // Here we see that, problem COULD BE BROKEN DOWN into more than one Subproblems
   // Use FOR LOOP for MIN/MAX finding and update state accordingly
   
   
   FOR(FILTER(IS_VALID(choices))
   {
      recursivePathOutput.add(choice1);
      int outputChoice1 =  choice1 + DP_SOLUTION_UTIL(inputA-choice1, inputB, Output);
      recursivePathOutput.remove(choice1); // BACKTRACK
      
      recursivePathOutput.remove(choice2);
      int outputChoice2 =  choice2 + DP_SOLUTION_UTIL(inputA-choice2, inputB, Output);
      recursivePathOutput.remove(choice2); // BACKTRACK
      
      recursivePathOutput.remove(choice3);
      int outputChoice3 =  choice3 + DP_SOLUTION_UTIL(inputA-choice3, inputB, Output);
      recursivePathOutput.remove(choice3); // BACKTRACK
   }
   
   //------------ GET OPTIMAL CHOICE
   int OPTIMAL_CHOICE = getOptimalChoice(outputChoice1, outputChoice2, outputChoice3);
   
   //------------ RETURN OPTIMAL CHOICE 

   return OPTIMAL_CHOICE;
} // End of DP_SOLUTION_UTIL

-------------------------------------------------------------------
// Outputs: recursivePath and int (MIN/MAX value)
```

#### Time Complexity ==>

- Exponential ( like Brute force/backtracking) K^N or factorial

#### Space Complexity ==>

- O(1) or O(K)

---

### APPROACH ==> TOP DOWN Implementation- Memoization

#### Why top down ??

Well we try solving problem until sub-problems gets solved. Similar to recursive approach

#### Determine Memoization Dimensions

- This is **TRICKY** to determine dimensions of memo
- However, Identify **Unique parameters** to DP_SOLUTION_UTILS function
- Memo/DP table => This could be 1D, 2D or 3D ... **Mostly 2D**
- Create memo with dimensions ```<Dim1-Input1, Dim2-Input2, Dim3-Output>```
- List down **all possible values of Output**
- **Just add 2 lines** in recursive code**
    - WRITE ==> Write to memo at the end of getting optimal value for current call
    - READ ==> Read from memo at the beginning ( after base case--before recursion) to avoid reevaluating same
      overlapping problem.

#### Time Complexity

- Polynomial of degree 2, 3 so on...... O(N^2)
- Mostly linear: O(N), O(N2) , O(NxM)

#### Space Complexity

- Polynomial of degree 2
- Mostly O(NxM)

---

### (OPTIONAL for 2024) APPROACH==> Bottom Up/Tabulation

### Prerequisite

- Implement recursively
- Reverse the top down by removing recursive call to **iterative call**

#### Determine Tabulation Dimensions ( same as Memoization BUT...)

- This is **TRICKY** to determine dimensions of memo
- However, Identify **Unique parameters** to DP_SOLUTION_UTILS function
- Memo/DP table => This could be 1D, 2D or 3D ... **Mostly 2D**
- Create memo with dimensions ```<Dim1-Input1, Dim2-Input2, Dim3-Output>```
- List down **all possible values of varying input parameters - e.g. n and W of knapsack problem**
- ** Just add 2 lines** in recursive code
    - Return the value from memo for current call if present
    - Replace recursive calls with memoized reference viz. DP[n-1][w]
- Unlike Memoization ( DP[N][M]..Size of the table would be DP[N **+1**][M **+1**] )
-
    - **First row and first column** are **dependencies of base cases**

#### Pseudocode

```java
        0.Input and Output:
        int w[N]=[10,20,30],int val[N]=[100,200,300]
        int W=7
        Constraints[W+1]=[0,1,2,3,4,5,6,7](This is CONSTRAINTS dimention)

        Output:int DP[N+1][M+1];

        (DP[i][j]means solution to problem with N=i and M=j)

        1.BASE CASE:Initialize First row and First column(USE FOR LOOPS)
        (Make sure to choose CORRECT initial value as per problem)

        2.CHOICE DIAGRAM:Use PRE solved subproblems to make a choice for current DP[i][j]
        for(i=1:i<N+1)
        for(j=1:j<M+1)
        // if-else-MAX-CHOICES Goes here

        3.Your answer will be present in DP[N+1][M+1]

        4.return DP[N+1][M+1] 
```

#### Time Complexity

- Polynomial of degree 2
- Mostly O(NxM)

#### Space Complexity

- Polynomial of degree 2
- Mostly O(NxM)

---

### Must Do Dynamic Programming Problems

Refer chrome bookmarks for more details.

---

### Useful References

Refer chrome bookmarks

---
