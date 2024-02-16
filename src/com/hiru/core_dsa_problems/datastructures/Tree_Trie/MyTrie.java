package com.hiru.core_dsa_problems.datastructures.Tree_Trie;

/**
 * ========================== Use cases ==========================
 * Purpose: Storing dictionary, phonebook..Sort strings alphabetically(DFS)
 *
 * ========================== Characteristics ==========================
 * --------------Root       ==> Node with null keys at the beginning
 * --------------Data       ==> (**INFERRED) , Index means char position.
 * -------------------------------Non-null node at the key position means its presence.
 * -------------------------------Index ( ch- 'a')
 * --------------Children   ==> Node.Key == Node Child == new TrieNode()
 * --------------Leaf Node  ==> Node Containing ALL null keys/children
 * --------------Traverse   ==> Incrementing indexOfString and moving to next level by changing node goes hand in hand**
 * -------------------------------1. charIndex++/charLevel++;
 * -------------------------------2. node = node.keys[charKeyPosition];
 * ==========================My Leanings==========================
 * 1. Depth = string char position (0--0, 1--1, ...)
 * 2. Each Node holds multiple keys
 * 3. Node does not hold special data unlike BST
 * 4. Node keys are ACTUALLY ONLY POINTERS instead of combination of keys and pointers in B-tree
 * 5. OPERATIONS:
 * ---------------INSERT ==> [[ O(key_length) ]] Simple; For each position, insert char at given level for given key and initialize key node
 * ---------------SEARCH ==> [[ O(key_length) ]] Same as insertion just reverse the char processing logic. FOR loop and node changes.
 * ---------------Print all (Backtracking way) ==>[[O(key_length * num_words]] This is also simple, you have SINGLE FOR LOOP for processing EACH LEVEL.
 * -------------------------If you find some key at this level, you simply follow these steps
 * ----------------------- -1. Add ch to string
 * -------------------------2. **recurse to that keyNode
 * -------------------------3. Backtrack-- remove ch from string
 */
public class MyTrie {
    private static final int ALBHABET_SIZE = 26;
    public MyTrieNode root;

    MyTrie() {
        root = new MyTrieNode();
    }

    ///////////////////////////////////////////
    public static void main(String args[]) {
        MyTrie trie = new MyTrie();

        // Operation -- Insert
        System.out.println("Inserting multiple Strings ==========> ");
        trie.insert("dad");
        trie.insert("dam");
        trie.insert("bat");
        trie.insert("bad");
        trie.insert("cat");

        // Operation -- Print all( backtracking)
        System.out.println("Printing ALl the Contents ==========> ");
        trie.printAllStrings(trie.root, "");

        // Operation -- Search
        System.out.println("Searching " + "dad" + ":" + trie.search("dad", trie.root));
        System.out.println("Searching " + "mine" + ":" + trie.search("mine", trie.root));
        System.out.println("Searching " + "cat" + ":" + trie.search("cat", trie.root));
        System.out.println("Searching " + "bat" + ":" + trie.search("bat", trie.root));
        System.out.println("Searching " + "dam" + ":" + trie.search("dam", trie.root));
        System.out.println("Searching " + "hello" + ":" + trie.search("hello", trie.root));

    }

    ///////////////////////////////////////////////////
    public void insert(String s) {
        // Explore each character-- Single character present at different level of trie
        MyTrieNode currNode = root;
        // Char at index N <==> Level N of tree
        for (int level = 0; level < s.length(); level++) {
            // 1. Read character at given level (0 == root level)
            char ch = s.charAt(level);

            // 2. Get its placement index at given level (Optional if node not present )
            // Learning-- Subtracting start char from all char gives index
            int chIndex = ch - 'a';
            if (currNode.keys[chIndex] == null) {
                // We did not find any trie node for this <level, pos> como. Create one
                currNode.keys[chIndex] = new MyTrieNode();
            }
            // 3. Advance currNode
            currNode = currNode.keys[chIndex];
        }
        // Inserted
    }

    ////////////////////////////////////////////
    boolean search(String str, MyTrieNode node) {

        // Level by level--Character by character search
        for (int level = 0; level < str.length(); level++) {
            //== Process this level

            // Read level l character
            char ch = str.charAt(level);

            //get its key
            int keyIndex = ch - 'a';

            // Get the key
            if (null == node.keys[keyIndex]) {
                return false; // Not found (SAD CASE)
            }
            // Move to next level ( DON'T FORGET)
            node = node.keys[keyIndex];
        }
        // HAPPY case
        return true;
    }

    ////////////////////////////////////////////
    // GFG ==> Solution to Sort strings using Trie
    // (https://www.geeksforgeeks.org/sorting-array-strings-words-using-trie/)
    public void printAllStrings(MyTrieNode node, String str) {
        // Explore node
        boolean isLeaf = true; // Assume leaf

        // If not leaf, Explore node keys
        for (int chIndex = 0; chIndex < ALBHABET_SIZE; chIndex++) {
            // Get keyNode for chIndex
            MyTrieNode keyNode = node.keys[chIndex];

            // Process keyNode only if it has meaningful data
            if (keyNode != null) {
                isLeaf = false; // At least one key is non-null

                // READ CHAR -- Get ascii value
                int asciiVal = chIndex + 'a';
                // READ CHAR -- Get char from ascii int
                char ch = (char) asciiVal;

                // 1. BACKTRACK/Append ch to string
                str += ch;
                // 2. BACKTRACK/DFS-Recursive
                printAllStrings(keyNode, str);
                // 3. BACKTRACK/Backtrack
                str = str.substring(0, str.length() - 1); // BACKTRACK
            }
        }

        // Learning== Base case need not tobe at the start, Here (keyNode != null) would never lead to function invocation with
        // null node
        // BASE CASE === Node is leaf Node , Print the string
        if (isLeaf) {
            System.out.println(str);
        }

    }

    /////////////////////////////////////////////////
    public static class MyTrieNode {
        MyTrieNode[] keys;

        public MyTrieNode() {
            keys = new MyTrieNode[ALBHABET_SIZE];
        }
    }
}
