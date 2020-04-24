
public class Inversion {
    private static int[] aux;
    public static int eval(int[] a) {
        int N = a.length;
        aux = new int[N];
        return eval(a, 0, N-1);
    }
    public static int eval(int[] a, int lo, int hi) {
        if (lo >= hi) return 0;
        int mid = (lo + hi)/ 2;
        return eval(a, lo, mid) + eval(a, mid+1, hi) + merge(a, lo, mid, hi);
    }
    public static int merge(int[] a, int lo, int mid, int hi) {
        int i = lo;
        int j = mid+1;
        int count = 0;
        for (int k = lo; k <= hi; k++)
            aux[k] = a[k];
        for (int k = lo; k <= hi; k++) {
            if      (i > mid)         a[k] = aux[j++];
            else if (j >  hi)         a[k] = aux[i++];
            else if (aux[i] <= aux[j])a[k] = aux[i++];
            else { count += mid+1-i;  a[k] = aux[j++]; }
        }
        return count;
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int[] a = new int[N];
        for (int k = 0; k < N; k++)
            a[k] = StdRandom.uniform(1000);

        int count  = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                if (a[i] > a[j]) count++;
        StdOut.println(count + " :: " + eval(a));
    }
}
