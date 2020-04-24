
public class GPACalculator {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);

        String[] a = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D", "F"};
        double[] b = {4.33, 4, 3.67, 3.33, 3, 2.67, 2.33, 2, 1.67, 1, 0};

        BinarySearchST<String, Double> gpaST;
        gpaST = new BinarySearchST<String, Double>(11);
        for (int i = 0; i < 11; i++)
            gpaST.put(a[i], b[i]);

        double sum = 0.0;
        for (int i = 0; i < N; i++)
            sum += gpaST.get(StdIn.readString());

        StdOut.printf("OVERALL GPA: %4.2f\n", sum/N);
    }
}
