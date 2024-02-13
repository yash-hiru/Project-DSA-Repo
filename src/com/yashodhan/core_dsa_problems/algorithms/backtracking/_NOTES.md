## Backtracking

---

### Background
1. **Recursion is parent** wheras **DP, Divide and Conquer and Backtracking** are **children**
2. **Children**:
   1. --------**Recursion**--------
      1. You explore **till Leaf node(s)** and exit on it. And **mostly get answer in Lead node**
      2. For e.g. BST problems 
   2. --------**Dynamic Programming**--------
      1. You explore **choices** for **optimization** problem. (Memoization-TopDown OR Tabulation-BottomUp) 
      2. You **cache** results of repeated problems.
      3. You mostly get **answer** in **last/root node**.
      4. In **Each recursive call** you make **certain decision** based on **some choice**
      5. You are guaranteed that, your **inner calls will lead to some answer for sure**
      6. Your base case/Exit condition does return some value viz. 0, 1 ( Smallest sub-problem solution)
      7. For e.g. Longest Common Subsequence, All N-grams, 0/1 knapsack, Graph Single source shortest path.
   4. --------**Backtracking**--------
      1. Controlled recursion with pass by reference
      2. Try to solve problem **incrementally** by following **"Potential solution PATH"**
      3. **Backtrack** when you see **NO PATH ahead due to current choice**.Revert the current choice and explore other valid choices.
      4. Subproblems ==> Enumeration, Optimization, Decision
      5. Complexity ==> Exponential and Factorial
   5. --------**Greedy** ----------------
      1. Each step in problem solving is leading to partial solution.
      2. You essentially do long term(Strategic) work using short term(Greedy) way.
          1. For example greedy approach for ```Largest number with K-swaps``` does NOT work for ALL inputs
          2. 1234567 (Works) , 4577(Does NOT work) 
      3**For e.g.** Fractional Knapsack, Multi level graph shortest path (A to B)

### How Does backtracking work ?

----
1. Backtracking is a general algorithm for **search all possible combinations/solutions** to some computational problem.
2. **Incrementally** builds candidates to the solutions using **depth first search** method
3. We starts from root of recursive tree and leads to either of **2 leaves**
   1. ```SUCCESS```- Add to solution
   2. ```ABANDON```- No solution could be found on this path.
3. ```Abandons``` each partial candidate c (**"backtracks"**)
as soon as it determines that c cannot possibly be completed to a valid solution.
4. We undo/backtrack certain assignments of values to variables **to reassign** them to ```other possible values```, see if those lead to a valid solution.
5. Backtracking is **optimization over brute force**: Mostly similar to Brute force HOWEVER, we stop exploring potential candidates as soon as those stop leading to solution
6. **Alternate definition** ==> **Controlled Recursion** + **Pass by Reference**


### Cost of backtracking

----
- Mostly **Exponential O(K^N)** or **factorial O(N!)**
- **Faster than brute force** which takes N^N
- Still **slower than DP, Greedy and Polynomial, linear and logarithmic solutions**.


### Type of backtracking problems

----

1. Decision ==> ```See if``` solution exist
2. Optimization ==> ```Find Optimal``` Solution (Any)
3. Enumeration ==> ```List all``` the solutions

### Psuedocode

----

```
void FIND_SOLUTIONS( CURRENT_STATE, IN_OUT_PARAMETERS):
    //---------------- Exit case or base case
    if (VALID_SOLUTION(IN_OUT_PARAMETERS)):
            // --- Some recursive path ends to DESIRED STATE
        STORE THE SOLUTION
        RETURN
    //---------------- Iterate over all the choices
    FOR (CHOICE: ALL_CHOICES):
        // Filter out valid choice before recursive call for next state 
        IF (VALID(CHOICE)):
        
                //----- We have found some valid choice for next state (phase)
            
            APPLY(CURRENT_STATE, CHOICE)

                //----- Confirm this choice assuming next state would lead to final solution
    
            FIND_SOLUTIONS(NEXT_STATE, IN_OUT_PARAMETERS)
            
                //----- Desicion Problem: Return immediately if we know at least 1 solution was found.

                //----- Backtrack if we dont see next state leading to the solution
            IF (PARAM STATUS or RETURN VALUE is FALSE ):
                    // ----- Dont backtrack for valid solution path 
                    // ----- we undo certain assignments of values to variables to reassign them to other possible values, see if those lead to a valid solution.
                BACKTRACK (remove(CURRENT_STATE, CHOICE))
    RETURN
```


### Must Do Backtracking Problems

----

```
1. Chess N Queen (**DONE**)
2. Rat in maze (**DONE**) 
3. Chess Knight tour (**DONE**) 
4. Combination Sum
5. Suduko
4. Palindrome Partitioning
5. Work Break
6. ALl subsets (**DONE**)
7. All permutations of string (**DONE**)
8. Phone Letter digits
9. M-coloring problem (**DONE**)
10. Longest Possible Route in a Matrix with Hurdles (HARD-- Interesting problem)
11. Partition of a set into K subsets with equal sum  
12. Print all longest common sub-sequences in lexicographical order ( HARD-- Why not DP ?)
```
### Useful References
1. [Geeks for geeks](https://www.geeksforgeeks.org/introduction-to-backtracking-data-structure-and-algorithm-tutorials/)
2. [Aditya Verma Backtracking Playlist](https://www.youtube.com/playlist?list=PL_z_8CaSLPWdbOTog8Jxk9XOjzUs3egMP)