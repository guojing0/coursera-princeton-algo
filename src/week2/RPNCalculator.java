package week2;

import java.util.Scanner;

public class RPNCalculator {

    public static void main(String[] args) {
        System.out.println(calc(InfixToPostfix.transform(args)));
    }

    public static double calc(String expr) {
        LinkedListStack<Double> val = new LinkedListStack<>();
        Scanner scanner = new Scanner(expr);

        while (scanner.hasNext()) {
            double val1, val2;
            String str = scanner.next();

            if (str.equals("+")) {
                val.push(val.pop() + val.pop());
            } else if (str.equals("-")) {
                val2 = val.pop();
                val1 = val.pop();
                val.push(val1 - val2);
            } else if (str.equals("*")) {
                val.push(val.pop() * val.pop());
            } else if (str.equals("/")) {
                val2 = val.pop();
                val1 = val.pop();
                val.push(val1 / val2);
            } else if (str.equals("^")) {
                val2 = val.pop();
                val1 = val.pop();
                val.push(Math.pow(val1, val2));
            }
            else {
                val.push(Double.parseDouble(str));
            }
        }
        return val.pop();
    }

}
