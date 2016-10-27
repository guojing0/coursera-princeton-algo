package week3;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;

public class BruteCollinearPoints {

    private int num = 0;
    private LineSegment[] segment;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        segment = new LineSegment[points.length];

        for (int p = 0; p < points.length; p++) {
            for (int q = p + 1; q < points.length; q++) {
                double pqSlope = points[p].slopeTo(points[q]);
                if (pqSlope == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }
                for (int r = q + 1; r < points.length; r++) {
                    double prSlope = points[p].slopeTo(points[r]);
                    if (pqSlope != prSlope) {
                        continue;
                    }
                    for (int s = r + 1; s < points.length; s++) {
                        if (pqSlope == prSlope && pqSlope == points[p].slopeTo(points[s])) {
                            Point[] temp = {points[p], points[q], points[r], points[s]};
                            Arrays.sort(temp);
                            segment[num++] = new LineSegment(temp[0], temp[3]);
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return num;
    }

    public LineSegment[] segments() {
        return Arrays.copyOf(segment, num);
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
