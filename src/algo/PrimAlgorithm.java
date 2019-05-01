package algo;

import graph.*;

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
        // FILL IN CODE
        // Note: must use a PriorityQueue and be efficient
        Table table[] = new Table[this.numNodes()];
      //  Initialize the table as shown above Repeat numVertices times:/
        for(int i = 0; i < this.numNodes(); ++){
            if(i )
            table[i]
        }
        v = findMinimumUknownVertex() mark v as known
        for each neighbor u of v:
        if (u is unknown)
        if table[u].cost > cost of edge from v to u {
            table[u].cost = cost of edge from v to u table[u].path = v
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




