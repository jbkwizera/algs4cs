public class MergeBU {
	private static Comparable[] aux;
	public static <Key extends Comparable<Key>> void sort(Key[] a) {
		int N = a.length;
		aux = new Comparable[N];
		for (int sz = 1; sz < N; sz = sz+sz)			// sz: subarray size
			for (int lo = 0; lo < N-sz; lo += sz+sz)	// lo: subarray index
				merge(a, lo, lo+sz-1, Math.min(lo+sz+sz-1, N-1));
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
	public static boolean less(Comparable x, Comparable y) {
		return x.compareTo(y) < 0;
	}
	public static void main(String[] args) {
		Character[] a = {'E', 'X', 'A', 'M', 'P', 'L', 'E'};
		sort(a);
		for (char ch: a)
			StdOut.print(ch + " ");
		StdOut.println();
	}
}
