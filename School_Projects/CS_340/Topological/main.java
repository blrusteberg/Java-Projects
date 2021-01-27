import java.util.*;
import java.io.PrintWriter;
import java.io.File;
import java.lang.Object;
import java.io.FileNotFoundException;
import java.lang.NullPointerException;
/**
 * Author: Blake Rusteberg
 * Program: Project 3
 * Date: 2/19/2019 
 * Class: Main
 */
public class main
{
    public static ArrayList<Integer> vertArray[];
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        String fileName;

        System.out.println("Enter the name of the File you would like to use. | Ex: graphin-c1.txt, graphin-c2.txt, graphin-DAG.txt |");
        fileName = kb.next();
        System.out.println();
        
        System.out.print("Depth First Search: ");
        
        try{
            openFile(fileName);
        }catch(FileNotFoundException e){
            System.err.println("File: " + e.getMessage());
            System.exit(1);
        }
    }

    public static void openFile(String fileName) throws FileNotFoundException {
        String line = null;
        Graph g1 = new Graph(10);
        try(Scanner inputFile = new Scanner(new File(fileName))) {
            while(inputFile.hasNext()) {
                line = inputFile.nextLine();
                String str = line;
                String newStr = str.replace(":", "");
                String[] numbers = newStr.split(" ");
                g1.array(numbers, vertArray);

            }
            
            g1.DFS(vertArray);
            System.out.println();
            if(g1.detectCyclic())
            {
                System.out.println();
                System.out.println("The graph is Cyclic.");
            }
            else
            {
                System.out.println();
                System.out.println("The graph is Acyclic.");
                g1.topologicalSort();
            }
            inputFile.close();
        }

    }

}

