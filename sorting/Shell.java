public class Shell {
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        int N = a.length;
        int h = 1;
        while (h < N/3) h = 3*h + 1;

        while (h >= 1) {
            for (int i = h; i < N; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
                    exch(a, j, j-h);
            }
            h = h/3;
        }
    }
    public static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
    public static void main(String[] args) {
        sort(args);
        StdOut.println("sorted args: ");
        for (String s: args)
            StdOut.println(s);
    }
}
