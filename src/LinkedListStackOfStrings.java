public class LinkedListStackOfStrings {

    private Node first = null;

    private class Node {
        String item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(String str) {
        Node oldFirst = first;
        first = new Node();
        first.item = str;
        first.next = oldFirst;
    }

    public String pop() {
        String temp = first.item;
        first = first.next;
        return temp;
    }

    public static void main(String[] args) {
        LinkedListStackOfStrings foo = new LinkedListStackOfStrings();
        foo.push("hello");
        foo.push("world");
        System.out.println(foo.isEmpty());
    }
}