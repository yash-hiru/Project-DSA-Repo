//==========================================================================================
// Problem Statement
//==========================================================================================
// Given an array arr[] where each element represents the max number of steps that can be made forward from that index.
// The task is to find the minimum number of jumps to reach the end of the array starting from index 0.

Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
Output: 3 (1-> 3 -> 9 -> 9)

Input:  arr[] = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
Output: 10

=========
Steps: Possible places for next jump
Jump: One index to other ( without value)

Initial State: 0
Intermediate State: Any valid index
Choice for next state: for (c: max_allowed_jumps)
Final State: size-1
Result: int(total jumps)

//==========================================================================================
// Algorithm/Code
//==========================================================================================

```
class MinJumpsSolution {

    ///////////////////////////////// Driver function
    public static void main(String args []) {
        int arr[] = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
        
        Log.Info(findMinJumps(arr));
    }
    
    ///////////////////////////////// Solution
    public int minJumps(int [] arr) {
        
        return minJumpsUtil(arr, 0);
    }
    
    ///////////////////////////////// Solution recursive
    // Move forward with possible outputs. Block at end state
    public int minJumpsUtil(int [] arr, int pos) {
    
        // == BASE CASE== Happy (Found some solution)
        if (pos == size-1) {
            return 0;
        }
        
        // == BASE CASE==Invalid state
        if( pos > size-1 ) {
            // Disqualify this path
            return Integer.MAX_VALUE; // Discard path which does not lead to solution
        }
        
        
        // == RECURSE == Recursive for partial solution
        // Read position to find next steps
        int steps = arr[pos]
        int minJumps= Integer.MAX_VALUE;
        
        for (int i=pos+1; i < pos+1+steps; i++) {
            // increment jump count by ONE for valid jump
            jumps = 1 + minJumpsUtil(arr, i);
             
            if(jumps < minJumps) {
                minJumps = jumps;
            } 
        }
        return minJumps;
    }

}
```

//==========================================================================================
// Dryrun
//==========================================================================================

Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
Output: 3 (1-> 3 -> 9 -> 9)

pos = 0 1(2,3,4)  
jumps = 0 1


