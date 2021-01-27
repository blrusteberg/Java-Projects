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
 * Class: Knapsack
 */
public class Knapsack extends main
{
    Knapsack() {}

    static int findMax(int x, int y)
    {
        return (x > y)? x : y;
    }

    static void knapsackAlgo(int W, int wArr[], int vArr[], int n)
    {
        int i, k;
        int arr[][] = new int [n+1][W+1];

        for (i = 0; i <= n; i++)
        {
            for (k = 0; k <= W; k++)
            {
                if(i == 0 || k == 0)
                {
                    arr[i][k] = 0;
                }
                else if(wArr[i - 1] <= k)
                {
                    arr[i][k] = findMax(vArr[i - 1] + arr[i - 1][k - wArr[i - 1]], arr[i - 1][k]);
                
                }
                else
                {
                    arr[i][k] = arr[i - 1][k];
                }

            }
        }
        
        int result = arr[n][W]; 
        System.out.println("Total value: " + result); 
        k = W; 
        
        for (i = n; i > 0 && result > 0; i--) 
        { 
            if (result == arr[i - 1][k])
            {
                continue; 
            }
            else 
            { 
                System.out.print("Item: " + i + " ");
                System.out.print("Weight: " + wArr[i - 1] + " ");
                System.out.println("Value: " + vArr[i - 1]);
                
                result = result - vArr[i - 1]; 
                k = k - wArr[i - 1]; 
            } 
        } 
    } 
}

   
