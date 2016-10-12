import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node pre, post;
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
        newNode.next = pre.next;

        pre.next.prev = newNode;
        pre.next = newNode;

        ++n;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node newNode = new Node();
        newNode.val = item;

        newNode.next = post;
        newNode.prev = post.prev;

        post.prev.next = newNode;
        post.prev = newNode;

        ++n;
    }

    public Item removeFirst() { // TODO if I can do better
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node temp = pre.next;
        Item tempVal = temp.val;

        pre.next = pre.next.next;
        pre.next.prev = pre;
        temp = null;

        --n;
        return tempVal;
    }

    public Item removeLast() { // TODO if I can do better
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        Node temp = post.prev;
        Item tempVal = temp.val;

        post.prev = post.prev.prev;
        post.prev.next = post;
        temp = null;

        --n;
        return tempVal;
    }

    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = pre.next;
        private int i = n;

        public boolean hasNext() {
            return i > 0;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.val;
            current = current.next;
            --i;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        Deque<Integer> foo = new Deque<>();

        foo.addLast(10);
        foo.addFirst(20);

        System.out.println(foo.removeFirst());
        System.out.println(foo.removeFirst());
    }

}