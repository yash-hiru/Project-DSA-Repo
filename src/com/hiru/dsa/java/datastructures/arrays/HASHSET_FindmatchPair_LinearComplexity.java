package com.hiru.dsa.java.datastructures.arrays;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * HashSet
 */
public class HASHSET_FindmatchPair_LinearComplexity {
    public static void main(String args[]) {
        List<Integer> elements = new ArrayList<>();
        elements.add(1);
        elements.add(2);
        elements.add(4);
        elements.add(9);
        elements.add(5);
        System.out.println(findPair(elements, 10));
    }

    public static boolean findPair(List<Integer> arr, int sum) {
        HashSet<Integer> cache = new HashSet<>();
        for (int i = 0; i < arr.size(); i++) {
            if (!cache.contains(arr.get(i))) {
                cache.add(arr.get(i));
            }
            if (cache.contains(sum - arr.get(i))) {
                return true;
            }
        }
        return false;
    }
}
