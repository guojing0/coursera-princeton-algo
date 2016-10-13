import edu.princeton.cs.algs4.StdRandom;

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
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (less(arr[j], arr[min])) {
                    min = j;
                }
            }
            swap(arr, i, min);
        }
    }

    public static void insertionSort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr[j], arr[j - 1])) {
                    swap(arr, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    public static void shellSort(Comparable[] arr) {
        int h = 1;

        while (h < arr.length / 3) {
            h = 3 * h + 1;
        }

        while (h >= 1) {
            for (int i = h; i < arr.length; i++) {
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h) {
                    swap(arr, j, j - h);
                }
            }
            h /= 3;
        }
    }

    public static void shuffle(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            swap(arr, i, StdRandom.uniform(i + 1)); // random number between 0 and i
        }
    }

    public static void main(String[] args) {
        Comparable[] foo = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(Arrays.toString(foo));
        shuffle(foo);
        System.out.println(Arrays.toString(foo));
        shellSort(foo);
        System.out.println(Arrays.toString(foo));
    }
}
