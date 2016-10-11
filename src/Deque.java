import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first;
    private int n;

    private class Node {
        private Item val;
        private Node next;
    }

    public Deque() {
        first = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node oldFirst = first;
        first = new Node();
        first.val = item;
        first.next = oldFirst;
        ++n;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (isEmpty()) {
            addFirst(item);
        } else {
            Node last = new Node();
            last.val = item;

            Node temp = first;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = last;
            ++n;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Item item = first.val;
        first = first.next;
        --n;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (n == 1) { // when first is the last item
            return removeFirst();
        } else {
            Node temp = first;
            while (temp.next.next != null) {
                temp = temp.next;
            }

            Item item = temp.next.val;
            temp.next = null;
            --n;
            return item;
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.val;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> foo = new Deque<>();
        foo.addLast(0);
        foo.addLast(1);
        System.out.println(foo.isEmpty());
        System.out.println(foo.removeLast());
        System.out.println(foo.removeLast());
        foo.addLast(5);
        System.out.println(foo.removeLast());
        System.out.println(foo.isEmpty());
    }

}