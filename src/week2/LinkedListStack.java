package week2;

import java.util.Iterator;

public class LinkedListStack<Item> implements Iterable<Item> {

    private Node first = null;

    private class Node {
        Item item;
        Node next;
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
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /*
     * Traditional implementation of stack based on linked lists
     */
    public boolean isEmpty() {
        return first == null;
    }

    public void push(Item str) {
        Node oldFirst = first;
        first = new Node();
        first.item = str;
        first.next = oldFirst;
    }

    public Item pop() {
        Item temp = first.item;
        first = first.next;
        return temp;
    }

    public static void main(String[] args) {
        LinkedListStack<Integer> foo = new LinkedListStack<>();
        foo.push(1);
        foo.push(2);
        Iterator bar = foo.iterator();

        while (bar.hasNext()) {
            Object i = bar.next();
            System.out.println(i);
        }
    }
}