public class LinkedListQueue<Item> {

    private Node first, last;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(Item item) {
        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }
    }

    public Item dequeue() {
        Item temp = first.item;
        first = first.next;
        if (isEmpty()) {
            last = null;
        }
        return temp;
    }

    public static void main(String[] args) {
        LinkedListQueue foo = new LinkedListQueue();
        System.out.println(foo.isEmpty());
        foo.enqueue("hello");
        foo.enqueue("world");
        foo.enqueue("you");
        System.out.println(foo.dequeue());
        System.out.println(foo.dequeue());
        System.out.println(foo.dequeue());
        System.out.println(foo.isEmpty());
    }

}