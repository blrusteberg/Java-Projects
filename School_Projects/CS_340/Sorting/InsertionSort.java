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
 * Class: InsertionSort
 */
public class InsertionSort extends main
{

    public static void InsertionSort(String[] sortArray, int count)
   {
       String temp;
       for(int i = 1; i < count; i++) 
       {
               temp = sortArray[i];
               int j = 0;
               for(j = i; j > 0; j--){
                    if(temp.compareTo(sortArray[j - 1]) < 0)
                         sortArray[j] = sortArray[j - 1];
                    else
                        break;
                    }
               sortArray[j] = temp;
       }
      
    }
    
}