public class xMerge {
    private static Comparable[] aux;
    public static <Key extends Comparable<Key>> void sort(Key[] a) {
        aux = new Comparable[a.length];
        sort(a, 0, a.length-1);
    }
    public static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        if (hi - lo < 15) {
            Insertion.sort(a, lo, hi);
            return;
        }
        else {
            sort(a, lo,   mid);
            sort(a, mid+1, hi);
            if (less(a[mid+1], a[mid]))
                merge(a, lo, mid, hi);
        }
    }
    public static void merge(Comparable[] a, int lo, int mid, int hi) {
        int i = lo, j = mid+1;

        for (int k = lo; k <= hi; k++)   // Copy a into aux.
            aux[k] = a[k];

        for (int k = lo; k <= hi; k++) { // Sort aux into a.
            if      (i > mid)              a[k] = aux[j++];
            else if (j >  hi)              a[k] = aux[i++];
            else if (less(aux[i], aux[j])) a[k] = aux[i++];
            else                           a[k] = aux[j++];
        }
    }
    public static boolean less(Comparable a, Comparable b) {
		return a.compareTo(b) < 0;
	}
    public static void main(String[] args) {

    }
}
