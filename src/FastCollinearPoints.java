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

            Arrays.sort(copyPoints, origin.slopeOrder());
            Arrays.sort(copySlopeArr);

            for (int k = 0; k < points.length - 3; k++) {
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