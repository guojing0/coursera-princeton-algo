import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation { // open is true, close false

    private WeightedQuickUnionUF union;
    private boolean[][] grid;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n cannot be smaller than zero");
        }

        union = new WeightedQuickUnionUF(n * n + 2); // first and last always open
        grid = new boolean[n][n];
    }

    public void open(int i, int j) {
        if (i <= 0 || i > grid.length) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        if (j <= 0 || j > grid.length) {
            throw new IndexOutOfBoundsException("column index j out of bounds");
        }

        grid[i - 1][j - 1] = true;

        int num = grid.length * i - grid.length + j;

        if (j > 1 && grid[i - 1][j - 2]) { // left
            union.union(union.find(num), union.find(num - 1));
        }
        if (j < grid.length && grid[i - 1][j]) { // right
            union.union(union.find(num), union.find(num + 1));
        }
        if (i > 1 && grid[i - 2][j - 1]) { // up
            union.union(union.find(num), union.find(num - grid.length));
        }
        if (i < grid.length && grid[i][j - 1]) { // down
            union.union(union.find(num), union.find(num + grid.length));
        }

        if (i == 1) {
            union.union(0, num);
        }
        if (i == grid.length) {
            union.union(grid.length * grid.length + 1, num); // len * len + 1 : last number
        }
    }

    public boolean isOpen(int i, int j) {
        if (i <= 0 || i > grid.length) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        if (j <= 0 || j > grid.length) {
            throw new IndexOutOfBoundsException("column index j out of bounds");
        }

        return grid[i - 1][j - 1];
    }

    public boolean isFull(int i, int j) {
        if (i <= 0 || i > grid.length) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        if (j <= 0 || j > grid.length) {
            throw new IndexOutOfBoundsException("column index j out of bounds");
        }

        int num = grid.length * i - grid.length + j;
        return union.connected(0, union.find(num));
    }

    public boolean percolates() {
        return union.connected(0, grid.length * grid.length + 1);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(1, 1);
        p.open(2, 1);
        p.open(3, 1);
        p.open(4, 1);
        p.open(1, 4);
        p.open(2, 4);
        p.open(4, 4);
        System.out.println(p.isFull(4, 4));
        System.out.println(p.percolates());
    }
}