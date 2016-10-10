package week2;

import java.util.Iterator;

public class ResizingArrayStack<Item> implements Iterable<Item> {

    private Item[] stack;
    private int len = 0;

    public ResizingArrayStack() {
        stack = (Item[]) new Object[1];
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i = len;

        public boolean hasNext() {
            return i > 1;
        }

        public Item next() {
            return stack[--i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /*
     * Traditional implementation of stack based on linked lists
     */
    public boolean isEmpty() {
        return stack[0] == null;
    }

    public void push(Item str) {
        if (len == stack.length) {
            resize(2 * stack.length);
        }
        stack[len++] = str;
    }

    public Item pop() {
        Item item = stack[--len];
        stack[len] = null;
        if (len > 0 && len < stack.length / 4) {
            resize(stack.length / 2);
        }
        return item;
    }

    public void resize(int capacity) {
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < len; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public static void main(String[] args) {
        ResizingArrayStack foo = new ResizingArrayStack();
        foo.push("hello");
        foo.push("world");
        foo.push("you");
        System.out.println(foo.pop());
        System.out.println(foo.pop());
        System.out.println(foo.pop());
        System.out.println(foo.isEmpty());
    }
}