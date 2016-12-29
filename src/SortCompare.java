import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare extends SortingAlgos {

    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) SortingAlgos.insertionSortWithSentinel(a);
        if (alg.equals("Selection")) SortingAlgos.selectionSort(a);
        if (alg.equals("Shell"))     SortingAlgos.shellSort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(String alg, int N, int T) {
        double total = 0;
        Double[] a = new Double[N];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < N; j++) {
                a[j] = StdRandom.uniform();
            }
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        double t1 = timeRandomInput(alg1, N, T);
        double t2 = timeRandomInput(alg2, N, T);
        StdOut.printf("For %d random doubles, %s is %.2f times faster than %s\n", N, alg1, t2/t1, alg2);
    }
}
