import java.util.Arrays;

public class BruteCollinearPoints {

    private int num = 0;
    private LineSegment[] segment;

    public BruteCollinearPoints(Point[] points) {
        if (points == null || Arrays.asList(points).contains(null)) {
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
                            segment[p] = new LineSegment(temp[0], temp[3]);
                            num++;
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

}
