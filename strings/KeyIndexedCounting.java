public class KeyIndexedCounting {
    private static class Record {
        private String key;
        private    int val;
        public Record(String n, int s)
        {   key = n; val = s; }
        public String toString()
        {   return String.format("%-8s %2d", key, val); }
    }
    public static void main(String[] args) {

        int N = StdIn.readInt();
        int R = StdIn.readInt();

        Record[] a = new Record[N];
        for (int i = 0; i < N; i++) {
            String key = StdIn.readString();
            int    val = StdIn.readInt();
            a[i] = new Record(key, val);
        }

        Record[] aux = new Record[N];
        int[] count  = new int[R+1];

        // build count
        for (int i = 0; i < N; i++)
            count[a[i].val + 1]++;  // makes the next step easier when r = 0.

        // counts to starting indexes
        for (int r = 0; r < R; r++)
            count[r+1] += count[r];

        // distribute records in aux.
        for (int i = 0; i < N; i++)
            aux[count[a[i].val]++] = new Record(a[i].key, a[i].val);

        // (deep) copy back.
        for (int i = 0; i < N; i++) {
            a[i] = new Record(aux[i].key, aux[i].val);
            StdOut.println(a[i]);
        }
    }
}
