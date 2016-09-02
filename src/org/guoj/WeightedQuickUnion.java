package org.guoj;

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
            id[i] = id[id[i]];
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
        WeightedQuickUnion wqu = new WeightedQuickUnion(6);
        wqu.union(0, 2);
        wqu.union(1, 2);
        wqu.union(4, 3);
        wqu.union(3, 2);
        System.out.println(wqu.connected(1, 4));
    }
}