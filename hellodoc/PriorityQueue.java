package com.example.ameya.hellodoc;

/**
 * Created by ameya on 10/12/17.
 */

import java.util.Vector;
import java.lang.Exception;


class PriorityQueue
{
String name,age;

    PriorityQueue(String n,String age)
    {
        name=n;
        this.age=age;
    }
    private Vector<Integer> A;


    public PriorityQueue()
    {
        A = new Vector();
    }


    public PriorityQueue(int capacity)
    {
        A = new Vector(capacity);
    }


    private int parent(int i)
    {

        if (i == 0)
            return 0;

        return (i - 1) / 2;
    }


    private int LEFT(int i)
    {
        return (2 * i + 1);
    }


    private int RIGHT(int i)
    {
        return (2 * i + 2);
    }


    void swap(int x, int y)
    {

        Integer temp = A.get(x);
        A.setElementAt(A.get(y), x);
        A.setElementAt(temp, y);
    }


    private void heapify_down(int i)
    {

        int left = LEFT(i);
        int right = RIGHT(i);

        int largest = i;



        if (left < size() && A.get(left) > A.get(i))
            largest = left;

        if (right < size() && A.get(right) > A.get(largest))
            largest = right;

        if (largest != i)
        {

            swap(i, largest);


            heapify_down(largest);
        }
    }


    private void heapify_up(int i)
    {

        if (i > 0 && A.get(parent(i)) < A.get(i))
        {

            swap(i, parent(i));


            heapify_up(parent(i));
        }
    }


    public int size()
    {
        return A.size();
    }


    public Boolean isEmpty()
    {
        return A.isEmpty();
    }


    public void add(Integer key)
    {

        A.addElement(key);


        int index = size() - 1;
        heapify_up(index);
    }


    public Integer poll()
    {
        try {

            if (size() == 0)
                throw new Exception("Index is out of range (Heap underflow)");


            int root = A.firstElement();	// or A.get(0);


            A.setElementAt(A.lastElement(), 0);
            A.remove(size()-1);


            heapify_down(0);


            return root;
        }

        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }



    public Integer peek()
    {
        try {

            if (size() == 0)
                throw new Exception("Index out of range (Heap underflow)");


            return A.firstElement();	// or A.get(0);
        }

        catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }


    public void clear()
    {
        System.out.print("Emptying queue: ");
        while (!A.isEmpty()) {
            System.out.print(poll() + " ");
        }
        System.out.println();
    }


    public Boolean contains(Integer i)
    {
        return A.contains(i);
    }


    public Integer[] toArray()
    {
        return A.toArray(new Integer[size()]);
    }


    public static void main (String[] args)
    {


        PriorityQueue pq = new PriorityQueue(10);


        pq.add(3);
        pq.add(2);
        pq.add(15);


        System.out.println("Priority Queue Size is " + pq.size());


        Integer searchKey = 2;

        if (pq.contains(searchKey))
            System.out.println("Priority Queue contains " + searchKey + "\n");


        pq.clear();

        if (pq.isEmpty())
            System.out.println("Queue is Empty");

        System.out.println("\nCalling remove operation on an empty heap");
        System.out.println("Element with highest priority is " + pq.poll());

        System.out.println("\nCalling peek operation on an empty heap");
        System.out.println("Element with highest priority is " + pq.peek());


        pq.add(5);
        pq.add(4);
        pq.add(45);


        Integer[] I = pq.toArray();
        System.out.print("\nPrinting array: ");
        for (int i : I)
            System.out.print(i + " ");

        System.out.println("\n\nElement with highest priority is " + pq.poll());
        System.out.println("Element with highest priority is " + pq.peek());
    }
}