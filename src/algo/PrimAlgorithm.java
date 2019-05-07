package algo;

import graph.*;
import heap.PriorityQueue;

/** Subclass of MSTAlgorithm. Uses Prim's algorithm to compute MST of the graph. */
public class PrimAlgorithm extends MSTAlgorithm {

    private int sourceVertex;

    /**
     * Constructor for PrimAlgorithm. Takes the graph
     * @param graph input graph
     * @param sourceVertex the first vertex of MST
     */
    public PrimAlgorithm(Graph graph, int sourceVertex) {
        super(graph);
        this.sourceVertex = sourceVertex;
    }

    /**
     * Compute minimum spanning tree for this graph using Prim's algorithm.
     * Add edges of MST to edgesMST list.
     * */
    @Override
    public void computeMST() {
        //FILL IN
        Table table[] = new Table[this.numNodes()];

        for(int i = 0; i < this.numNodes(); i++){
            Table element;
            if(i == sourceVertex) {
                element = new Table(0, -1);
                table[i] = element; // insert to priority queue
            }
            else{
                element = new Table(Integer.MAX_VALUE, -1);
                table[i] = element;
            }
       }

       /*
       TABLE INITIALIZED
        */

       PriorityQueue queue = new PriorityQueue(numNodes());
       /*
       Priority Queue Start
        */

        Boolean found[] = new Boolean[numNodes()];
        /*
        So we don't run into -1 in the heap
         */
        for(int a = 0; a < numNodes(); a++)
            found[a] = false;

        for(int i = 0; i < table.length; i++){
            queue.insert(i,table[i].cost);
        } // filled up the queue with initial values
        //correct

        /*
        Starting Prim's algorithm
         */
        for(int i = 0; i < numNodes(); i++) {
            int min = queue.removeMin();
            found[min] = true;
            Edge pointer = getFirstEdge(min);
            while(pointer != null){
                int newCost = pointer.getCost();
                int Id = pointer.getId2();
                if(!found[Id]) { // if we know the shortest way to get there, we no longer need to worry about it
                    if(newCost < table[Id].cost) { // if the cost is cheaper than what we currently have, update the table and heap
                        queue.reduceKey(Id, newCost);
                        table[Id].cost = newCost;
                        table[Id].path = pointer.getId1();
                        // if not found
                    }
                }
                // need to update table as well
                pointer = pointer.next();
            }
        }


        /*
        Transfer findings of Table to MSTedges
         */
        for(int c = 0; c < numNodes(); c++){
            if (table[c].path != -1) {
                int id1 = table[c].path;
                int id2 = c;
                int cost = table[c].cost;
                Edge mstEdge = new Edge(id1,id2,cost);
                addMSTEdge(mstEdge);
            }
        }
    }

    public class Table {
        public int cost;
        public int path;

        public Table(int cost, int path){
            this.cost = cost;
            this.path = path;
        }
    }
}




