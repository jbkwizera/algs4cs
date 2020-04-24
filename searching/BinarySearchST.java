import java.util.NoSuchElementException;
import java.util.Iterator;

public class BinarySearchST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private Key[] keys;
    private Value[] vals;
    private int N = 0;

    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    public int size()
    {   return N; }
    public boolean isEmpty()
    {   return size() == 0; }
    public boolean contains(Key key) {
        int i = rank(key);
        return keys[i] != null && key.compareTo(keys[i]) == 0;
    }
    public Key min()
    {   return keys[0]; }
    public Key max()
    {   return keys[N-1]; }
    public Key select(int k)
    {   return keys[k]; }
    public Key ceiling(Key key)
    {   int i = rank(key); return keys[i]; }
    public void deleteMin()
    {   if (N > 0) delete(keys[rank(keys[0])]); N--; }

    public void deleteMax()
    {   if (N > 0) delete(keys[rank(keys[N-1])]); N--; }
    // exercise 3.1.17
    public Key floor(Key key) {
        int i = rank(key);
        if (i*keys[i].compareTo(key) == 0) return keys[i];
        return keys[i-1];
    }
    // exercise 3.1.16
    public void delete(Key key) {
        int i = rank(key);
        if (key.compareTo(keys[i]) < 0) throw new NoSuchElementException();

        for (int j = i; j < N; j++)
        {   keys[j] = keys[j+1]; vals[j] = vals[j+1]; }
        keys[N-1] = null; vals[N-1] = null;
        N--;
    }
    public Value get(Key key) {
        int i = rank(key);
        if (key.compareTo(keys[i]) == 0) return vals[i];
        return null;
    }

    public void put(Key key, Value val) {
        int i = rank(key);
        if (i < N && key.compareTo(keys[i]) == 0)
        {   vals[i] = val; return; }

        for (int j = N; j > i; j--)
        {   keys[j] = keys[j-1]; vals[j] = vals[j-1];   }
        keys[i] = key; vals[i] = val;
        N++;
    }
    public int rank(Key key) {
        int lo = 0, hi = N-1;
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            int cmp = key.compareTo(keys[mid]);
            if      (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else    return mid;
        }
        return lo;
    }

    /*––––––––––––––––––––––iterator implementation––––––––––––––––––––––––––*/
    public Iterator<Key> iterator()
    {   return new BinarySearchSTIterator(); }

    private class BinarySearchSTIterator implements Iterator<Key> {
        private int i = 0;
        public boolean hasNext()
        {   return i < N; }
        public Key next()
        {   return keys[i++]; }
    }

    public static void main(String[] args) {

        BinarySearchST<String, Integer> bst;
        bst = new BinarySearchST<String, Integer>(15000);
        String[] a = StdIn.readAllStrings();
        Stopwatch sw = new Stopwatch();
        for (String s: a)
            bst.put(s, null);
        StdOut.println("Took: " + sw.elapsedTime());
    }
}
