package algo;

import graph.*;
import sets.DisjointSets;

/** Subclass of MSTAlgorithm. Computes MST of the graph using Kruskal's algorithm. */
public class KruskalAlgorithm extends MSTAlgorithm {

    /**
     * Constructor for KruskalAlgorithm. Takes the graph
     * @param graph input graph
     */
    public KruskalAlgorithm(Graph graph) {
        super(graph);
    }

    /**
     * Compute minimum spanning tree for this graph. Add edges of MST to
     * edgesMST list. Should use Kruska's algorithm and DisjointSet class.
     */
    @Override
    public void computeMST() {
        // FILL IN CODE
        DisjointSets sets = new DisjointSets();
        sets.createSets(numNodes());

        KruskalElement edges[] = new KruskalElement[88];
        int counter = 0;
        for(int i = 0; i < numNodes(); i++){
            Edge temp = getFirstEdge(i);
            while(temp != null){
                edges[counter] = new KruskalElement(temp.getId1(), temp.getId2(), temp.getCost());
                counter++;
                temp = temp.next();
            }
        }

        /*
        Sort the edges
         */

        KruskalElement sortedSet[] = new KruskalElement[edges.length];


        for(int i = 0; i < edges.length; i++){
            int min = Integer.MAX_VALUE;
            int minIndex = -1;
            for(int a = 0; a < edges.length; a++){
                if(edges[a]!= null && edges[a].cost <= min) {
                    min = edges[a].cost;
                    minIndex = a;
                }
            }
            sortedSet[i] = new KruskalElement(edges[minIndex].node1, edges[minIndex].node2, edges[minIndex].cost);
            edges[minIndex] = null;
        }

        /*
        Now give disjoint sets the sorted edges;  Add the edges to MST as well
         */

        for(int i = 0; i < sortedSet.length; i++){
            if(sets.find(sortedSet[i].node1) != sets.find(sortedSet[i].node2)) {
                sets.union(sortedSet[i].node1, sortedSet[i].node2);
                Edge edge = new Edge(sortedSet[i].node1, sortedSet[i].node2, sortedSet[i].cost);
                addMSTEdge(edge);
            }
        }
    }
        /*
        End of Kruskal!
         */


    public class KruskalElement{
        public int node1;
        public int node2;
        public int cost;
        public KruskalElement(int node1, int node2, int cost){
            this.node1 = node1;
            this.node2 = node2;
            this.cost = cost;
        }
    }
}
