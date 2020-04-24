import java.util.Iterator;
import java.util.Hashtable;
import java.util.Arrays;

public class SeparateChainingHashST<Key extends Comparable<Key>, Value>{
    private int N;  // # of key-value pairs
    private int M;  // hash table size
    private SequentialSearchST<Key, Value>[] st;

    public SeparateChainingHashST()
    {   this(997);  }

    public int size()
    {   return N; }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<Key, Value>[]) new SequentialSearchST[M];
        for (int i = 0; i < M; i++)
            st[i] = new SequentialSearchST();
    }
    private int hash(Key key)
    {   return (key.hashCode() & 0x7fffffff) % M; }

    public Value get(Key key)
    {   return st[hash(key)].get(key);  }

    public int sizeOfKey(Key key)
    {   return st[hash(key)].size(); }

    public void put(Key key, Value val)
    {   st[hash(key)].put(key, val); N++; }

    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);

        SeparateChainingHashST<String, Integer> st;
        st = new SeparateChainingHashST<String, Integer>();

        //Hashtable<String, Integer> ht;
        //ht = new Hashtable<String, Integer>();
        String[] a = StdIn.readAllStrings();
        for (String s: a) {
            //String s = StdIn.readString();
            if (s.length() < min) continue;
            if (st.get(s) == null) st.put(s, 0);
            st.put(s, st.get(s) + 1);
        }
        /*
        String mx = "";
        ht.put(mx, 0);
        for (String s: ht.keySet())
            if (s.length() >= min)
                if (ht.get(s) > ht.get(mx))
                    mx = s;
        StdOut.printf("%15s %8d\n", mx, ht.get(mx)); */

        Histogram hist = new Histogram(990);
        Arrays.sort(a);
        if (a[0].length() >= min) {
            hist.addDataPoint(st.sizeOfKey(a[0]));
            StdOut.println(st.sizeOfKey(a[0]));
        }
        int N = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i].equals(a[i-1])) continue;
            hist.addDataPoint(st.sizeOfKey(a[i]));
            StdOut.println(st.sizeOfKey(a[i]));
            N++;
        }
        StdOut.println(a.length*1.0/997);
        hist.draw();
    }
}
// S:22 E:10 A:20 R:14 C:69 H:4 X: 47 M:49 P:78 L:38
