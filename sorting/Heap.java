public class Heap {
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int N = a.length;

        for (int k = N/2; k >= 1; k--)
            sink(a, k, N);

        while (N > 1) {
            exch(a, 1, N--);
            sink(a, 1, N);
        }
    }
    /*----------------------------helper methods----------------------*/
    private static void sink(Comparable[] a, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(a, j, j+1)) j++;
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }
    private static void swim(Comparable[] a, int k, int N) {
        while (k > 1 && less(a, k, k/2)) {
            exch(a, k, k/2);
            k = k/2;
        }
    }
    // Sink uses 1-based indexing... convert to 0-based.
    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = temp;
    }
    private static boolean less(Comparable[] a, int i, int j) {
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    public static void main(String[] args) {
    }
}
