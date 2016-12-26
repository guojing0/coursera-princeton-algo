package week2;

public class CircularLLQueue {

    private Node last;

    private class Node {
        int item;
        Node next;
    }

    public boolean isEmpty() {
        return last == null;
    }

    public void enqueue(int num) {
        if (isEmpty()) {
            last = new Node();
            last.item = num;
            last.next = last;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = num;
            last.next = oldLast.next;
            oldLast.next = last;
        }
    }

    public int dequeue() {
        int val = last.next.item;
        last.next = last.next.next;
        return val;
    }

    public static void main(String[] args) {
        CircularLLQueue foo = new CircularLLQueue();
        foo.enqueue(1);
        foo.enqueue(2);
        foo.enqueue(3);
        foo.enqueue(4);
        System.out.println(foo.dequeue());
        System.out.println(foo.dequeue());
        System.out.println(foo.dequeue());
        System.out.println(foo.dequeue());
    }
}
