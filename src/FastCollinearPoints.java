import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Arrays;

public class FastCollinearPoints {

    private int num = 0;
    private LineSegment[] segment;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException();
        }

        segment = new LineSegment[points.length];
        double[] slopeArr = new double[points.length - 1];

        for (int i = 0; i < points.length; i++) {
            Point origin = points[i];

            for (int j = i + 1; j < points.length; j++) {
                if (origin == points[j]) {
                    throw new IllegalArgumentException();
                }
                slopeArr[j - 1] = origin.slopeTo(points[j]);
            }

            Point[] copyPoints = Arrays.copyOf(points, points.length);
            double[] copySlopeArr = Arrays.copyOf(slopeArr, slopeArr.length);

            Arrays.sort(copyPoints, i, copyPoints.length, origin.slopeOrder());
            Arrays.sort(copySlopeArr, i, copySlopeArr.length);

            for (int k = i; k < points.length - 3; k++) {
                if (copySlopeArr[k] == copySlopeArr[k + 1] && copySlopeArr[k] == copySlopeArr[k + 2]) {
                    segment[num++] = new LineSegment(origin, copyPoints[k + 3]);
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

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}