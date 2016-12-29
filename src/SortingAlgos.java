import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;

public class SortingAlgos {

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void swap(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void selectionSort(Comparable[] arr) {
        int len = arr.length;

        for (int i = 0; i < len; i++) {
            int min = i;
            for (int j = i + 1; j < len; j++) {
                if (less(arr[j], arr[min])) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    public static void insertionSort(Comparable[] arr) {
        int len = arr.length;

        for (int i = 1; i < len; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j], arr[j - 1])) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    public static void insertionSortWithSentinel(Comparable[] arr) {
        int len = arr.length;

        int minIndex = 0;
        for (int i = 1; i < len; i++) {
            if (less(arr[i], arr[minIndex])) minIndex = i;
        }
        swap(arr, 0, minIndex);

        for (int i = 1; i < len; i++) {
            int j = i;
            while (less(arr[j], arr[j - 1])) {
                swap(arr, j - 1, j);
                j--;
            }
        }
    }

    public static void insertionSortWithoutSwap(Comparable[] arr) {
        int len = arr.length;

        int minIndex = 0;
        for (int i = 1; i < len; i++) {
            if (less(arr[i], arr[minIndex])) minIndex = i;
        }
        swap(arr, 0, minIndex);

        for (int i = 1; i < len; i++) {
            Comparable v = arr[i];
            int j = i;
            while (less(v, arr[j - 1])) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = v;
        }
    }

    public static void shellSort(Comparable[] arr) {
        int len = arr.length;
        int h = 1;

        while (h < len / 3) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            for (int i = h; i < len; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(arr[j], arr[j - h])) {
                        swap(arr, j - h, j);
                    }
                }
            }
            h = h / 3;
        }
    }

    public static void shuffle(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, StdRandom.uniform(i + 1)); // random number between 0 and i
        }
    }

    public static void main(String[] args) {
        int N = 10000;
        Comparable[] foo = new Comparable[N];
        for (int i = 0; i < N; i++) {
            foo[i] = i;
        }
//        System.out.println(Arrays.toString(foo));
        StdRandom.shuffle(foo);
        System.out.println(Arrays.toString(foo));
        Stopwatch timer = new Stopwatch();
        insertionSortWithSentinel(foo);
        StdOut.println(timer.elapsedTime());
        System.out.println(Arrays.toString(foo));
    }
}
