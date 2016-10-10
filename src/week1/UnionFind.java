package week1;

public class UnionFind {

    private int id[];

    public UnionFind(int N) {
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public static void main(String[] args) {
        UnionFind UF = new UnionFind(10);
        UF.union(2, 7);
        UF.union(7, 8);
        UF.union(9, 4);
        UF.union(3, 4);
        System.out.println(UF.connected(3, 9));
    }
}
