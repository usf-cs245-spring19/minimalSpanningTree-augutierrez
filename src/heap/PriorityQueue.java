package heap;

/** A priority queue: represented by the min heap.
 *  Used in Prim's algorithm. */
public class PriorityQueue {
	// FILL IN CODE as needed
    private int size;
    private PriorityQueueObject[] heap;
    int position[];


    public PriorityQueue(int size){
        this.size = 1;
        position = new int[size+1000]; // just to make sure we don't run out of memory
        heap = new PriorityQueueObject[size+1];
        heap[0] = new PriorityQueueObject(-1, Integer.MIN_VALUE);
    }

    void insert(int nodeId, int priority){ // if we insert more elements, position wont be large enough

        heap[size] = new PriorityQueueObject(nodeId, priority);
        int x = bubbleup(size);
        position[nodeId] = x;
        size++;
    }

    int bubbleup(int x){ // to be used after insertion
        int priority = heap[x].cost;
        int Id = heap[x].nodeId;
        int index = x;
        while(heap[index/2].cost < priority) {
            heap[index].cost = heap[index / 2].cost;
            heap[index].nodeId = heap[index / 2].nodeId;// node id and cost
            heap[index / 2].cost = priority;
            heap[index / 2].nodeId = Id;
            index = index/2;
        }
        return index;
    }
    int bubbledown(int x){ // to be used after deletion
        int priority = heap[x].cost;
        int Id = heap[x].nodeId;
        int index = x;

        while(index * 2 < size){
            //&& (heap[index*2].cost < priority || heap[index*2+1].cost < priorty
            int cost1;
            int cost2 = Integer.MAX_VALUE;
            cost1 = heap[index*2].cost;

            if(index*2 + 1 < size){ // if there is a second child
                cost2 = heap[index*2+1].cost;
                if(cost2 < cost1 && cost2 < priority){ // swap with second child
                    heap[index].cost = heap[index * 2 + 1].cost;
                    heap[index].nodeId = heap[index * 2 + 1].nodeId;// node id and cost
                    heap[index * 2 + 1].cost = priority;
                    heap[index * 2 + 1].nodeId = Id;
                    index = index * 2 + 1;
                }
            }
            else if(cost1 < cost2 && cost1 < priority){ //swap with first child
                heap[index].cost = heap[index * 2].cost;
                heap[index].nodeId = heap[index * 2].nodeId;// node id and cost
                heap[index * 2].cost = priority;
                heap[index * 2].nodeId = Id;
                index = index * 2;
            }
            else{
                break;
            }
        }
        return index;
    }

    //- Inserts node id with the given priority into the priority queue. Priority is the cost of the edge.
    int removeMin(){
        int Id = heap[1].nodeId;
//        int priority = heap[1].cost;
        if(size == 1){ //heap is empty
            return -1;
        }
        heap[1].cost = heap[size - 1].cost;
        heap[1].nodeId = heap[size - 1].nodeId;
        size --;
        bubbledown(1);
        position[Id] = -1;
        return Id;
    }
    // - Removes the vertex with the smallest priority from the queue, and returns it.

    void reduceKey(int nodeId, int newPriority){
        int a = 0;
        if(newPriority < heap[position[nodeId]].cost)
            a = -1;
        if(newPriority > heap[position[nodeId]].cost)
            a = 1;
        // if priority is changed to the same thing, nothing will happen
        heap[position[nodeId]].cost = newPriority;
        int x = -1;
        if(a == 1){
            x = bubbledown(position[nodeId]);
            position[nodeId] = x;
        }
        if(a == -1) {
            x = bubbleup(position[nodeId]);
            position[nodeId] = x;
        }
    } //Reduces the priority of the given vertex in the priority queue to newPriority, rearranging the queue (minHeap) as necessary.
    //To implement this method efficiently, you will need to keep track of where each element is in the priority queue (heap). The simplest way to do it is to store an array of positions in the priority queue for each node id(see below).



    public class PriorityQueueObject{
        public int nodeId;
        public int cost;

        public PriorityQueueObject(int nodeId, int cost){
            this.nodeId = nodeId;
            this.cost = cost;
        }
    }
}

