package week2;

import edu.princeton.cs.algs4.StdOut;

public class MoveToFront<T> {

    private Node first;

    private class Node {
        T value;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(T val) {
        Node oldFirst = first;
        first = new Node();
        first.value = val;
        first.next = oldFirst;
    }

    public void moveToFront(T v) {
        Node prevX = first;
        Node n = nodeOf(v);
        while (prevX.next != n) {
            prevX = prevX.next;
        }
        Node oldPostX = n.next;
        n.next = first;
        first = n;
        prevX.next = oldPostX;
    }

    public Node nodeOf(T val) {
        Node n = first;
        while (n != null) {
            if (n.value == val) {
                return n;
            }
            n = n.next;
        }
        return null;
    }

    public boolean includes(T val) {
        Node n = first;
        while (n != null) {
            if (n.value == val) {
                return true;
            }
            n = n.next;
        }
        return false;
    }

    public String toString() {
        String s = "";
        Node n = first;
        while (n != null) {
            s += n.value + " ";
            n = n.next;
        }
        return s;
    }

    public static void main(String[] args) {
        MoveToFront<Integer> foo = new MoveToFront<>();
        for (int i = 0; i < args.length; i++) {
            int val = Integer.parseInt(args[i]);
            if (foo.includes(val)) {
                foo.moveToFront(val);
            } else {
                foo.insert(val);
            }
        }
        StdOut.println(foo);
    }
}
