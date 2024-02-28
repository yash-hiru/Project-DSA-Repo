# Tress
## Binary Search Tree
### General Approach
```
1. Use following Keywords: <Functions> CALL_SOLUTION, RETURN, CALL_SOLUTION_RECURSE, CALL_UTIL_FUNC_X, CALL_MAIN, CALL_GET_SAMPLE_EXAMPLE
<Logic> IF, ELSE, EQ, LOOP, LOOP-CONDITION, AND, LOCAL_FUNC_CALL, ASSIGN, LOOKUP, THROW, TRY-CATCH
<Comments> TBD, BASE-CASE-X, ASSUME, COMPLEXITY
During Interview Write Down: 1. Function Signatures to be used.Must haves- main(), solution(), getSample(), util()
2. [Diagram] Examples- Base case1, Base Case2...., General scale, Scalability case
3. State of the global data structure after EACH iteration/recursive call
4. [Diagram] Recursive call tree with main parameters in node and return value
5. Test cases dry run for each base and corner cases.
6. Approach- Brut-Force-Algorithm ( 5 to 10 mins), Optimized Algorithm (approach and algorithm)
7. [Diagram] Flow Chart of main logic- Optional
8. [Code] Optimized Algorithm Implementation/ OR/ Brut force implementation ( IF optimized did not go well)
9. [Analysis] Best /average/worst case time/space complexity
10. Some Good suggestions with viable optimizations.
```



### Tips
#### BST to DLL type problems( RIGHT-Visit-LEFT)
**Standard - Without modifications**
- Use REVERSE INORDER
- Solve for BASE CASES
- Update HEAD ( global pointer) from rightmost element( biggest) to smallest element
- Your solution at the end ( HEAD) will be updated after full recursion completion

**Standard - Inplace modifications ( left as backward, right as forward)**
- Tricky 
- Solve for BASE CASES
- Follow same approach (REVERSE INORDER) but DO required pointer adjustments 

#### LHS/RHS view
- Keep track of Levels
- Choose PREORDER but choose LEFT/RIGHT first based on LHS RHS view
- Print/Keep track of FIRST element of ANY LEVEL
- Thats it

#### Bottom/Top view
- Keep track of left/right POSITION ( -2 -1 0 1 2)
- Choose INORDER
- Print/Keep track of FIRST element of ANY POSITION
- Thats it
- 
#### Border Traversal (clockwise)
- Split problem and Dont do Inplace..Use LIST for quick proper solution
    - Reverse LHS view ( Insert all to solution if not exist)
    - Top view ( Insert all to solution if not exist)
    - RHS view  ( Insert all to solution if not exist)
    - Reverse Bottom view ( Insert all to solution if not exist)
- Print all from result LIST

#### Breadth First Traversal-- Standard BFS without level tracking
- NOT RECURSIVE approach..ITERATIVE
- Use Queue
- Approach
```
queue.enque(root)
  WHILE(queue.isNotEmpty()) 
    element = queue.deque()
    visit(element)
    queue.enque(element.left)
    queue.enque(element.right)
```
#### Breadth First Traversal-- Zigzag, Bottom-up, Even/Odd
- Zigzag traverse ( Use 2 stacks)
- EVEN/ODD level prints: 
  - MUST HAVE-- HASHMAP
    - Preprocessing: 
      - Insert < level, element> to hashmap
      - Start from < level=0, root>
      - While inserting children , lookup level of current element and insert
        (level+=1, root.left) and (level+=1, root.right) 
    - PostProcessing:
        - Filter and print level wise
        - For reverse levels ( Use Stack/Sort by decrementing order viz SortedHashMap) 

#### Remove Node
```
In cases other than root, you CAN NOT remove the same element in recursive call
because you need this level of tracking  [[[ L1(root) --> L2(**remove**Child) --> L3(Grand Child) ]]] to remove

Make this algorithm LOOK AHEAD (root value SHOULD NOT match to input value in recursive mode)
Strategy:  1. Handle Tree ROOT or immediate child removal in outer NON-Recursive call - remove(int value)
        2. Handle rest of the following cases in recursive call - removeRecursive(MyNode root, int value)
Learnings from failure:
      1. Base cases to recursive function changes took so much time (30 mins)
      2. Returning the cross call roots and using it in caller was not came to mind early. Less Confidence.
      3. Grandchildren adjustments were having multiple mistakes ,provided that, it was straightforward on paper.
      4. Recursion (after running base cases) based on value < > checks was straightforward.. took more time
      5. Obvious mistakes on conditions and typos
      6. Better to WRITE ALGO IN DETAILS BEFORE code..Coding is straightforward
```
