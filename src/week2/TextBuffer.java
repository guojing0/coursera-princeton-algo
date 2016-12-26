package week2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

public class TextBuffer {

    private LinkedListStack<Character> buffer;
    private LinkedListStack<Character> temp;
    private LinkedListStack<Character> restoreBuffer;

    public TextBuffer() {
        buffer = new LinkedListStack<>();
        temp = new LinkedListStack<>();
        restoreBuffer = new LinkedListStack<>();
    }

    public int size() {
        int n = 0;
        for (char c : buffer) {
            n++;
        }
        return n;
    }

    public void insert(char c) {
        buffer.push(c);
    }

    public char delete() {
        char c = buffer.pop();
        restoreBuffer.push(c);
        return c;
    }

    public char restore() {
        char c = restoreBuffer.pop();
        buffer.push(c);
        return c;
    }

    public void left(int k) {
        while (k-- > 0) {
            temp.push(buffer.pop());
        }
    }

    public void right(int k) {
        while (k-- > 0) {
            buffer.push(temp.pop());
        }
    }

    public String toString() {
        while (!temp.isEmpty()) {
            buffer.push(temp.pop());
        }

        String s = "";

        Iterator iter = buffer.iterator();
        while (iter.hasNext()) {
            s = iter.next() + s;
        }
        return s;
    }

    public static void main(String[] args) { // to be or not to be
        TextBuffer foo = new TextBuffer();
        for (int i = 0; i < args.length; i++) {
            for (int j = 0; j < args[i].length(); j++) {
                foo.insert(args[i].charAt(j));
            }
        }

        StdOut.println(foo);
        foo.left(2);
        foo.insert(' ');
        foo.left(3);
        foo.insert(' ');
        foo.left(4);
        foo.insert(' ');
        foo.left(3);
        foo.insert(' ');
        foo.right(2);
        foo.insert(' ');
        foo.insert('o');
        foo.insert('r');
        foo.delete();
        foo.delete();
        foo.delete();
        foo.restore();
        foo.restore();
        foo.restore();

        StdOut.println(foo);
    }
}
