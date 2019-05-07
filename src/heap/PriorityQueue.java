package heap;

/** A priority queue: represented by the min heap.
 *  Used in Prim's algorithm. */
public class PriorityQueue {
	// FILL IN CODE as needed
    private int size;
    private PriorityQueueObject[] heap;
    int position[];


    public PriorityQueue(int size){
        this.size = 0;
        position = new int[size];
        heap = new PriorityQueueObject[size+1];
        heap[0] = new PriorityQueueObject(-1, Integer.MIN_VALUE);
    }


    public void insert(int nodeId, int priority){ // if we insert more elements, position wont be large enough
        size++;
        heap[size] = new PriorityQueueObject(nodeId, priority);
        position[nodeId] = size;
        bubbleup(size);

    }


    int bubbleup(int x){ // to be used after insertion
        int index = x;
        Boolean needBreak = false;

        while(!needBreak) { // check parent, swap with parent if parent is larger, fix positions
            int IdChild = heap[index].nodeId;
            int priorityChild = heap[index].cost;
            int IdParent = heap[index/2].nodeId; // currently higher priority
            int priorityParent = heap[index/2].cost;

            if(priorityChild < priorityParent) {
                int pos2 = position[IdChild];
                int pos1 = position[IdParent];

                heap[index].cost = priorityParent; // switched cost
                heap[index].nodeId = IdParent;// switched node Id's

                position[IdParent] = pos2;


                heap[index / 2].cost = priorityChild;
                heap[index / 2].nodeId = IdChild;

                position[IdChild] = pos1; // switched position

                index = index / 2;
            }
            else{
                needBreak = true;
            }
        }
        return index;
    }

    int bubbledown(int x){ // to be used after deletion

        int index = x;
       Boolean needBreak = false;
        while(index * 2 < size && !needBreak) {
            int priorityParent = heap[index].cost; // parent (current)
            int IdParent = heap[index].nodeId;

            int priorityChild1 = heap[index * 2].cost; // first child
            int IdChild1 = heap[index * 2].nodeId;

            if (index * 2 + 1 < size) { // if there is a second child
                int priorityChild2 = heap[index * 2 + 1].cost;
                int IdChild2 = heap[index * 2 + 1].nodeId;

                if (priorityChild2 <= priorityChild1 && priorityChild2 < priorityParent) {
                    //do swapping
                    int pos2 = position[IdParent];
                    int pos1 = position[IdChild2];

                    heap[index].cost = priorityChild2; // switched cost
                    heap[index].nodeId = IdChild2;// switched node Id's

                    position[IdChild2] = pos2; // switched position

                    heap[index * 2 + 1].cost = priorityParent; // switched cost
                    heap[index * 2 + 1].nodeId = IdParent;// switched node Id's

                    position[IdParent] = pos1; // switched position

                    index = index * 2 + 1;
                } else if (priorityChild1 < priorityChild2 && priorityChild1 < priorityParent) {
                    //do swapping
                    int pos2 = position[IdParent];
                    int pos1 = position[IdChild1];

                    heap[index].cost = priorityChild1; // switched cost
                    heap[index].nodeId = IdChild1;// switched node Id's

                    position[IdChild1] = pos2; // switched position

                    heap[index * 2].cost = priorityParent; // switched cost
                    heap[index * 2].nodeId = IdParent;// switched node Id's

                    position[IdParent] = pos1; // switched position

                    index = index * 2;
                } else {
                    needBreak = true;
                }
            } else {
                if (priorityChild1 < priorityParent) {
                    //do swapping
                    int pos2 = position[IdParent];
                    int pos1 = position[IdChild1];

                    heap[index].cost = priorityChild1; // switched cost
                    heap[index].nodeId = IdChild1;// switched node Id's

                    position[IdChild1] = pos2; // switched position

                    heap[index * 2].cost = priorityParent; // switched cost
                    heap[index * 2].nodeId = IdParent;// switched node Id's

                    position[IdParent] = pos1; // switched position

                    index = index * 2;
                } else {
                    needBreak = true;
                }
            }


        }
        return index;
    }

    //- Inserts node id with the given priority into the priority queue. Priority is the cost of the edge.
    public int removeMin(){
        if(size == 0){ //heap is empty
            return -1;
        }
        int Id = heap[1].nodeId;
        position[Id] = -1;
//        int priority = heap[1].cost;
        position[heap[size].nodeId] = 1;
        heap[1].cost = heap[size].cost;
        heap[1].nodeId = heap[size].nodeId;

        size --;
        bubbledown(1);

        return Id;
    }

    // - Removes the vertex with the smallest priority from the queue, and returns it.

    public void reduceKey(int nodeId, int newPriority){

        heap[position[nodeId]].cost = newPriority;
        bubbleup(position[nodeId]);
    }

    // helper print function for debugging purposes
    public void print(){
        for(int i = 0; i < heap.length; i ++){
            System.out.println("ID: " + heap[i].nodeId + " COST: " + heap[i].cost);
        }
    }
    
    public class PriorityQueueObject{
        public int nodeId;
        public int cost;

        public PriorityQueueObject(int nodeId, int cost){
            this.nodeId = nodeId;
            this.cost = cost;
        }
    }
}

