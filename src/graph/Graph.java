package graph;

/**
 * A class that represents a graph: stores the array of city nodes, the
 * adjacency list, as well as the hash table that maps city names to node ids.
 * Nodes are cities (of type CityNode); edges connect them and the cost of each edge
 * is the distance between the cities.
 * Fill in code in this class. You may add additional methods and variables.
 * You are required to implement a PriorityQueue from scratch, instead of using Java's built in.
 */

import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class Graph {
    private CityNode[] nodes; // nodes of the graph
    private Edge[] adjacencyList; // adjacency list; for each vertex stores a linked list of edges
    private int numEdges; // total number of edges
    // Add other variables as needed


    /**
     * Constructor. Reads graph info from the given file,
     * and creates nodes and edges of the graph.
     *
     * @param filename name of the file that has nodes and edges
     */
    public Graph(String filename) throws java.io.FileNotFoundException{
       // FILL IN CODE: load the graph from the given file
        File file = new File ("input/"+filename); // this has no error
        Scanner input = new Scanner(file);
        String line = input.nextLine(); // should be NODES
        line = input.nextLine(); // should tell us how many iterations to go through
        int iterations = Integer.parseInt(line);
        nodes = new CityNode[iterations];//initiate nodes
        for(int i = 0; i < iterations; i++){
            line = input.nextLine();
            String data[] = line.split(" ");
            nodes[i] = new CityNode(data[0], Double.parseDouble(data[1]), Double.parseDouble(data[2]));
        }
        input.nextLine();

        System.out.println(line);



            int id1 = -1;
            int id2 = -1;
            adjacencyList = new Edge[100]; // CHECK HOW TO DO THIS RIGHT, segfault if don't initialize
            int numEdges = 0; // keeps track of number of edges
            for(int i = 0; i < iterations; i++) {
                if(input.hasNextLine()) {
                    line = input.nextLine();
                    String data[] = line.split(" ");
//                    System.out.println("here");
                    if (nodes[i].getCity().equals(data[0]))
                        id1 = i;
                    if (nodes[i].getCity().equals(data[1]))
                        id2 = i;
//                    System.out.println("here");
                    adjacencyList[i] = new Edge(id1, id2, Integer.parseInt(data[2]));
                    numEdges++;
                }
                else
                    i = iterations; //break
            }
        System.out.println("here");



        }







    /**
     * Return the number of nodes in the graph
     * @return number of nodes
     */
    public int numNodes() {
        return nodes.length;
    }

    /** Return the head of the linked list that contains all edges outgoing
     * from nodeId
     * @param nodeId id of the node
     * @return head of the linked list of Edges
     */
    public Edge getFirstEdge(int nodeId) {
        return adjacencyList[nodeId];
    }

    /**
     * Return the edges of the graph as a 2D array of points.
     * Called from GUIApp to display the edges of the graph.
     *
     * @return a 2D array of Points.
     * For each edge, we store an array of two Points, v1 and v2.
     * v1 is the source vertex for this edge, v2 is the destination vertex.
     * This info can be obtained from the adjacency list
     */
    public Point[][] getEdges() {
        Point[][] edges2D = new Point[numEdges][2];
        // FILL IN CODE
        for(int i = 0; i < numEdges; i++){
            edges2D[i][0] = nodes[adjacencyList[i].getId1()].getLocation();
            edges2D[i][1] = nodes[adjacencyList[i].getId2()].getLocation();
        }
        return edges2D;
    }

    /**
     * Get the nodes of the graph as a 1D array of Points.
     * Used in GUIApp to display the nodes of the graph.
     * @return a list of Points that correspond to nodes of the graph.
     */
    public Point[] getNodes() {
        if (nodes == null) {
            System.out.println("Graph is empty. Load the graph first.");
            return null;
        }
        Point[] nodes = new Point[this.nodes.length];
        // FILL IN CODE
        for(int i = 0; i < this.nodes.length; i++){ //will create a list of the locations of the nodes
            nodes[i] = this.nodes[i].getLocation();
        }
        return nodes;
    }

    /**
     * Used in GUIApp to display the names of the cities.
     * @return the list that contains the names of cities (that correspond
     * to the nodes of the graph)
     */
    public String[] getCities() {
        if (nodes == null) {
            //System.out.println("Graph is empty, load the graph from the file first");
            return null;
        }
        String[] labels = new String[nodes.length];
        // FILL IN CODE
        for(int i = 0; i < this.nodes.length; i++) { //will create a list of the cities of the nodes
            labels[i] = nodes[i].getCity();
        }
        return labels;
    }

    /**
     * Return the CityNode for the given nodeId
     * @param nodeId id of the node
     * @return CityNode
     */
    public CityNode getNode(int nodeId) {
        return nodes[nodeId];
    }

}