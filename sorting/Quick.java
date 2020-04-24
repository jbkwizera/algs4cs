import java.util.Comparator;

public class Quick {
    public static void sort(Comparable[] a) {
        // Shuffle & find max.
        int N = a.length;
        int k = 0;
        for (int i = 0; i < N; i++) {
            if (less(a[k], a[i])) k = i;
            int r = i + StdRandom.uniform(N-i);
            Comparable temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
        Comparable temp = a[k];
        a[k] = a[N-1];
        a[N-1] = temp;

        // Sort.
        sort3w(a, 0, N-1);
    }
    // sort with a comparator
    public static void sort(Comparable[] a, Comparator comparator) {
        // Shuffle & find max.
        int N = a.length;
        int k = 0;
        for (int i = 0; i < N; i++) {
            if (less(a[k], a[i], comparator)) k = i;
            int r = i + StdRandom.uniform(N-i);
            Comparable temp = a[i];
            a[i] = a[r];
            a[r] = temp;
        }
        Comparable temp = a[k];
        a[k] = a[N-1];
        a[N-1] = temp;

        // Sort.
        sort3w(a, 0, N-1, comparator);
    }
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo + 15) { Insertion.sort(a, lo, hi); return; }
        int j = partition(a, lo, hi);   // Partition.
        sort(a, lo, j-1);               // Sort left.
        sort(a, j+1, hi);               // Sort right.
    }

    // 3-way partitioning: much more efficient for arrays with
    // many duplicate keys.
    public static void sort3w(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, i = lo, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }
        sort3w(a, lo, lt-1);
        sort3w(a, gt+1, hi);
    }
    // sort3w with a comparator.
    public static void sort3w(Comparable[] a, int lo, int hi, Comparator comparator) {
        if (hi <= lo) return;
        int lt = lo, i = lo, gt = hi;
        Comparable v = a[lo];
        while (i <= gt) {
            int cmp = comparator.compare(a[i], v);
            if      (cmp < 0) exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }
        sort3w(a, lo, lt-1, comparator);
        sort3w(a, gt+1, hi, comparator);
    }
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi+1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) ;
            while (less(v, a[--j])) ;
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
    // less with comparator.
    public static boolean less(Comparable x, Comparable y, Comparator comparator) {
        return comparator.compare(x, y) < 0;
    }
    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1]))
                return false;
        return true;
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Integer[] a = new Integer[N];
        for (int i = 0; i < N; i++)
            a[i] = StdRandom.uniform(0, 100);

        sort(a);
        StdOut.println(isSorted(a));
        for (int i = 0; i < N; i++)
            StdOut.print(a[i] + " ");
        StdOut.println();
    }
}
