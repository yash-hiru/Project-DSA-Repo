### Approach

If V is size of graph
Find K such that ANY subset of Size K is vertex cover( sub-optimal) solution

[Geeks solution using GOSPER Hack](https://www.geeksforgeeks.org/finding-minimum-vertex-cover-graph-using-binary-search/)

### Pseudocode

```java
class GFG {

    /** Binary Search way to find Minimal Subset*/
    // Reduce 2^N time complexity to log(N)
    void findMinimalCover(int min, int max) {
        int k = (int) min + (max - min) / 2; // Find MID which is k here
        // K size subsets-- See if we can have AT LEAST 1 cover
        for (HashSet subset : getSubsets(k)) {
            if (isCover(subset) == true) {
                // CASE 1 ---- FOUND at least one subset WITHOUT any cover
                findMinimalCover(min, k - 1); // OPTIMIZE -- Found lower K
            }
        }
        // CASE 2 ---- NOT FOUND even a single subset of size K as cover
        findMinimalCover(k + 1, max); // Try higher k
    }
}
```