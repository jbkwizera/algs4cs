import java.util.Comparator;
import java.util.Arrays;
public class ClosestPair {
    private static class Point {
        private static final Comparator SORT_BY_X = new SortByX();
        private static final Comparator SORT_BY_Y = new SortByY();
        private int x;
        private int y;
        private Point(int x, int y)
        {   this.x = x; this.y = y; }
        public double distance(Point that) {
            double dx = x - that.x;
            double dy = y - that.y;
            return Math.sqrt(dx*dx + dy*dy);
        }
        public String toString()
        {   return String.format("(%2d, %2d)", x, y); }
    }
    private static class SortByX implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (a.x > b.x) return +1;
            if (a.x < b.x) return -1;
            return 0;
        }
    }
    private static class SortByY implements Comparator<Point> {
        public int compare(Point a, Point b) {
            if (a.y > b.y) return +1;
            if (a.y < b.y) return -1;
            return 0;
        }
    }
    private static void closest(Point[] pts) {
        int N = pts.length;
        double min = Integer.MAX_VALUE;
        Point a = null;
        Point b = null;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (pts[j].distance(pts[i]) < min) {
                    a = pts[i];
                    b = pts[j];
                    min = a.distance(b);
                }
            }
        }
        StdOut.println(a + " - " + b + ": " + min);
    }
    public static void main(String[] args) {
        int N = StdIn.readInt();
        Point[] pts= new Point[N];
        for (int i = 0; i < N; i++)
            pts[i] = new Point(StdIn.readInt(), StdIn.readInt());

        Arrays.sort(pts, Point.SORT_BY_X);
        for (Point pt: pts)
            StdOut.println(pt);
        Arrays.sort(pts, Point.SORT_BY_Y);
        StdOut.println("------------");
        for (Point pt: pts)
            StdOut.println(pt);
        StdOut.println("------------");
        closest(pts);
    }
}
