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
 * Class: Main
 */
public class main
{
    static final int NUM_OF_LETTERS = 1500000;
    static String[] sortArray = new String[NUM_OF_LETTERS];
    static int count;
    static long startTime, elaspedTime;
    
   public static void main (String[] args)
   {
       Scanner kb = new Scanner(System.in);
       String inputTxt;
       String input;
       String[] combineWords = new String[4];   //Create 3 Strings for the output file
       
       System.out.println("Which file would you like to sort from? | Ex: perm or sorted |");
       combineWords[0]= kb.next();
       
       System.out.println("How many words would you liked to have sorted? | Ex: 15K, 30K, 45K |");
       combineWords[1]= kb.next();
       
       combineWords[2] = ".txt";
       
       combineWords[3] = combineWords[0] + combineWords[1] + combineWords[2]; 
       
       inputTxt = combineWords[3];
       
       System.out.println();
       System.out.println("You will be sorting the file " + inputTxt + ".");
       System.out.println();
       
       System.out.println("Which sorting algorithm would you like to use | Ex: Insertion, Merge, Heap |");
       input = kb.next();
       
       
       if(input.equals("Insertion")) // InsertionSort is chosen
       {
           InsertionSort insertion = new InsertionSort();
           timerStart();
           insertion.InsertionSort(sortArray, inputFile(inputTxt));
           timerStop();
           String fileName = "IS" + combineWords[1] + combineWords[2];
           PrintWriter writer = null;
           try{ 
                writer = new PrintWriter(fileName);
           }catch(FileNotFoundException e){
                System.out.println("could not make file");
           }
           for(int i = 0; i < count; i++)
            {
                writer.print(sortArray[i]);
            }
            writer.close();
       }
       else if(input.equals("Merge")) // MergeSort is chosen
       {
           MergeSort merge = new MergeSort();
           timerStart();
           merge.mergeSort(sortArray, inputFile(inputTxt));
           
           timerStop();
           String fileName = "MS" + combineWords[1] + combineWords[2];
           
           PrintWriter writer = null;
           try{ 
                writer = new PrintWriter(fileName);
           }catch(FileNotFoundException e){
                System.out.println("could not make file");
           }
           for(int i = 0; i < count; i++)
            {
                writer.print(sortArray[i]);
            }
            writer.close();
        
       }
       else if(input.equals("Heap")) // HeapSort is chosen
       {
           HeapSort heapSort = new HeapSort();
           timerStart();
           heapSort.heapSort(sortArray, inputFile(inputTxt));
           timerStop();
           
           String fileName = "HS" + combineWords[1] + combineWords[2];
           PrintWriter writer = null;
           try{ 
                writer = new PrintWriter(fileName);
           }catch(FileNotFoundException e){
                System.out.println("could not make file");
           }
           for(int i = 0; i < count; i++)
            {
                writer.print(sortArray[i]);
            }
            writer.close();
       }
      
   }
    
   public static int inputFile(String input) //Reads from a text file
   {
       Scanner fileIn = null;
       try{
            fileIn = new Scanner(new File(input));
        }
       catch(Exception e){
            System.out.println("could not find file");
        }
   
      while(fileIn.hasNext())
      {
           String letter = fileIn.next();
           sortArray[count] = letter;
           count++; 
      }
       return count;
   }
   
   static void timerStart()
   {
       startTime = System.currentTimeMillis();
   }
   
   static void timerStop()
   {
       double elapsedNanos = System.currentTimeMillis() - startTime;
       double timeInSeconds;
       System.out.println();
       System.out.println("This sort took " + elapsedNanos + " ms to compute.");
   }
}

