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
        position = new int[size]; // just to make sure we don't run out of memory/
        heap = new PriorityQueueObject[size+1];
        heap[0] = new PriorityQueueObject(-1, Integer.MIN_VALUE);
    }

    public void insert(int nodeId, int priority){ // if we insert more elements, position wont be large enough

        heap[size] = new PriorityQueueObject(nodeId, priority);
        position[nodeId] = size;
        bubbleup(size);
        //position[nodeId] = x;
        size++;
    }
    // bubble up and bubble down should change positions array too
    int bubbleup(int x){ // to be used after insertion
     //   System.out.println(x);
        int priority = heap[x].cost;
        int Id = heap[x].nodeId;
  //      System.out.println("THIS IS THE ID IN BUBBLE : " + Id);
        int index = x;
        while(heap[index/2].cost > priority) {
//            int tempCost = heap[index].cost;
//            int tempNode = heap[index].nodeId;
            // fix position of the one you swap too
            heap[index].cost = heap[index / 2].cost;
            heap[index].nodeId = heap[index / 2].nodeId;// node id and cost
            heap[index / 2].cost = priority;
            heap[index / 2].nodeId = Id;
   //         print();
            int pos1 = position[heap[index / 2].nodeId];
 //           System.out.println(heap[1].nodeId);
            int pos2 = position[heap[index].nodeId];
            position[heap[index / 2].nodeId] = pos2;
            position[heap[index].nodeId] = pos1;
            index = index/2;
        }
        return index;
    }

    int bubbledown(int x){ // to be used after deletion
  //      System.out.println(x);
        int priority = heap[x].cost;
        int Id = heap[x].nodeId;
        int index = x;
        //position[x] = 1;
        while(index * 2 < size){
            //&& (heap[index*2].cost < priority || heap[index*2+1].cost < priorty
            int cost1;
            int cost2 = Integer.MAX_VALUE;
            cost1 = heap[index*2].cost;
            Boolean needBreak = true;
            if(index*2 + 1 < size){ // if there is a second child
                cost2 = heap[index*2+1].cost;
                if(cost2 < cost1 && cost2 < priority){ // swap with second child
//                    int tempCost = heap[index].cost;
//                    int tempNode = heap[index].nodeId;
                    heap[index].cost = heap[index * 2 + 1].cost;
                    heap[index].nodeId = heap[index * 2 + 1].nodeId;// node id and cost
                    heap[index * 2 + 1].cost = priority;
                    heap[index * 2 + 1].nodeId = Id;

                    int pos1 = position[heap[index * 2].nodeId];
                    int pos2 = position[heap[index].nodeId];
                    position[heap[index * 2].nodeId] = pos2;
                    position[heap[index].nodeId] = pos1;

                    index = index * 2 + 1;
                    needBreak = false;
                }
            }
            if((cost1 == cost2 || cost1 < cost2) && cost1 < priority){ //swap with first child
                heap[index].cost = heap[index * 2].cost;
                heap[index].nodeId = heap[index * 2].nodeId;// node id and cost
                heap[index * 2].cost = priority;
                heap[index * 2].nodeId = Id;

                int pos1 = position[heap[index * 2].nodeId];
                int pos2 = position[heap[index].nodeId];
                position[heap[index * 2].nodeId] = pos2;
                position[heap[index].nodeId] = pos1;

                index = index * 2;
                needBreak = false;
            }
            if(needBreak){
                break;
            }
        }
        return index;
    }

    //- Inserts node id with the given priority into the priority queue. Priority is the cost of the edge.
    public int removeMin(){
        if(size == 1){ //heap is empty
            return -1;
        }
        int Id = heap[1].nodeId;
//        int priority = heap[1].cost;

        heap[1].cost = heap[size - 1].cost;
        heap[1].nodeId = heap[size - 1].nodeId;
        int newPos = bubbledown(1);
        position[heap[size - 1].nodeId] = 1;
        //position[size-2] = newPos; // -2 because position doesn't count the min int at 0  MAYBE
        position[Id] = -1; // position in the heap, -1 because its gone
  //      System.out.println(size);
        size --;
        return Id;
    }
    // - Removes the vertex with the smallest priority from the queue, and returns it.

    public void reduceKey(int nodeId, int newPriority){
//        if(newPriority < heap[position[nodeId]].cost)
//            a = -1;
//        if(newPriority > heap[position[nodeId]].cost)
//            a = 1;
        // if priority is changed to the same thing, nothing will happen
        //if(newPriority < heap[position[nodeId]].cost) {
        heap[position[nodeId]].cost = newPriority;
  //      System.out.println("ID OUTSIDE BUBBLE : " + nodeId);
//        System.out.println("REDUCE KEY ID : " + nodeId);
//        System.out.println("POSITION : " + position[nodeId]);
        bubbleup(position[nodeId]);
 //       System.out.println("NEW POSITION IS : " + position[nodeId]);

            //position[nodeId] = x;
        //}
    }

    public void print(){
        for(int i = 0; i < heap.length; i ++){
            System.out.println("ID: " + heap[i].nodeId + " COST: " + heap[i].cost);
        }
    }
    //Reduces the priority of the given vertex in the priority queue to newPriority, rearranging the queue (minHeap) as necessary.
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

