package com.hiru.core_dsa_problems.datastructures.linkedlist.single;

import com.hiru.core_dsa_problems.commons.MyLogger;

///////////////////////////////////
// class MyNode
///////////////////////////////////
public class MyNode {
    private int data;
    private MyNode next;

    //Ctor
    public MyNode(final int data) {
        this.data = data;
        next = null;
    }

    // Print
    public void print() {
        MyLogger.info("\nNode: " + data, true);
    }

    // GetNext
    public MyNode getNext() {
        return next;
    }

    // SetData
    public void setData(int newData) {
        data = newData;
    }

    //SetNext
    public void setNext(final MyNode newNext) {
        next = newNext;
    }

    public int getData() {
        return data;
    }
}
