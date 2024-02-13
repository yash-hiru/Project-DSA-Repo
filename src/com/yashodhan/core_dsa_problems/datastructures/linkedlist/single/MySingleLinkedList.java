package com.yashodhan.core_dsa_problems.datastructures.linkedlist.single;

import com.yashodhan.core_dsa_problems.commons.MyLogger;

//////////////////////////////////////////////////////////////////////////
// class MySingleLinkedList
//////////////////////////////////////////////////////////////////////////
class MySingleLinkedList {
    private MyNode head;
    private int size;

    /////////////////////////////////////
    //constructor
    public MySingleLinkedList() {
        head = null;
        size = 0;
    }

    /////////////////////////////////////
    // Append (Special insert- Insert at end)
    public void PRACTICE_append(MyNode node) {
        // If Head is null
        if (head == null) {
            head = node;
        } else {
            // Iterate to get end of the linkedlist
            MyNode end = iterateAndGetEnd(false);
            // Insert new element
            end.setNext(node);
        }
        MyLogger.info("Node Inserted:" + node.getData());
        // Increment size
        size += 1;
    }

    /////////////////////////////////////
    private MyNode iterateAndGetEnd(boolean shouldLog) {
        // If Head is null
        if (head == null) {
            return null;
        } else {
            //Traverse and go to end
            MyNode prev = head;
            MyNode ptr = head.getNext();
            System.out.print("" + prev.getData() + "-->");
            while (ptr != null){
                System.out.print("" + ptr.getData() + "-->");
                prev = ptr;
                ptr = ptr.getNext();
            }
            return prev;
        }
    }


    /////////////////////////////////////
    // Print
    public void print() {
        MyLogger.info(">>>>>>>>>> List Contents >>>>>>>>");
        if (head == null) {
            MyLogger.info("Head:" + null);
        } else {
            MyLogger.info("Head:" + head.getData());
        }
        MyLogger.info("Size:" + size);
        MyLogger.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        iterateAndGetEnd(true);
    }

    /////////////////////////////////////
    // Remove (requires backup of ptr.next)
    // set prev.next = ptr.next
    // this will remove ptr itself..
    // Update size

    /////////////////////////////////////
    // RemoveAt (same as remove..Just set for loop with counter)


    /////////////////////////////////////
    // reverseInPlace
    public void PRACTICE_reverseInPlace(){
        MyLogger.info("Reverse LinkedList in Place");
        // Case1- Standard case with 3 or more elements (TODO- Have modular function)
        // TODO Case2- Base Case- 2 elements (Have modular function)
        // TODO Case3-Base case 1 element (Have modular function)
        // TODO Case4- Empty List (Have modular function)

        MyNode ptr1 = head;
        MyNode ptr2 = head.getNext();
        MyNode ptr3 = head.getNext().getNext();
        boolean firstTime = true;
        while(ptr3 !=null) {
            // Swap first2 pointers
            ptr2.setNext(ptr1);
            if(firstTime) {
                ptr1.setNext(null);
                firstTime = false;
            }
            // Make Pointer Adjustment
            ptr1 = ptr2;
            ptr2 = ptr3;
            ptr3 = ptr2.getNext();
            // Take back
            // Progress
        }
        ptr2.setNext(ptr1);
        // Update head
        head=ptr2;
    }

    /////////////////////////////////////
    // findMid
    MyNode PRACTICE_findMid() {
        // Strategy: Get two pointer, Increment second pointer by two , Return first pointer data once
        // Base and corner cases: 1 (Empty List--return nothing)
        //             2 (Single element--Return it)
        //             3 (2 elements--Return either of two)
        //             4 (Even number of elements-- Return ceil of N/2 .. Can take it as input (Nice to have)
        //case1
        MyNode ptr1 = head;
        if(ptr1 == null) {
            return null;
        }
        //case2
        MyNode ptr2 = ptr1.getNext();
        if (ptr2 == null){
            return ptr1;
        }
        //case3, 4 (Traverse till End by faster ptr2)
        // Ptr1 and Ptr2 not null so far
        while(ptr2 != null) {
            ptr1 = ptr1.getNext(); // 1X speed
            ptr2= ptr2.getNext(); //1X Speed
            if(ptr2 == null) {
                break;
            }
            ptr2 = ptr2.getNext(); //2X speed
        }
        return ptr1; //Ptr1 is the mid
    }

    /////////////////////////////////////
    // SumLists ( two integers)
    public static int PRACTICE_getTheSumOf(MySingleLinkedList list1, MySingleLinkedList list2) {
        // Strategy - Each list contains (LSB to MSB digits), Use base 10, Increment base as and when you visit next
        // FUNC- Calculate number for each list
        int num1 = list1.getIntegerRepresentation();
        int num2 = list2.getIntegerRepresentation();
        // EXPR- Add them together
        return num1 +num2;
        // RETURN int: Return result
    }

    private int getIntegerRepresentation() {
        // FUNC- Calculate number
        MyNode ptr = head;
        int result = 0;
        final int BASE_TEN = 10;
        int baseExponential = 0;
        while (ptr != null) {
            // Read the value
            int var = ptr.getData();
            // Increment base
            int coef = new Double(Math.pow(BASE_TEN, baseExponential)).intValue();
            // Multiply var and coef and add to result
            result += var*coef;
            // increment baseExponential
            baseExponential +=1;
            ptr = ptr.getNext();
        }
        return result;
    }


    //////////////////////////////////////////////////////////////////////////
    // MAIN: Driver Program
    //////////////////////////////////////////////////////////////////////////
    public static void main(String args[]) {

        MySingleLinkedList linkedList = TEST_get_sample();
        //////// Test ReverstInPlace
        linkedList.PRACTICE_reverseInPlace();
        linkedList.print();

        /////// Find mid
        linkedList = new MySingleLinkedList();
        linkedList.PRACTICE_append(new MyNode(10));
        linkedList.PRACTICE_append(new MyNode(20));
        linkedList.PRACTICE_append(new MyNode(30));
        linkedList.PRACTICE_append(new MyNode(40)); //Mid
        linkedList.PRACTICE_append(new MyNode(50));
        linkedList.PRACTICE_append(new MyNode(60));
        linkedList.PRACTICE_append(new MyNode(70));
        linkedList.PRACTICE_findMid().print();

        ///////// Add two list (as number representation)
        MySingleLinkedList listNumber1 = new MySingleLinkedList();
        listNumber1.PRACTICE_append(new MyNode(1));
        listNumber1.PRACTICE_append(new MyNode(2));
        listNumber1.PRACTICE_append(new MyNode(3));

        MySingleLinkedList listNumber2 = new MySingleLinkedList();
        listNumber2.PRACTICE_append(new MyNode(0));
        listNumber2.PRACTICE_append(new MyNode(7));
        listNumber2.PRACTICE_append(new MyNode(5));
        System.out.flush();
        MyLogger.info("Number addition- "+PRACTICE_getTheSumOf(listNumber1, listNumber2), true);
    }

    ///////////////////////////////// Get Test data
    public static MySingleLinkedList TEST_get_sample(){
        MySingleLinkedList linkedList = new MySingleLinkedList();
        linkedList.print();

        linkedList.PRACTICE_append(new MyNode(10));
        linkedList.PRACTICE_append(new MyNode(20));
        linkedList.PRACTICE_append(new MyNode(30));
        linkedList.PRACTICE_append(new MyNode(40));
        linkedList.print();
        return linkedList;
    }

}
