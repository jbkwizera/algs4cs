import java.util.Arrays;
public class Ksmallest {
    // Shrink and partition until j is equal to k.
    public static Comparable select(Comparable[] a, int k) {
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if      (j == k) return a[k];
            else if (j > k)  hi = j - 1;
            else if (j < k)  lo = j + 1;
        }
        return a[k];
    }
    public static double median(Comparable[] a) {
        int N = a.length;
        int x = (int)(select(a, N/2));
        int y = (int)(select(a, N/2+1));

        if (N % 2 == 1) return x;
        return (x + y)/2.0;
    }
    // Every partition a number gets into its final position j.
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;
        Comparable v = a[lo];

        while (true) {
            while (less(a[++i], v)) if (i == hi) break;
            while (less(v, a[--j])) if (j == lo) break;
            if (i >= j) break;
            exch(a, i, j);
        }
        exch(a, lo, j);
        return j;
    }
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static boolean less(Comparable x, Comparable y) {
        return x.compareTo(y) < 0;
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);

        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(100);
        StdOut.println(Arrays.toString(a));
        StdOut.println(select(a, k-1));
        StdOut.println(Arrays.toString(a));
    }
}
