public class WeightedQuickUnion {

    private int[] id, sz;

    public WeightedQuickUnion(int N) {
        id = new int[N];
        sz = new int[N];

        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int root(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]]; // magic trick
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void union(int p, int q) {
        int prt = root(p);
        int qrt = root(q);
        if (prt == qrt) {
            return;
        }
        if (sz[prt] > sz[qrt]) {
            id[qrt] = prt;
            sz[prt] += sz[qrt];
        } else {
            id[prt] = qrt;
            sz[qrt] += sz[prt];
        }
    }

    public static void main(String[] args) {
        WeightedQuickUnion wqu = new WeightedQuickUnion(8);
        wqu.union(2, 7);
        wqu.union(6, 7);
        wqu.union(1, 2);
        wqu.union(0, 1);
        wqu.union(3, 2);
        wqu.union(4, 3);
        wqu.union(2, 7);

        for (int i : wqu.id) {
            System.out.println(i);
        }
    }
}