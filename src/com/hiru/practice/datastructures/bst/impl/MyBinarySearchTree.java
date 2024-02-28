package com.hiru.practice.datastructures.bst.impl;

import com.hiru.practice.util.MyLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * MyBST Main Data structure class
 */
public class MyBinarySearchTree {
    //////////////////////// Fields
    private MyNode root;
    private int numberOfNodes = 0;

    // CTOR-Empty tree
    MyBinarySearchTree() {
        root = null;
    }

    //CTOR-Initial tree with just root
    MyBinarySearchTree(final int value) {
        root = new MyNode(value);
    }

    //Getter for root
    MyNode getRoot() {
        return root;
    }

    void setRoot(MyNode newRoot) {
        root = newRoot;
    }

    /////////////// Operation -- Insertion (Public outer and inner recursive APIs)////////////////////
    public void insert(final int value) {
        // Trigger recursive call
        insertRecurse(root, value);
    }

    private MyNode insertRecurse(MyNode root, final int value) {
        // Input validation viz Null check etc. will go here
        // case1: Empty Tree
        if (root == null) {
            root = new MyNode(value);
            return root;
        }
        // Case2: Left is NULL and element is less than root
        else if (value < root.getData()) {
            if (root.getLeft() == null) {
                root.setLeft(new MyNode(value));
                return root;
            } else {
                //RECURSE***
                return insertRecurse(root.getLeft(), value);
            }
        }
        // Case3: Right is NULL and element is greater than root
        else if (value > root.getData()) {
            if (root.getRight() == null) {
                root.setRight(new MyNode(value));
                return root;
            } else {
                //RECURSE**
                return insertRecurse(root.getRight(), value);
            }
        }
        // Case4: Ignore existing node insertion
        else {
            //Node already exist
            MyLogger.warn("Node Already Exist"); //return path
            return root;
        }
    }


    /////////////////////// Traverse --- Preorder ///////////////////////////
    public void traversePreorder(MyNode root) {
        // Return Paths/Base cases
        if (root == null) {
            return;
        }
        // Print current Node
        System.out.print(root.getData() + ", ");
        // Recursively traverse Left
        traversePreorder(root.getLeft());
        // Recursively Traverse Right
        traversePreorder(root.getRight());

    }

    /////////////////////// Traverse --- Inorder ///////////////////////////
    public void traverseInorder(MyNode root) {
        // Return Paths/Base cases
        if (root == null) {
            return;
        }
        // Recursively traverse Left
        traverseInorder(root.getLeft());
        // Print current Node
        System.out.print(root.getData() + ", ");
        // Recursively Traverse Right
        traverseInorder(root.getRight());

    }

    /////////////////////// Traverse --- Postorder ///////////////////////////
    public void traversePostorder(MyNode root) {
        // Return Paths/Base cases
        if (root == null) {
            return;
        }
        // Recursively traverse Left
        traversePostorder(root.getLeft());
        // Recursively Traverse Right
        traversePostorder(root.getRight());
        // Print current Node
        System.out.print(root.getData() + ", ");
    }

    ////////////////////// View --- LeftHandSide ///////////////////////////////
    public void viewLeftHandSide(MyNode root, Map<Integer, Integer> resultLHSView, int depth) {
        // Recurse InOrder (For now)
        // Even preorder and Postorder would also WORK as far as LEFT tree is being traversed before RIGHT Subtree
        // Keep track of Depth-In/Out parameter
        // Update first depth instance. Ignore next instances(Inorder magic)
        if (root == null) {
            return;
        }
        // Recursively traverse Left: Pass in incremented value in recursive call
        viewLeftHandSide(root.getLeft(), resultLHSView, depth + 1);

        // Update first observed node at GIVEN DEPTH to LHS output
        if (!resultLHSView.containsKey(depth)) {
            // Insert record to output
            resultLHSView.put(depth, root.getData());
        }
        // Recursively Traverse Right: Pass in incremented value in recursive call
        viewLeftHandSide(root.getRight(), resultLHSView, depth + 1);
    }

