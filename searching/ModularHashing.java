public class ModularHashing {
    public static int hash(String x, int M) {
        return (x.hashCode() & 0x7fffffff) % M;
    }
    public static void main(String[] args) {
        int N = 0;
        int M = 997;
        int[] dist = new int[M];


        Histogram hist = new Histogram(M);
        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            dist[hash(s, M)]++;
            hist.addDataPoint(hash(s, M));
            N++;
            StdOut.println(s + ": " + hash(s, 16));
        }
        //hist.draw();
    }
}
