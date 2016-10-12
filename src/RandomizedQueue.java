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

    public Item dequeue() { // Order does not matter coz nth is random
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        int nth = StdRandom.uniform(n);
        Item temp = arr[nth];
        arr[nth] = arr[--n]; // subtract n first then index

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
            iter[nth] = iter[--i];

            return temp;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> foo = new RandomizedQueue<>();

        for (int i = 0; i < 10; i++) {
            foo.enqueue(i);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(foo.dequeue());
        }
    }

}