    ////////////////////// View --- RightHandSide ///////////////////////////////
    public void viewRightHandSide(MyNode root, Map<Integer, Integer> resultRHSView, int depth) {
        // Recurse InOrder (For now)
        // Even preorder and Postorder would also WORK as far as RIGHT tree is being traversed before LEFT Subtree
        // Keep track of Depth-In/Out parameter
        // Update first depth instance. Ignore next instances(Inorder magic)
        if (root == null) {
            return;
        }

        // Recursively Traverse Right: Pass in incremented value in recursive call
        viewRightHandSide(root.getRight(), resultRHSView, depth + 1);

        // Update first observed node at GIVEN DEPTH to LHS output
        if (!resultRHSView.containsKey(depth)) {
            // Insert record to output
            resultRHSView.put(depth, root.getData());
        }

        // Recursively traverse Left: Pass in incremented value in recursive call
        viewRightHandSide(root.getLeft(), resultRHSView, depth + 1);
    }

    ////////////////////// Operation--- remove ///////////////////////////////
    // In cases other than root, you CAN NOT remove the same element in recursive call
    // because you need this level of tracking  [[[ L1(root) --> L2(**remove**Child) --> L3(Grand Child) ]]] to remove

    // Make this algorithm LOOK AHEAD (root value SHOULD NOT match to input value in recursive mode)
    // Strategy:  1. Handle Tree ROOT or immediate child removal in outer NON-Recursive call - remove(int value)
    //            2. Handle rest of the following cases in recursive call - removeRecursive(MyNode root, int value)
    // Learnings:
    //          1. Base cases to recursive function changes took so much time (30 mins)
    //          2. Returning the cross call roots and using it in caller was not came to mind early. Less Confidence
    //          3. Grandchildren adjustments were having multiple mistakes ,provided that, it was straightforward on paper.
    //          4. Recursion (after running base cases) based on value < > checks was straightforward.. took more time
    //          5. Obvious mistakes on conditions and typos
    //          6. Better to WRITE ALGO IN DETAILS BEFORE code..Coding is straightforward
    //
    //TODO README.md(Already took them to RED book):   Use following Keywords: <Functions> CALL_SOLUTION, RETURN, CALL_SOLUTION_RECURSE, CALL_UTIL_FUNC_X, CALL_MAIN, CALL_GET_SAMPLE_EXAMPLE
    //                         <Logic> IF, ELSE, EQ, LOOP, LOOP-CONDITION, AND, LOCAL_FUNC_CALL, ASSIGN, LOOKUP, THROW, TRY-CATCH
    //                         <Comments> TBD, BASE-CASE-X, ASSUME, COMPLEXITY
    //               During Interview Write Down: 1. Function Signatures to be used.Must haves- main(), solution(), getSample(), util()
    //                           2. [Diagram] Examples- Base case1, Base Case2...., General scale, Scalability case
    //                           3. State of the global data structure after EACH iteration/recursive call
    //                           4. [Diagram] Recursive call tree with main parameters in node and return value
    //                           5. Test cases dry run for each base and corner cases.
    //                           6. Approach- Brut-Force-Algorithm ( 5 to 10 mins), Optimized Algorithm (approach and algorithm)
    //                           7. [Diagram] Flow Chart of main logic- Optional
    //                           8. [Code] Optimized Algorithm Implementation/ OR/ Brut force implementation ( IF optimized did not go well)
    //                           9. [Analysis] Best /average/worst case time/space complexity
    //                           10. Some Good suggestions with viable optimizations.
    public MyNode remove(MyNode root, final int value) {
        if (root != null) {
            if (value == root.getData()) {
                // Case2- remove OnlyRoot from the tree
                if (root.getLeft() == null && root.getRight() == null) {
                    root = null;
                }
                // Case3: Simpler RootOnly Left Subtree
                else if (root.getLeft() != null && root.getLeft().getLeft() == null && root.getLeft().getRight() == null) {
                    // RootOnly Left subtree- Simply set it as root
                    root.getLeft().setRight(root.getRight());
                    root = root.getLeft();
                }
                // Case3: Simpler RootOnly Right Subtree
                else if (root.getRight() != null && root.getRight().getLeft() == null && root.getRight().getRight() == null) {
                    // RootOnly Right subtree- Simply set it as root
                    root.getRight().setLeft(root.getLeft());
                    root = root.getRight();
                }
                //Case4: Contains some Grand children - See if right child of left child exist
                else if (root.getLeft() != null && root.getLeft().getRight() != null) {
                    // Get the Grand child
                    MyNode candidate = root.getLeft().getRight();
                    // Unlink Grand child candidate from its parent (here root's child)
                    root.getLeft().setRight(null);
                    //Promote grandchild as a root
                    candidate.setLeft(root.getLeft());
                    candidate.setRight(root.getRight());
                    root = candidate;
                }
                //Case4: Contains some Grand children - See if left child of right child exist
                else if (root.getRight() != null && root.getRight().getLeft() != null) {
                    // Get the Grand child
                    MyNode candidate = root.getRight().getLeft();
                    // Unlink Grand child candidate from its parent (here root's child)
                    root.getRight().setLeft(null);
                    //Promote grandchild as a root
                    candidate.setLeft(root.getLeft());
                    candidate.setRight(root.getRight());
                    root = candidate;
                }
            } else if (value < root.getData()) {
                // Recuse LEFT subtree to evaluate similar base cases- And set new LEFT returned from callee's NEW root
                // IMP (TODO-DONE): Here setting caller's root as callee's left (wiring changes) is EXTREMELY important
                // to have cross call communication in return channel

                root.setLeft(remove(root.getLeft(), value));
            } else if (value > root.getData()) {
                // Recuse RIGHT subtree to evaluate similar base cases- And set new RIGHT returned from callee's NEW root
                // IMP (TODO-DONE): Here setting caller's root as callee's right (wiring changes) is EXTREMELY important
                // to have cross call communication in return channel
                root.setRight(remove(root.getRight(), value));
            }
        }
        // return root to call
        return root;
    }

