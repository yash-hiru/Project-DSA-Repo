package com.hiru.dsa.java.datastructures.trie.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * **** This is trie implementation with following considerations and insights.****
 * Considerations ==> Words, Lookup, Distinct count, Occurances, Prefix search,
 * Each Node ==> {HashMap{String ch, TrieNode} , bool isWord, int numOccurances>}
 * Root Node==> Always is initialised with NO keys at all
 * Leaf Node ==> Node without any keys
 * .................All leafs nodes are word nodes but all word nodes not necessarily Leaf Nodes
 *
 * Common Use cases ==>
 * ..............1. Word count (count all internal and external nodes)
 * ..............2. Word count with prefix (Skip prefix char nodes and .. then ..count all internal and external nodes)
 * ..............3. Word occurance count (Word lookup and return numOccurances)
 * ..............4. Print all words
 * ......................Backtracking DFS:
 * ......................Choices==keys, Stage==level, Base==isEndOfNode, Backtack==str=str.substring(0, length-1)
 */
public class MyTrie {
    MyTrieNode root = new MyTrieNode();

    /////////////////////////////////////////////////////

    public static void main(String args[]) {
        MyTrie trie = new MyTrie();
        trie.insert("hello");
        trie.insert("hello");
        trie.insert("world");
        trie.insert("hiren");
        trie.insert("harshad");
        trie.insert("yashodhan");
        trie.insert("yashodhan");
        trie.insert("yashodhan");
        trie.insert("yash");
        trie.insert("yashoda");
        trie.insert("yashashree");
        trie.insert("yashodhar");


        System.out.println("Find: ========================");
        trie.find("hello");

        System.out.println("Statistics: ========================");
        trie.printWordsWithCounts(trie.root, "");

        System.out.println("Prefix Count: ========================");
        trie.countWordsWithPrefix("yash"); //Return 5

        System.out.println("Count Words BFS Count: ========================");
        System.out.println(trie.countDistinctWordsBFS()); //Return 9

        System.out.println("Count Words DFS Count: ========================");
        System.out.println(trie.countDistinctWordsDFS(trie.root)); //Return 9
    }

    /////////////////////////////////////////////////////

    /**
     * Insert
     *
     * @param str
     */
    public void insert(String str) {
        // Start from root and add key at all levels
        if (str != null) {
            MyTrieNode node = root; // Initial state
            // Update the trie
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                if (!node.mapping.containsKey("" + ch)) {
                    node.mapping.put("" + ch, new MyTrieNode());
                }
                node = node.mapping.get("" + ch); // Next level node
                node.numOccurances += 1;
            }
            // Word level stats
            node.isEndOfWord = true;
        }
    }

    public boolean find(String str) {
        if (str == null || str.trim().length() == 0) {
            return false;
        }
        MyTrieNode node = root; // Initial state
        // Update the trie
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (!node.mapping.containsKey("" + ch)) {
                // System.out.println("Find: " + str + " : FALSE");
                return false;
            }
            node = node.mapping.get("" + ch); // Next level node
        }
        if (node.isEndOfWord) {
            System.out.println("Find: " + str + " : TRUE");
            return true;
        } else {
            //System.out.println("Find: " + str + " : FALSE");
            return false;
        }
    }

    /////////////////////////////////////////////////////

    /**
     * PrintAll
     */
    public void printWordsWithCounts(MyTrieNode node, String str) {
        // Backtracking-- Stage == Trie level
        // Backtracking -- Choice == key(s)
        for (String key : node.mapping.keySet()) {
            str += key; // Backtracking-- Make choice
            MyTrieNode value = node.mapping.get(key);
            if (value.isEndOfWord) {
                System.out.println("Word: " + str + ":" + value.numOccurances);
            }
            printWordsWithCounts(value, str);
            str = str.substring(0, str.length() - 1);// Backtracking-- Make choice
        }
    }

    /**
     * Lookup: Iterate over string chars and explore inner levels
     */
    public int countWordsWithPrefix(String prefix) {
        System.out.println("Prefix: " + prefix);
        MyTrieNode node = root;
        // Part1- find the prefix end node
        for (int i = 0; i < prefix.length(); i++) {
            String key = "" + prefix.charAt(i);
            if (node.mapping.containsKey(key)) {
                node = node.mapping.get(key);
            }
        }
        // Part2- Find the words originating from the prefix (DFS way recursively)
        int uniqueWords = countDistinctWordsDFS(node);
        System.out.println("Word Count: " + uniqueWords);
        return uniqueWords;
    }

    public int countDistinctWordsBFS() {
        LinkedList<MyTrieNode> queue = new LinkedList<>();

        //1. Initial State: Enqueu ROOT keys
        for (Map.Entry<String, MyTrieNode> entry :
                root.mapping.entrySet()) {
            queue.add(entry.getValue());
        }
        // IMPORTANT: Slight variation includes the Initial state being prefix keys.
        // 2. Loop until queue is empty ( O(NxK) )
        int count = 0;
        while (!queue.isEmpty()) {
            // 2.1 Deque
            MyTrieNode node = queue.poll();
            // 2.2 Increment count if word?
            if (node.isEndOfWord)
                count += 1;
            // 2.3 Enque children keys
            if (node.mapping.entrySet().size() > 0) {
                queue.addAll(node.mapping.values()); // Bulk enque
            }
        }
        //3. return count
        return count;
    }

    // Recursive DFS
    private int countDistinctWordsDFS(MyTrieNode node) {
        // Case1-- Word at leaf level (return 1)
        if (node.mapping.keySet().size() == 0) {
            return 1; //Base case: Return 1 for any unique word. (dont return word occurance count)
        }

        // Case2-- Word at intermediate level (increment by 1 and recurse)
        int count = 0;
        if (node.isEndOfWord) {
            count += 1;// Increment one count before DFS
        }
        // Recurse
        for (String key : node.mapping.keySet()) {
            count += countDistinctWordsDFS(node.mapping.get(key)); // Aggregate: Sum all counts
        }
        return count;
    }

    /**
     * Trie Node
     */
    static class MyTrieNode {
        HashMap<String, MyTrieNode> mapping; // Key value mappings
        boolean isEndOfWord; // Indicates End of word node
        int numOccurances; // Count of given word if its endof word node (0 for intermediate node..could be used for prefixes)

        /**
         * CTOR
         */
        public MyTrieNode() {
            mapping = new HashMap<>();
            numOccurances = 0;
            isEndOfWord = false;
        }
    }
}
