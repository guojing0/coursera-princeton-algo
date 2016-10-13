package week2;

public class RPNCalculator {

    public static void main(String[] args) {
        LinkedListStack<Double> val = new LinkedListStack<>();

        for (int i = 0; i < args.length; i++) {
            double val1, val2;
            String str = args[i];

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
        System.out.println(val.pop());
    }

}