    //////////////////////////////////////////////////////////////////////////
    // MAIN: Driver Program
    //////////////////////////////////////////////////////////////////////////
    private static MyBinarySearchTree TEST_getSample() {
        ////////////////////// Construct Tree
        MyBinarySearchTree tree = new MyBinarySearchTree(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(15);
        tree.insert(7);
        tree.insert(5);
        tree.insert(9);
        return tree;
    }

    public static void main(String args[]) {
        ////////////////////// TODO- Remove Node
        MyBinarySearchTree tree1 = TEST_getSample();
        tree1.setRoot(tree1.remove(tree1.getRoot(), 10));
        System.out.println();
        MyLogger.info("Preorder:");
        tree1.traversePreorder(tree1.getRoot());
        System.out.println();
        MyLogger.info("Inorder:");
        tree1.traverseInorder(tree1.getRoot());
        System.out.println();
        MyLogger.info("Postorder:");
        tree1.traversePostorder(tree1.getRoot());
        System.out.println();

        ////////////////////// Traverse-In/Pre/Post
        MyBinarySearchTree tree = TEST_getSample();
        System.out.println();
        MyLogger.info("Preorder:");
        tree.traversePreorder(tree.getRoot());
        System.out.println();
        MyLogger.info("Inorder:");
        tree.traverseInorder(tree.getRoot());
        System.out.println();
        MyLogger.info("Postorder:");
        tree.traversePostorder(tree.getRoot());
        System.out.println();

        ////////////////////// LHS view
        HashMap<Integer, Integer> lhsResultMap = new HashMap<>();
        tree.viewLeftHandSide(tree.getRoot(), lhsResultMap, 0);
        System.out.println(lhsResultMap);


        ////////////////////// RHS view
        HashMap<Integer, Integer> rhsResultMap = new HashMap<>();
        tree.viewRightHandSide(tree.getRoot(), rhsResultMap, 0);
        System.out.println(rhsResultMap);


        ////////////////////// TODO- Convert SortedArray Into Binary Tree

        ///////////////////// TODO- Validate BST -Left subtree contains all smaller and right contains all bigger values

        //////////////////// TODO - Construct BST From preorder


    }
}
