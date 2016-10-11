import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private int n;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (n == arr.length) {
            resize(arr.length * 2);
        }
        arr[n++] = item;
    }

    public Item dequeue() { // TODO see if I can do it better
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int nth = StdRandom.uniform(n);
        Item temp = arr[nth];

        if (nth == 0) {
            for (int i = 1; i < n; i++) {
                arr[i - 1] = arr[i];
            }
            arr[n - 1] = null;
        } else if (nth == n - 1) {
            arr[nth] = null;
        } else {
            for (int i = nth; i < n - 1; i++) {
                arr[i] = arr[i + 1];
            }
            arr[n - 1] = null;
        }
        --n;

        if (n > 0 && n < arr.length / 4) {
            resize(arr.length / 2);
        }

        return temp;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return arr[StdRandom.uniform(n)];
    }

    private void resize(int cap) {
        Item[] temp = (Item[]) new Object[cap];
        for (int i = 0; i < n; i++) {
            temp[i] = arr[i];
        }
        arr = temp;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Item[] iter = arr.clone();
        private int i = n;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            int nth = StdRandom.uniform(i);
            Item temp = iter[nth];

            if (nth == 0) {
                for (int j = 1; j < i; j++) {
                    iter[j - 1] = iter[j];
                }
            } else if (nth == i - 1) {
                iter[nth] = null;
            } else {
                for (int j = nth; j < i - 1; j++) {
                    iter[j] = iter[j + 1];
                }
            }
            --i;

            return temp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> foo = new RandomizedQueue<>();
        foo.enqueue(1);
        foo.enqueue(2);
        foo.enqueue(3);
        Iterator<Integer> bar = foo.iterator();

        while (bar.hasNext()) {
            System.out.println(bar.next());
        }

        System.out.println(bar.hasNext());
    }

}
