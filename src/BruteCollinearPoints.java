import java.util.Arrays;

public class BruteCollinearPoints {

    private int num = 0;
    private LineSegment[] segment;

    public BruteCollinearPoints(Point[] points) {
        if (points == null || Arrays.asList(points).contains(null)) {
            throw new NullPointerException();
        }

        int len = points.length;
        Point[] seg = new Point[len];
        segment = new LineSegment[len];

        for (int i = 0; i < len; i++) {
            seg[i] = points[i];
        }

        for (int p = 0; p < len; p++) {
            for (int q = p + 1; q < len; q++) {
                double pqSlope = seg[p].slopeTo(seg[q]);
                if (pqSlope == Double.NEGATIVE_INFINITY) {
                    throw new IllegalArgumentException();
                }
                for (int r = p + 2; r < len; r++) {
                    double prSlope = seg[p].slopeTo(seg[r]);
                    if (pqSlope != prSlope) {
                        continue;
                    }
                    for (int s = p + 3; s < len; s++) {
                        if (pqSlope == prSlope && pqSlope == seg[p].slopeTo(seg[s])) {
                            Point[] temp = {seg[p], seg[q], seg[r], seg[s]};
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
        return Arrays.copyOf(segment, segment.length);
    }

    public static void main(String[] args) {
        Point[] foo = new Point[8];
        foo[0] = new Point(1, 1);
        foo[1] = new Point(2, 2);
        foo[2] = new Point(3, 3);
        foo[3] = new Point(4, 4);
        foo[4] = new Point(5, 5);
        foo[5] = new Point(1, 2);
        foo[6] = new Point(2, 1);
        foo[7] = new Point(4, 5);

        BruteCollinearPoints bcp = new BruteCollinearPoints(foo);
        System.out.println(bcp.numberOfSegments());
        System.out.println(Arrays.toString(bcp.segments()));
    }
}
