public class VisualAccumulator extends Accumulator {
    public VisualAccumulator(int points, double max) {
        StdDraw.setXscale(0, points);
        StdDraw.setYscale(0, max);
        StdDraw.setPenRadius(0.005);
    }
    public void addDataValue(double val) {
        N++;
        sum += val;
        StdDraw.setPenColor(StdDraw.DARK_GRAY);
        StdDraw.point(N, val);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.point(N, sum/N);
    }
}
