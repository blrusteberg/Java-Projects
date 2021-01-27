
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
 * Class: Edge
 */
public class Edge extends main {
        int source;
        int destination;
        int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }