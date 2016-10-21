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
                            segment[num++] = new LineSegment(points[p], points[s]);
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

        BruteCollinearPoints bcp = new BruteCollinearPoints(foo);
        System.out.println(bcp.numberOfSegments());
        System.out.println(Arrays.toString(bcp.segments()));
    }

}
