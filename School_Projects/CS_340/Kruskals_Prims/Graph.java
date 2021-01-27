import java.io.*; 
import java.util.*;
/**
 * Author: Blake Rusteberg
 * Program: Project 4
 * Date: 3/31/2019 
 * Class: Graph
 */
public class Graph extends main{ 

    private int verts = 9;
    ArrayList<Edge> allEdges = new ArrayList<>();
    ArrayList<Edge>[] adjList;

    Graph(int verts) {
        this.verts = verts;
        adjList = new ArrayList[verts];
            
        for (int i = 0; i < verts ; i++)
        {
            adjList[i] = new ArrayList<>();
        }

    }

    void addEdgeKruskals(int source, int dest, int weight) 
    { 
        Edge e = new Edge(source, dest, weight);
        allEdges.add(e);
    } 
    
    public void addEdgePrims(int source, int destination, int weight) 
    {
            Edge e = new Edge(source, destination, weight);
            adjList[source].add(e);

            e = new Edge(destination, source, weight);
            adjList[destination].add(e); 
    }

    public void array(String[] numbers, ArrayList<Edge> verticesArray) {
        boolean check = false;
        int source = 0;
        int dest = 0;
        int weight = 0;

        for(int i = 0; i < numbers.length; i++) 
        {
            if(i < numbers.length-1) 
            {
                if(check == false) 
                {
                    source = Integer.parseInt(numbers[i]);
                    check = true;
                }
                dest = Integer.parseInt(numbers[i+1]);
                if(i % 2 == 0)
                {
                    weight = Integer.parseInt(numbers[i+2]);
                    addEdgeKruskals(source, dest, weight);
                    addEdgePrims(source, dest, weight);
                }
            } 
            else 
            {
                break;
            }
        }

    }

    public void KruskalsMinimumSpanningTree()
    {
        PriorityQueue<Edge> edgeQueue = new PriorityQueue<>(allEdges.size(),
                Comparator.comparingInt(o -> o.weight));
        for (int i = 0; i < allEdges.size(); i++)
        {
            edgeQueue.add(allEdges.get(i));
        }
        int[] parent = new int[verts];

        makeSet(parent);

        ArrayList<Edge> minimumSpanningTree = new ArrayList<>();
        int index = 0;
        int count = 0;
        while (allEdges.size() != 0 )
        {
            if(count == allEdges.size())
            {
                break;
            }
            Edge e = edgeQueue.remove();

            int setX = find(parent, e.source);
            int setY = find(parent, e.destination);
            count++;
            if(setX == setY) 
            { 

            }
            else
            {
                minimumSpanningTree.add(e);
                index++;
                union(parent, setX, setY);
            }

        }

        System.out.println("Kruskal's Minimum Spanning Tree: ");
        printGraph(minimumSpanningTree);

        String fileOutKruskal = "kruskalout.txt";

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileOutKruskal);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        writer.println("Kruskal's Minimum Spanning Tree: ");
        for (int i = 0; i < minimumSpanningTree.size() ; i++)
        {
            Edge e = minimumSpanningTree.get(i);
            writer.println("Edge(" + (i+1) + ")" + " = " + "(" + e.source 
                + " ---> " + e.destination + ")");
        }

        writer.close();
    }

    public void makeSet(int [] parent)
    {
        for (int i = 0; i <verts; i++) 
        {
            parent[i] = i;
        }
    }

    public int find(int [] p, int v){

        if(p[v] != v)
        {
            return find(p, p[v]);
        }
        return v;
    }

    // Union by Depth
    public void union(int [] p, int x, int y)
    {
        int setXParent = find(p, x);
        int setYParent = find(p, y);
        p[setYParent] = setXParent;
    }

    public void PrimsMinimumSpanningTree()
    {
        boolean[] inHeap = new boolean[verts];
        ResultSet[] resultSet = new ResultSet[verts];

        int [] key = new int[verts];

        HeapNode[] heap_Nodes = new HeapNode[verts];
        for (int i = 0; i < verts; i++) 
        {
            heap_Nodes[i] = new HeapNode();
            heap_Nodes[i].vertex = i;
            heap_Nodes[i].key = Integer.MAX_VALUE;

            resultSet[i] = new ResultSet();
            resultSet[i].result_parent = -1;

            inHeap[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        heap_Nodes[0].key = 0;
        minHeap mH = new minHeap(verts);

        for(int i = 0; i < verts; i++)
        {
            mH.insert(heap_Nodes[i]);
        }

        while(!mH.isEmpty())
        {
            HeapNode extNode = mH.extractMin();

            int extVert = extNode.vertex;
            inHeap[extVert] = false;

            ArrayList<Edge> list = adjList[extVert];
            
            for (int i = 0; i < list.size(); i++)
            {
                Edge edge = list.get(i);
                
                if(inHeap[edge.destination]) 
                {
                    int destination = edge.destination;
                    int newKey = edge.weight;
                    
                    if(key[destination] > newKey)
                    {
                        decreaseKey(mH, newKey, destination);
                        resultSet[destination].result_parent = extVert;
                        resultSet[destination].result_weight = newKey;
                        key[destination] = newKey;
                    }
                }
            }
        }
        printMST(resultSet);
            
        String fileOutPrim = "primsout.txt";

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(fileOutPrim);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        int total_min_weight = 0;
        writer.println("Prim's Minimum Spanning Tree: ");
        for (int i = 1; i < verts; i++) 
        {
            if (resultSet[i].result_weight == 0)
            {
                break;
            }
            writer.println("Edge(" + i + ") = " + i + " ---> " + resultSet[i].result_parent);
            total_min_weight += resultSet[i].result_weight;
        }
        writer.println("Prim's Total Minimum Key: " + total_min_weight);
        writer.close();
    }

    public void decreaseKey(minHeap minHeap, int newKey, int vertex)
    {
        int index = minHeap.indexes[vertex];
        HeapNode node = minHeap.minH[index];
        node.key= newKey;
        minHeap.moveUp(index);
    }

    public void printMST(ResultSet[] resultSet)
    {
        int total_min_weight = 0;
        System.out.println("Prim's Minimum Spanning Tree: ");
        
        for (int i = 1; i < verts; i++) 
        {
            if (resultSet[i].result_weight == 0)
            {
                break;
            }
            System.out.println("Edge(" + i + ") " + i + " ---> " + resultSet[i].result_parent +
                " weight: " + resultSet[i].result_weight);
            total_min_weight += resultSet[i].result_weight;
        }
        System.out.println("Prim's Total Minimum Key: " + total_min_weight);
    }

    public void print()
    {
        printGraph(allEdges);
    }

    public void printGraph(ArrayList<Edge> edgeList)
    {
        for (int i = 0; i < edgeList.size() ; i++)
        {
            Edge e = edgeList.get(i);
            System.out.println("Edge(" + (i+1) + ") " + e.source +
                " ---> " + e.destination +
                " weight: " + e.weight);
        }
    }
       

}

