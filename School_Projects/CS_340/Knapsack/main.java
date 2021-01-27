import java.util.*;
import java.io.PrintWriter;
import java.io.File;
import java.lang.Object;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
/**
 * Author: Blake Rusteberg
 * Program: Project 6
 * Date: 4/22/2019 
 * Class: main
 */
public class main
{
    public static int n = 10;
    public static int[] vArr = new int[10];
    public static int[] wArr = new int[10];
    public static void main(String[] args) {

        Scanner kb = new Scanner(System.in);
        String fileName = "knapsack1.txt";

        System.out.println();

        try{
            openFile(fileName);
        }catch(FileNotFoundException e){
            System.err.println("File: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void openFile(String fileName) throws FileNotFoundException 
    {
        int value;
        int weight;
        int numOfItems;
        int weightCap;
        int count = 0;
        
        try(Scanner inputFile = new Scanner(new File(fileName))) 
        {
            numOfItems = inputFile.nextInt();
            weightCap = inputFile.nextInt();
            while(inputFile.hasNext()) 
            {
                weight = inputFile.nextInt();
                wArr[count] = weight;
                value = inputFile.nextInt();
                vArr[count] = value;
                count++;
            }

        }

        Knapsack knap = new Knapsack();

        knap.knapsackAlgo(weightCap, wArr, vArr, numOfItems);

    }

}
