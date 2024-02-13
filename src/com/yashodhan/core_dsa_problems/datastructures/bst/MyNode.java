package com.yashodhan.core_dsa_problems.datastructures.bst;

import com.yashodhan.core_dsa_problems.commons.MyLogger;

public class MyNode {
    // fields
    private int data;
    private MyNode left;
    private MyNode right;

    //Ctor
    MyNode(final int val) {
        data = val;
    }

    // Getters
    public int getData() {
        return data;
    }

    public MyNode getLeft() {
        return left;
    }

    public MyNode getRight() {
        return right;
    }

    // Setters
    public void setData(int val) {
        data = val;
    }

    public void setLeft(MyNode val) {
        left = val;
    }

    public void setRight(MyNode val) {
        right = val;
    }

    public void print() {
        MyLogger.info("Printing Single Node");
        //Print Data
        MyLogger.info("Data:" + data);
        // Print Left
        if (left == null) {
            MyLogger.info("Left: NULL");
        }
        else {
            MyLogger.info("Left:" + left.getData());
        }
        // Print Right
        if (right == null) {
            MyLogger.info("Right: NULL");
        }
        else {
            MyLogger.info("Right:" + right.getData());
        }
    }

    public static void main(String args[]) {
        MyNode node = new MyNode(10);
        node.setLeft(new MyNode(8));
        node.setRight(new MyNode(12));
        node.print();
    }
}
// Done
