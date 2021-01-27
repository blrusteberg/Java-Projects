import java.util.*;
import java.io.PrintWriter;
import java.io.File;
import java.lang.Object;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
/**
 * Author: Blake Rusteberg
 * Program: Project 4
 * Date: 3/31/2019 
 * Class: main
 */
public class main
{
    public static ArrayList<Edge> edgeArray;
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String fileName;

        System.out.println("Enter the name of the File you would like to use. | Ex: graphin_w_ud.txt, graphin_w_ud2.txt |");
        fileName = kb.next();
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
        String line = null;
        Graph g1 = new Graph(9);
        try(Scanner inputFile = new Scanner(new File(fileName))) 
        {
            while(inputFile.hasNext()) 
            {
                line = inputFile.nextLine();
                String str = line;
                String newStr = str.replace(":", "");
                String[] numbers = newStr.split(" ");
                g1.array(numbers, edgeArray);

            }
           
            g1.print();
            
            System.out.println();
            System.out.println();
            
            g1.KruskalsMinimumSpanningTree();
            
            System.out.println();
            System.out.println();
            
            g1.PrimsMinimumSpanningTree();
            
            System.out.println();
      
            inputFile.close();
        }

    }

}

