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
 * Class: MergeSort
 */
public class MergeSort extends main
{

    public static void mergeSort(String[] sortArray, int count) {
        if (count < 2) {
            return;
        }
        int mid = count / 2;
        String[] l = new String[mid];
        String[] r = new String[count - mid];
 
        for (int i = 0; i < mid; i++) {
            l[i] = sortArray[i];
         }
    
        for (int i = mid; i < count; i++) {
            r[i - mid] = sortArray[i];
        }
        mergeSort(l, mid);
        mergeSort(r, count - mid);
        merge(sortArray, l, r, mid, count - mid);
    }

    public static void merge(String[] a, String[] l, String[] r, int left, int right){
        int i = 0, j = 0, k = 0;
        while (i < left && j < right)
        {
            if (l[i].compareTo(r[j]) <= 0) {
                a[k++] = l[i++];
            }
            else {
                a[k++] = r[j++];
            }
        }
        while (i < left) {
            a[k++] = l[i++];
        }
        while (j < right) {
            a[k++] = r[j++];
        }
    }
}
