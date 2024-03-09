package com.hiru.dsa.java.datastructures.stack.impl;

class MyStack {

    private int data[];  // Stack array
    private int top; // Stack top pointer
    private int maxCapacity; // Max Capacity (TBD: See if we don't want to restrict)

    /**
     * Constructor
     *
     * @param size
     */
    public MyStack(final int size) {
        data = new int[size];
        maxCapacity = size;
        top = -1;
    }

    /**
     * Push Element
     *
     * @param newElement
     */
    public void push(final int newElement) throws MyStackIsFullException {
        //check if full
        if (this.isFull()) {
            System.out.println("WARN: Stack is Full...");
            throw new MyStackIsFullException("My Stack is full");
        }
        data[++top] = newElement;

    }

    /**
     * Pop element
     *
     * @return top element
     */
    public int pop() {
        if (isEmpty()) {
            throw new MyStackIsEmptyException(" MyStack is Empty");
        }
        return data[top--];
    }

    /**
     * Check if is full
     *
     * @return
     */
    private boolean isFull() {
        return data.length == top + 1;
    }

    /**
     * Check if is empty
     *
     * @return
     */
    private boolean isEmpty() {
        return top == -1;
    }

    // reset()
    private void reset() {
        data = new int[maxCapacity];
        top = -1;
    }

    // display()
    public void display() {
        System.out.println(">>>>>>>>>> INFO: Stack Details >>>>>>>>>>");
        System.out.println("Data:");
        for (int i : data) {
            System.out.print(i + ", ");
        }
        System.out.println("\nTop:");
        System.out.println(top);
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }

    /**
     * Contains element
     */
    private boolean contains(final int element) {
        //TODO: Implement
        throw new RuntimeException("TO BE IMPLEMENTED");
    }

    public class MyStackIsFullException extends RuntimeException {
        public MyStackIsFullException(final String message) {
            super(message);
        }
    }

    public class MyStackIsEmptyException extends RuntimeException {
        public MyStackIsEmptyException(final String message) {
            super(message);
        }
    }

    //////////////////////////////////////////////////////////////////////////
    // MAIN: Driver Program
    //////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        System.out.println("*********** This is stack Data Structure ********* ");
        MyStack stack = new MyStack(5);

        stack.push(10);
        stack.display();

        stack.pop();
        stack.display();

        try {
            stack.pop();
        } catch (RuntimeException e) {
            System.out.println("ERROR: As expected");
        }

        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);

        stack.display();
        try {
            stack.push(60);
        } catch (RuntimeException e) {
            System.out.println("ERROR: As expected");
        }

    }

}
