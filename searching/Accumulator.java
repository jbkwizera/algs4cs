public class Accumulator {
    protected double sum;
    protected int N;

    public Accumulator() {}

    public   void addDataValue(double val) { sum += val; N++; }
    public double mean()                   { return sum/N; }
    public String toString() {
        return "Mean (" + N + " values): " +
                String.format("%7.5f", mean());
    }
}
