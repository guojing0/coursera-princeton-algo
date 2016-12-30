import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare extends SortingAlgos {

    public static double time(String alg, Comparable[] a) {
        Stopwatch timer = new Stopwatch();
        if (alg.equals("Insertion")) SortingAlgos.insertionSortWithoutSwap(a);
        if (alg.equals("Selection")) SortingAlgos.selectionSort(a);
        if (alg.equals("Shell"))     SortingAlgos.shellSort(a);
        if (alg.equals("Merge"))     SortingAlgos.mergeSort(a);
        return timer.elapsedTime();
    }

    public static double timeRandomInput(Double[] a, String alg, int T) {
        double total = 0;
        for (int i = 0; i < T; i++) {
            total += time(alg, a);
        }
        return total;
    }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        int N = Integer.parseInt(args[2]);
        int T = Integer.parseInt(args[3]);
        Double[] a = new Double[N];
        Double[] b = new Double[N];
        for (int i = 0; i < N; i++) {
            double u = StdRandom.uniform();
            a[i] = u;
            b[i] = u;
        }
        double t1 = timeRandomInput(a, alg1, T);
        double t2 = timeRandomInput(b, alg2, T);

        StdOut.printf("For %d random doubles, %s is %.3f times faster than %s\n", N, alg1, t2/t1, alg2);
    }
}
