
import java.io.*; 
import java.util.*;

/**
 * Author: Blake Rusteberg
 * Program: Project 3
 * Date: 2/19/2019 
 * Class: Graph
 */
public class Graph extends main{
    private int V = 9;   // No. of vertices 
    private ArrayList<Integer> verticesArray[]; 

    Graph(int v) 
    { 
        V = v; 
        verticesArray = new ArrayList[v]; 
        for (int i = 0; i < v; ++i) 
        {
            verticesArray[i] = new ArrayList(); 
        }
    } 

    void addEdge(int l, int q) 
    { 
        verticesArray[l].add(q);   
    } 

    public void array(String[] numbers, ArrayList<Integer> verticesArray[]) {
        boolean check = false;
        int v1 = 0;
        int v2 = 0;

        for(int i = 0; i < numbers.length; i++) 
        {
            if(i < numbers.length-1) 
            {
                if(check == false) 
                {
                    v1 = Integer.parseInt(numbers[i]);
                    check = true;
                }
                v2 = Integer.parseInt(numbers[i+1]);
                addEdge(v1,v2);
            } 
            else 
            {
                break;
            }
        }

    }

    public void DFSRecursive(int node, boolean visited[]) 
    {
        visited[node] = true;
        if (node != 0)
        {
            System.out.print(node + " "); 
        }

        Iterator<Integer> it = verticesArray[node].listIterator();

        while (it.hasNext()) 
        { 
            int n = it.next(); 
            if (!visited[n]) 
                DFSRecursive(n, visited); 
        }  

    }

    public void DFS(ArrayList<Integer> verticesArray[])
    {
        boolean visited[] = new boolean[V];

        for (int i = 0; i < V; i++)
        {
            if(visited[i] == false)
            {
                DFSRecursive(i, visited);
            }
        }
    }

    public boolean detectCyclic() {
        boolean visited[] = new boolean[V];
        boolean recursiveArray[] = new boolean[V];

        for (int i = 0; i < V; i++) 
        {
            if (cyclicRecursive(i, visited, recursiveArray))
            {
                return true;
            }
        }
        return false;
    }

    public boolean cyclicRecursive(int vertex, boolean[] visited, boolean[] recursiveArray) {
        visited[vertex] = true;
        recursiveArray[vertex] = true;

        for (int i = 0; i < verticesArray[vertex].size(); i++) 
        {
            int adjVertex = verticesArray[vertex].get(i);
            if (!visited[adjVertex] && cyclicRecursive(adjVertex, visited, recursiveArray))
            {
                return true;
            } else if (recursiveArray[adjVertex])
            {
                return true;
            }
        }
        recursiveArray[vertex] = false;
        return false;
    }
    
    void topologicalSortUtil(int node, boolean visited[], Stack stack) 
    { 
        visited[node] = true; 
        Integer i; 
  
        Iterator<Integer> it = verticesArray[node].iterator(); 
        while (it.hasNext()) 
        { 
            i = it.next(); 
            if (!visited[i]) 
            {
                topologicalSortUtil(i, visited, stack); 
            }
        } 
   
        stack.push(new Integer(node)); 
    } 
  
    void topologicalSort() 
    { 
        Stack stack = new Stack(); 
  
        boolean visited[] = new boolean[V]; 
        for (int i = 1; i < V; i++) 
        {
            visited[i] = false;
        }
        
        for (int i = 1; i < V; i++)
        {  
            if (visited[i] == false)
            {
                topologicalSortUtil(i, visited, stack); 
            }
        }
        System.out.println();
        System.out.printf("Topologically Sorted: ");
        while (stack.empty()==false)
        {
            System.out.print(stack.pop() + " "); 
        }                             
    } 
}
