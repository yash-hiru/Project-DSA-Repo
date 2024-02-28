### Solution

// ===========================================================================
// Problem Summary
// ===========================================================================

Given N items where each item has some weight and profit associated with it
and also given a bag with capacity W, [i.e., the bag can hold at most W weight in it].
The task is to put the items into the bag such that the sum of profits associated with them is the maximum possible.

The constraint here is we can either put an item completely into the bag
or cannot put it at all [It is not possible to put a part of an item into the bag].

Input: N = 3, W = 4, profit[] = {1, 2, 3}, weight[] = {4, 5, 1}
Output: 3

Input: N = 3, W = 3, profit[] = {1, 2, 3}, weight[] = {4, 5, 6}
Output: 0

// ===========================================================================
// Understanding and Analysis
// ===========================================================================

State: Put item

Inputs/immutable: prof, wt

Mutable inputs: W, i(current item)

Base case: w[i] > W

Base case: i > size

MAX( Choices):
Put: prof[i]+ (prof,wt, W-wt[i], i+1)
Dont Put:  (prof,wt, W, i+1)

Subproblems: i, i+1, size-1

Initial State: (prof, wt, W, 0)

// ===========================================================================
// Implementation
// ===========================================================================

```java
//////////////////////////////////////////////
class KnapSack {
    public static void main(String[] args) {
        int W = 4;
        int profit[] = new int[]{1, 2, 3};
        int weight[] = new int[]{4, 5, 1};
        return knapsackMaxProfit(prof, wt, W);
    }


//////////////////////////////////////////////

    int knapsackMaxProfit(int[] prof, int[] wt, int W) {
// initial state -- Start from 0th item
        return knapsackMaxProfitUtil(prof, wt, prof.length W, 0);
    }

//////////////////////////////////////////////

    int knapsackMaxProfitUtil(int[] prof, int[] wt, int size, int W, int i) {
// Base case
        if (wt[i] > W) {
            return 0;
        }

        // Base case
        if (i > size) {
            return 0;
        }

        // Explore choices
        int maxProfit = Math.max(prof[i] + knapsackMaxProfitUtil(prof, wt, size, W - wt[i], i + 1),
                knapsackMaxProfitUtil(prof, wt, size, W, i + 1));

        // Recursive choices
        return maxProf;
    }

    ///////////////////////////////////////////////////
    int knapsackMaxProfitUtilMemoized(int[] prof, int[] wt, int size, int W, int i, int[] memo) {
        // Base case
        if (wt[i] > W) {
            return 0;
        }

        // Base case
        if (i > size) {
            return 0;
        }

        if (memo[i] != -1) {
            return memo[i];
        }

        // Explore choices
        memo[i] = Math.max(prof[i] + knapsackMaxProfitUtil(prof, wt, size, W - wt[i], i + 1),
                knapsackMaxProfitUtil(prof, wt, size, W, i + 1));

        // Recursive choices
        return memo[i];
    }
}



```

### Retro

Took **18 mins** to solve recursive way

handled all the cases

Coded properly with commend

Complete coded

took extra 5 mins for memoized implmentation


