
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.lang.Object;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;

/**
 * Author: Blake Rusteberg
 * Program: Project 1 Sorting and Heaps
 * Date: 2/3/2019 
 * Class: HeapSort
 */
public class HeapSort extends main
{
     public void buildHeap(String[] sortArray, int count) {
        long startTime = System.currentTimeMillis();
        
        for(int i = (sortArray.length/2) - 1; i >= 0; i--) {
            heapify(sortArray, count, i);
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println();
        System.out.println("Total execution time to build the heap: " + (endTime-startTime) + " ms");
    }   
    
    public void heapSort(String sortArray[], int count) 
    {
        buildHeap(sortArray, count);
        for (int i = count-1; i>=0; i--) 
        { 
            String temp = sortArray[0]; 
            sortArray[0] = sortArray[i]; 
            sortArray[i] = temp; 
  
            heapify(sortArray, i, 0); 
        } 
    }
    
    void heapify(String sortArray[], int n, int i) 
    { 
        int largest = i; 
        int l = 2*i + 1; 
        int r = 2*i + 2; 
  
        if (l < n && sortArray[l].compareTo(sortArray[i]) > 0) 
            largest = l; 
  
        if (r < n && sortArray[r].compareTo(sortArray[largest]) > 0) 
            largest = r; 
  
        if (largest != i) 
        { 
            String swap = sortArray[i]; 
            sortArray[i] = sortArray[largest]; 
            sortArray[largest] = swap; 
  
            heapify(sortArray, n, largest); 
        } 
    } 
    } 
