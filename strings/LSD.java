public class LSD {
    public static void sort(String[] a)
    {   sort(a, a[0].length()); }
    private static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
        String[] t = new String[N];
        for (int d = W-1; d >= 0; d--) {
            // do key-indexed counting in dth char.
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++) // frequency counts
                count[a[i].charAt(d) + 1]++;
            for (int r = 0; r < R; r++) // starting indexes.
                count[r+1] += count[r];
            for (int i = 0; i < N; i++) // distribute the keys in t
                t[count[a[i].charAt(d)]++] = a[i];
            for (int i = 0; i < N; i++) // copy back
                a[i] = t[i];
        }
    }
    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        LSD.sort(a);
        for (String s: a)
            StdOut.println(s);
    }
}
