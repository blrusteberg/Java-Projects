import java.io.*; 
import java.util.*;
/**
 * Author: Blake Rusteberg
 * Program: Project 4
 * Date: 3/31/2019 
 * Class: minHeap
 */

public class minHeap extends main
{
    int capacity;
    int currentSize;
    HeapNode[] minH;
    int [] indexes; 

    public minHeap(int capacity) 
    {
        this.capacity = capacity;
        minH = new HeapNode[capacity + 1];
        indexes = new int[capacity];
        minH[0] = new HeapNode();
        minH[0].key = Integer.MIN_VALUE;
        minH[0].vertex= -1;
        currentSize = 0;
    }

    public void swap(int a, int b) 
    {
        HeapNode temp = minH[a];
        minH[a] = minH[b];
        minH[b] = temp;
    }

    public boolean isEmpty() 
    {
        return currentSize == 0;
    }

    public int heapSize()
    {
        return currentSize;
    }
    
    public void display()
    {
        for (int i = 0; i <= currentSize; i++) 
        {
            System.out.println(" " + minH[i].vertex + "   key   " + minH[i].key);
        }
        System.out.println("________________________");
    }

    public void insert(HeapNode heap) 
    {
        currentSize++;
        int idx = currentSize;
        minH[idx] = heap;
        indexes[heap.vertex] = idx;
        moveUp(idx);
    }

    public void moveUp(int pos) 
    {
        int parentIdx = pos/2;
        int currentIdx = pos;
        
        while (currentIdx > 0 && minH[parentIdx].key > minH[currentIdx].key) 
        {
            HeapNode currentNode = minH[currentIdx];
            HeapNode parentNode = minH[parentIdx];


            indexes[currentNode.vertex] = parentIdx;
            indexes[parentNode.vertex] = currentIdx;
            swap(currentIdx,parentIdx);
            currentIdx = parentIdx;
            parentIdx = parentIdx/2;
        }
    }

    public HeapNode extractMin() 
    {
        HeapNode min = minH[1];
        HeapNode lastNode = minH[currentSize];
        
        indexes[lastNode.vertex] = 1;
        minH[1] = lastNode;
        minH[currentSize] = null;
        sinkDown(1);
        currentSize--;
        return min;
    }

    public void sinkDown(int k) 
    {
        int smallest = k;
        int leftChildIdx = 2 * k;
        int rightChildIdx = 2 * k + 1;
        
        if (leftChildIdx < heapSize() && minH[smallest].key > minH[leftChildIdx].key) 
        {
            smallest = leftChildIdx;
        }
        
        if (rightChildIdx < heapSize() && minH[smallest].key > minH[rightChildIdx].key) 
        {
            smallest = rightChildIdx;
        }
        
        if (smallest != k) 
        {
            HeapNode smallestNode = minH[smallest];
            HeapNode kNode = minH[k];

            indexes[smallestNode.vertex] = k;
            indexes[kNode.vertex] = smallest;
            swap(k, smallest);
            sinkDown(smallest);
        }
    }

}
