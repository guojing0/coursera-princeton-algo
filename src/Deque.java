import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> { // TODO debug removeFirst()

    private Node pre, post;
    private Node first; // TODO delete anyway just for test here
    private int n = 0;

    private class Node {
        private Item val;
        private Node prev;
        private Node next;
    }

    public Deque() {
        pre = new Node();
        post = new Node();
        pre.next = post;
        post.prev = pre;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newNode = new Node();
        newNode.val = item;

        newNode.prev = pre;
        newNode.next = newNode.prev.next;

        pre.next.prev = newNode;
        pre.next = newNode;

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

        Item temp = pre.next.val;

        pre.next = pre.next.next;
        pre.next.prev = pre;
        pre.next = null;

        --n;
        return temp;
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
        foo.addFirst(2);
        foo.addFirst(4);
        System.out.println(foo.removeFirst());
        System.out.println(foo.removeFirst());
    }

}