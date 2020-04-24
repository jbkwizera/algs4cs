public class Insertion {
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++)
            for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }
    public static <Key extends Comparable<Key>> void sort(Key[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j-1]); j--)
                exch(a, j, j-1);
    }
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i <= hi; i++)
            if (less(a[i], a[i-1]))
                return false;
        return true;
    }
    public static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    public static void main(String[] args) {
        Integer[] a = {1, 2, 3};
        sort(a);
    }
}
