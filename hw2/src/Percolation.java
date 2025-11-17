import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
   public class WeightedQuickUnionUF {
        public WeightedQuickUnionUF(int n)      // creates a UF with n elements
        public int count()                      // number of disjoint sets
        public int find(int p)                  // the root of p's set
        public boolean connected(int p, int q)  // whether p and q are in the same set
        public void union(int p, int q)         // join p and q's sets together
    }
*/

public class Percolation {
    // Add any necessary instance variables.
    int numOfOpenSites;
    int a;
    int[] stateList;
    WeightedQuickUnionUF uf1; // contains the bottom space
    WeightedQuickUnionUF uf2; // do not contain the bottom
    public Percolation(int N) {
        // Fill in this constructor.
        if(N < 0){
            throw new IllegalArgumentException();
        }
        a = N;
        numOfOpenSites = 0;
        stateList = new int[a*a];
        uf1 = new WeightedQuickUnionUF(a*a + 2);
        uf2 = new WeightedQuickUnionUF(a*a + 1);  
    }

    public void open(int row, int col) {
        // Fill in this method.
        valid(row, col);
        int pos = row * a + col + 1;
        
        if(stateList[pos - 1] == 1){
            return;
        }
        stateList[pos - 1] = 1;
        // up right left down
        if(col > 0 && isOpen(row, col - 1)){ uf1.union(pos, pos - 1); uf2.union(pos, pos - 1);}
        if(col < a - 1 && isOpen(row, col + 1)){ uf1.union(pos, pos + 1); uf2.union(pos, pos + 1);}
        if(row > 0 && isOpen(row - 1, col)){ uf1.union(pos, pos - a); uf2.union(pos, pos - a);}
        else if(row == 0){ uf1.union(0, pos); uf2.union(0, pos); }
        if(row < a - 1 && isOpen(row + 1, col)){ uf1.union(pos, pos + a); uf2.union(pos, pos + a); }
        else if(row == a - 1){ uf1.union(pos, a*a + 1); }

        numOfOpenSites ++;
    }

    public boolean isOpen(int row, int col) {
        // Fill in this method.
        valid(row, col);
        int pos = row * a + col + 1; 
        return (stateList[pos - 1] == 1);
    }

    public boolean isFull(int row, int col) {
        // Fill in this method.
        valid(row, col);
        int pos = row * a + col + 1; 
        return uf2.connected(pos, 0);
    }

    public int numberOfOpenSites() {
        // Fill in this method.
        return numOfOpenSites;
    }

    public boolean percolates() {
        // Fill in this method.
        return uf1.connected(0, a*a + 1);
    }

    public void valid(int row, int col){
        if(row < 0 || row > a - 1){
            throw new IndexOutOfBoundsException();
        }
        if(col < 0 || col > a - 1){
            throw new IndexOutOfBoundsException();
        }
    }
}
