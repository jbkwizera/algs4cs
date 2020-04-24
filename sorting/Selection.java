public class Selection {
	public static <Key extends Comparable<Key>> void sort(Key[] a) {
		int N = a.length;
		for (int i = 0; i < N; i++) {
			int min = i;
			for (int j = i+1; j < N; j++)
				if (less(a[j], a[min])) min = j;
			exch(a, i, min);
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
		Integer[] a = {1, 2, 3};
		sort(a);
	}
}
