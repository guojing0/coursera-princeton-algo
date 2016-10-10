public class ResizingArrayStackOfStrings {

    private String[] stack;
    private int len = 0;

    public ResizingArrayStackOfStrings() {
        stack = new String[1];
    }

    public boolean isEmpty() {
        return stack[0] == null;
    }

    public void push(String str) {
        if (len == stack.length) {
            resize(2 * stack.length);
        }
        stack[len++] = str;
    }

    public String pop() {
        String item = stack[--len];
        stack[len] = null;
        if (len > 0 && len < stack.length / 4) {
            resize(stack.length / 2);
        }
        return item;
    }

    public void resize(int capacity) {
        String[] temp = new String[capacity];
        for (int i = 0; i < len; i++) {
            temp[i] = stack[i];
        }
        stack = temp;
    }

    public static void main(String[] args) {
        ResizingArrayStackOfStrings foo = new ResizingArrayStackOfStrings();
        foo.push("hello");
        foo.push("world");
        foo.push("you");
        System.out.println(foo.pop());
        System.out.println(foo.pop());
        System.out.println(foo.pop());
        System.out.println(foo.isEmpty());
    }
}