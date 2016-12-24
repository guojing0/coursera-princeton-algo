package week2;

import edu.princeton.cs.algs4.StdOut;

public class Josephus {

    public static void main(String[] args) {
        LinkedListQueue<Integer> circle = new LinkedListQueue<>();
        for (int i = 0; i < Integer.parseInt(args[0]); i++) {
            circle.enqueue(i);
        }

        int partition = Integer.parseInt(args[1]);

        while (!circle.isEmpty()) {
            for (int i = 1; i <= partition; i++) {
                if (i < partition) {
                    circle.enqueue(circle.dequeue());
                } else {
                    StdOut.print(circle.dequeue() + " ");
                }
            }
        }
    }
}
