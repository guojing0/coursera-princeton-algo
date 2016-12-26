package week2;

public class InfixToPostfix {

    public static void main(String[] args) {
        System.out.println(transform(args));
    }

    public static String transform(String[] str) {
        LinkedListQueue<String> res = new LinkedListQueue<>();
        LinkedListStack<String> temp = new LinkedListStack<>();

        String output = "";

        for (int i = 0; i < str.length; i++) {
            String s = str[i];

            if (s.equals("(")) {

            } else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/") || s.equals("^")) {
                temp.push(s);
            } else if (s.equals(")")) {
                res.enqueue(temp.pop());
            } else {
                res.enqueue(s);
            }
        }

        while (!res.isEmpty()) {
            output += res.dequeue() + " ";
        }

        return output;
    }
}
