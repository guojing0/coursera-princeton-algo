import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.Map;

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

    public static void merge(Comparable[] arr, int lo, int mid, int hi) {
        int i = lo, j = mid + 1;
        Comparable[] aux = new Comparable[arr.length];

        for (int k = lo; k < hi + 1; k++) {
            aux[k] = arr[k];
        }

        for (int k = lo; k < hi + 1; k++) {
            if (i > mid) {
                arr[k] = aux[j++];
            } else if (j > hi) {
                arr[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                arr[k] = aux[j++];
            } else {
                arr[k] = aux[i++];
            }
        }
    }

    public static void mergeSort(Comparable[] arr, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(arr, lo, mid);
        mergeSort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    public static void mergeSort(Comparable[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void shuffle(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, StdRandom.uniform(i + 1)); // random number between 0 and i
        }
    }

    public static void bottomUpMergeSort(Comparable[] arr) {
        int len = arr.length;

        for (int sz = 1; sz < len; sz *= 2) {
            for (int lo = 0; lo < len - sz; lo += sz + sz) {
                merge(arr, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, len - 1));
            }
        }
    }

    public static void main(String[] args) {
        int N = 50000;
        Comparable[] foo = new Comparable[N];
        for (int i = 0; i < N; i++) {
            foo[i] = i;
        }
//        System.out.println(Arrays.toString(foo));
        StdRandom.shuffle(foo);
        System.out.println(Arrays.toString(foo));
        Stopwatch timer = new Stopwatch();
        shellSort(foo);
        StdOut.println(timer.elapsedTime());
        System.out.println(Arrays.toString(foo));
    }
}
