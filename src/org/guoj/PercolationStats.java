package org.guoj;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private double[] prob;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }

        prob = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int openNum = 0;

            for (int j = 0; j < Math.pow(n, 2); j++) {
                int row = StdRandom.uniform(1, n + 1); // TODO consider +1 or not
                int column = StdRandom.uniform(1, n + 1);

                if (!percolation.isOpen(row, column)) {
                    percolation.open(row, column);
                    ++openNum;
                }

                if (percolation.percolates()) {
                    prob[i] = openNum / Math.pow(n, 2);
                }
            }
        }

    }

    public double mean() {
        return StdStats.mean(prob);
    }

    public double stddev() {
        return prob.length == 1 ? Double.NaN : StdStats.stddev(prob);
    }

    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(prob.length);
    }

    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(prob.length);
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        PercolationStats p = new PercolationStats(n, trials);

        System.out.println("mean                    = " + p.mean());
        System.out.println("stddev                  = " + p.stddev());
        System.out.println("95% confidence interval = " + p.confidenceLo() + ", " + p.confidenceHi());
    }
}