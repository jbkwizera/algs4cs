public class Bubble {
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int N = a.length;
        boolean f = true;
        while (f) {
            f = false;
            for (int i = 1; i < N; i++) {
                if (less(a, i, i-1)) {
                    exch(a, i, i-1);
                    f = true;
                }
            }
        }
    }
    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    public static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Bubble.sort(a);
        for (String s: a)
            StdOut.println(s);
    }
}
