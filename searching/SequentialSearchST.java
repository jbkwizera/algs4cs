import java.util.NoSuchElementException;
import java.util.Iterator;

public class SequentialSearchST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
    private Node first;
    private int N;
    private class Node {
        Key key;
        Value val;
        Node next;
        public Node(Key key, Value val, Node next) {
            this.key  = key;
            this.val  = val;
            this.next = next;
        }
    }
    /* shorthand methods */
    public int size()
    {   return N; }
    public boolean isEmpty()
    {   return size() == 0; }
    public boolean contains(Key key)
    {   return get(key) != null; }

    public void delete(Key key) {
        // lazy deletion
        put(key, null);

        // eager deletion
        if (first == null) throw new NoSuchElementException("ST is empty.");
        if (first.key.equals(key))
        {   first = first.next; N--; return; }

        for (Node x = first; x.next != null; x = x.next)
            if (x.next.key.equals(key))
            {   x.next = x.next.next; N--; return; }
    }
    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
                return x.val;
        return null;
    }
    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next)
            if (key.equals(x.key))
            {   x.val = val; return; }
        first = new Node(key, val, first);
        N++;
    }
    /*******************Iterator implementation ************************/
    public Iterator<Key> iterator()
    {   return new SequentialSearchSTIterator(); }

    private class SequentialSearchSTIterator implements Iterator<Key> {
        private Stack<Key> stk;
        private SequentialSearchSTIterator() {
            stk = new Stack<Key>();
            for (Node x = first; x != null; x = x.next)
                stk.push(x.key);
        }
        public boolean hasNext()
        {   return !stk.isEmpty(); }
        public Key next()
        {   return stk.pop(); }
    }

    public static void main(String[] args) {
        SequentialSearchST<String, Integer> st;
        st = new SequentialSearchST<String, Integer>();

        while (!StdIn.isEmpty()) {
            String key = StdIn.readString();
            if (!st.contains(key)) st.put(key, 0);
            st.put(key, st.get(key) + 1);
        }
        for (String key: st)
            StdOut.printf("%13s: %3d\n", key, st.get(key));
    }
}
