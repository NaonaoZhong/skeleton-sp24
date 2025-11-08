public class UnionFind {
    // TODO: Instance variables
    private int[] array;
    private int size;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        size = N;
        array = new int[size];
        for (int i=0; i<N; i++){
            array[i] = -1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        int p = array[v];
        while(p >= 0){
            p = array[p]; // update the upper Node
        }
        return -p;
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return array[v];
    }

    public int root(int v){
        int p = v;
        while(array[p] >= 0){
            p = array[p];
        }
        return p;
    }
    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        if(root(v1) == root(v2)){
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if ( v < 0 || v >= size){
            throw new IllegalArgumentException(
                String.format("v should be a Integer between 0 and %d ", size)
            );
        }
        int p = v;
        int t;
        int root = root(v); 
        while(array[p] >= 0){
            t = p;
            p = array[p];
            array[t] = root;
        }
        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (root(v1) == root(v2)){
            return;
        }
        int sizeOf1 = sizeOf(v1);
        int sizeOf2 = sizeOf(v2);
        if (sizeOf1 > sizeOf2){
            array[root(v1)] = -(sizeOf1 + sizeOf2);
            array[root(v2)] = root(v1);
        }
        else{
            array[root(v2)] = -(sizeOf1 + sizeOf2);
            array[root(v1)] = root(v2);
        }

    }

}
