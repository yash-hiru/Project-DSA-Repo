
## Learnings
**31/12/2024**

TOOK total 40 mins without any interactions 
Saved 5 mins for discussions and dryrun still failed to do ALL within time
```


* 
 * ********** Challeges **********
 * REQUIRED TO RESERVE AT LEAST 20-25 mins ..
 * Hence DISCUSSION+CODING to be completed within 30 mins
 *      Did NOT explore optimal solution (5 mins)
 *      Did NOT do dry run for simple and edge cases( 10 mins)
 *      Did not do code review (2 mins)
 *      Did not discuss time complexity and suggestions(5 mins)
 *
 *  ********** Retrospection **********
 * COULD HAVE SAVED 20 mins: HOW ?
 *      Spent 10 mins for problem description
 *      Spent 5 mins for formatting
 *      Spent 5 mins for unnecessary details typing and typo correction
 *
 *  ********** Rectification  **********
 *  STEP BY STEP APPROACH:
 *      [10 mins]
 *          Finish problem discussion within 10 mins ( discuss- type all the appraoches with pros and cons)
 *          Focus on agreed upon approach and start coding
 *      [20 mins] **** EXTREMELY IMPORTANT
 *          IMPLEMENT discussed solution within NO MORE THAN 20 mins.
 *              (LIGHTENING speed.. HENCE PRACTICE)
 *          DONT OVERFORMAT ..ignore extra spaces, small typos and small lang syntax errors
 *          START with EMPTY CLASS, CORE METHOD STUB and comments as PSEUDOCODE in java for quick IMPRESSION
 *          TALK and TALK..Discuss the time complexity and assumptions
 *          ASSUME some helper classes and utils to avoid diverting focus from main goal
 *          PAY ATTENSION to coding-- loops, exit conditions, recursion and iterations
 *          PAUSE-REVIEW-CODE--Repeat--Discuss (Loop)
 *          AVOID OVER FAST TYPING -- Maintain moderate Speed to avoid rework
 *      [10 mins]
 *          [5 mins] CODE REVIEW quickly within [5 mins]
 *          [5 mins] DRYRUN with dryrun for [5 mins] ( DO DRYRUN in own notepad) just write test case
 *          in the editor and tag it SUCCESS or failed
 *      [5 mins]
 *          RESERVE for optimizations, improvements and suggestions
 *          WRITE quickly to let them know you thought about it
    

```


## EDITOR
```
### ####################### Problem Description and Assumptions
//    Chess board of 8x8 size ..Knight starting from 0,0 and ending to 7,7
//    Constraints:
//    Visit each cell ONLY once
//    Visit ALL cells
//    Reach to 7,7
//    Input:
```


### ################### Coding--Approach(A)
```
/**
 This is for handling Knight Tour problem
 */
public class KnightVisitorApp {

    /**
     * MAIN METHOD --Driver program
     */
    public static void main(String args[]) {
        // Generate test data
        int[][] board = new int8[][ 8]();

        // Initialize the matrix with -1
        Arrays.fill(board, -1);

        // Invoke the solution
        knightTour(board);
    }


    /**
     * METHOD- Non recursive Initial State trigger
     */
    public void knightTour(final int[][] board) {

        // Kickoff tour from 0,0 and invoke recursive function with this initial state

        if (kinitTourRecursively(board, 0, 0, 0)) {
            LOG.info("Found the solution: " + board.toString());
        } else {
            LOG.info(" DID Not find the solution");
        }
    }


    /**
     * METHOD-- Recursive function to visit single cell
     */
    public void kinitTourRecursively(final int[][] board, int row, int column, int size, int visit) {
        // EXIT state -- All cells filled with some number other than -1
        if (row == size && column == size) {
            // We had got the solution
            return true;
        }

        // EXPLORE the choices ( Assuming Choice is Class holding row and column index)
        // Implement getNextLocations() (TODO)
        for (Choice choice : getNextLocations(row, column)) {
            // Check if valid choice
            if (board[choice.row][choice.column] == -1) {
                // FOUND POTENTIAL CANDIDATE -- Explore the choice
                visit = visit + 1;
                board[choice.row][choice.column] = visit;

                // RECURSE-- Next state---for next state of partial solution
                kinitTourRecursively(board, choice.row, choice.column, size, visit);
                // Stop immediately (use return value) if we are interested only in SINGLE WORKING SOLUTION

                // BACKTRACK if solution did not end to desired state..Tryother choices
                visit = visit - 1;
                board[choice.row][choice.column]

            }
            // Else explore other choices
            return false;
        }


        /** Get all choices*/
        private Choice[] getAllMovesFrom ( int row, int column){
            // TODO implement it: Check for the row and col upper and lower bounds
            // 2 ... row-2....col-1 ..col+1 ...
            // 2 ... row-1....col-2 ..col+2 ...
            // 2 ... row+1....col-2 ..col+2 ...
            // 2 ... row+2....col-1 ..col+1 ...
            // Return valid locations
            ArrayList<Choice> moves = new ArrayList<>();
            return moves;
        }


    }
```

### #######################     Dryrun
COULD NOT DO ( ****)

### #######################     Optimizations/NTH    

1. Board with some function and print all the inputs
2. Board with obstracles
3. Parallel processing ??? TBD


### #######################     COMPLEXITY    
// COULD NOT DO




