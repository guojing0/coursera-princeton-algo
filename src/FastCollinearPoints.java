import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

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
                    Point[] temp = {copyPoints[k + 1], copyPoints[k + 2], copyPoints[k + 3]};
                    Arrays.sort(temp);
                    if (origin.isSmaller(temp[0])) {
                        segment[num++] = new LineSegment(origin, temp[2]);
                    } else {
                        segment[num++] = new LineSegment(origin, temp[0]);
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

        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }

        Point[] foo = new Point[16];
        foo[0] = new Point(1, 1);
        foo[1] = new Point(1, 2);
        foo[2] = new Point(1, 3);
        foo[3] = new Point(1, 4);
        foo[4] = new Point(2, 1);
        foo[5] = new Point(2, 2);
        foo[6] = new Point(2, 3);
        foo[7] = new Point(2, 4);
        foo[8] = new Point(3, 1);
        foo[9] = new Point(3, 2);
        foo[10] = new Point(3, 3);
        foo[11] = new Point(3, 4);
        foo[12] = new Point(4, 1);
        foo[13] = new Point(4, 2);
        foo[14] = new Point(4, 3);
        foo[15] = new Point(4, 4);
        FastCollinearPoints fcp = new FastCollinearPoints(foo);
        System.out.println(fcp.numberOfSegments());
        System.out.println(Arrays.toString(fcp.segments()));
    }

}