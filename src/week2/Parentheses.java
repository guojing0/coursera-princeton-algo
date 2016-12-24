package week2;

import edu.princeton.cs.algs4.StdOut;

public class Parentheses {

    public static void main(String[] args) {
        StdOut.println(verify(args));
    }

    public static boolean verify(String[] str) {
        LinkedListStack<String> stack = new LinkedListStack<>();

        for (int i = 0; i < str.length; i++) {
            String s = str[i];

            if (s.equals("(") || s.equals("[") || s.equals("{")) {
                stack.push(s);
            }

            if (s.equals(")") && !stack.pop().equals("(")) {
                return false;
            } else if (s.equals("]") && !stack.pop().equals("[")) {
                return false;
            } else if (s.equals("}") && !stack.pop().equals("{")) {
                return false;
            }
        }
        return true;
    }
}
