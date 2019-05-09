package sets;

/** A class that represents the Disjoint Sets data structure. Please refer
 * to the lecture slides.
 * This class is used in Kruskal's.
 * */
public class DisjointSets {
    private int[] parent;

    public void createSets(int n) {
        // FILL IN CODE
        parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = -1;
        }
        // every node starts off as its own set
    }

    /**
     * Returns the root of the "tree" that x belongs to. Uses path compression
     * heuristic.
     * @param x node id
     * @return root of the tree that x belongs to
     */
    public int find(int x) {
        // FILL IN CODE
        while (parent[x] >= 0)
            x = parent[x];

        return x;
    }

    /**
     * Merges the trees of x and y.
     * @param x node id
     * @param y node id
     */
    public void union(int x, int y) {
        // FILL IN CODE
        int rootx = find(x);
        int rooty = find(y);

        if(rootx == rooty)
            return;

        if(parent[rootx] < parent[rooty]){
            parent[rooty] = rootx; // assigning biggest tree to smallest one
        }
        else{
            if (parent[rootx] == parent[rooty])
                parent[rooty]--;

            parent[rootx] = rooty;
        }
    }

}

