import java.util.Iterator;
import java.util.Arrays;
public class BST<Key extends Comparable<Key>, Value> implements Iterable<Key>{
    private Node root;
    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        private Node(Key key, Value val, int N)
        { this.key = key; this.val = val; this.N = N; }
    }
    public int size()
    {   return size(root); }
    private int size(Node x)
    {   return (x == null)? 0: x.N; }
    public Key min()
    {   return min(root).key; }
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }
    public Key max()
    {   return max(root).key; }
    private Node max(Node x) {
        if (x.right == null) return x;
        return min(x.right);
    }
    public int height()
    {   return height(root); }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x == null) return null;
        return x.key;
    }
    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp <  0) return floor(x.left, key);
        Node t = floor(x.right, key);
        if (t != null) return t;
        return x;
    }
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) return null;
        return x.key;
    }
    private Node ceiling(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp >  0) return ceiling(x.right, key);
        Node t = ceiling(x.left, key);
        if (t != null) return t;
        return x;
    }
    public Value get(Key key)
    {   return get(root, key); }
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.val;
    }

    public void put(Key key, Value val)
    {   root = put(root, key, val); }
    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
    public Key select(int k)
    {   return select(root, k).key; }
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if      (t > k) return select(x.left,  k);
        else if (t < k) return select(x.right, k-t-1);
        return x;
    }
    public int rank(Key key)
    {   return rank(root, key); }
    public int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return rank(x.right, key) + size(x.left) + 1;
        return  size(x.left);
    }
    public void deleteMin()
    {   root = deleteMin(root); }
    private Node deleteMin(Node x) {
        if (x == null) return null;
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    public void delete(Key key)
    {   root = delete(root, key); }
    public Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if      (cmp < 0) x.left  = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right,key);
        else {
            if (x.right == null) return x.left;
            if (x.left  == null) return x.right;
            Node t = x;  // keep x safe.
            x = min(t.right); // successor.
            x.right = deleteMin(t.right); // delete old min./what suxx was.
            x.left  = t.left;             // set x.left to t.left
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }
    /*----------------iterators-----------------------------*/
    public BSTIterator iterator()
    {   return new BSTIterator(); }
    private class BSTIterator implements Iterator<Key> {
        Queue<Key> q = new Queue<Key>();
        public BSTIterator()
        {   inorder(root); }
        private void inorder(Node x) {
            if (x == null) return;
            inorder(x.left);
            q.enqueue(x.key);
            inorder(x.right);
        }
        public boolean hasNext()
        {   return !q.isEmpty(); }
        public Key next()
        {   return q.dequeue();  }
    }
    public Iterable<Key> keys()
    {   return keys(min(), max()); }
    public Iterable<Key> keys(Key lo, Key hi) {
        Queue<Key> q = new Queue<Key>();
        keys(root, q, lo, hi);
        return q;
    }
    public void keys(Node x, Queue<Key> q, Key lo, Key hi) {
        if (x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) keys(x.left,  q, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) q.enqueue(x.key);
        if (cmphi > 0) keys(x.right, q, lo, hi);
    }
    public void printLevel() {
        Queue<Node> q = new Queue<Node>();
        q.enqueue(root);
        while (!q.isEmpty()) {
            Node x = q.dequeue();
            StdOut.print(x.key + " ");
            if (x.left  != null) q.enqueue(x.left);
            if (x.right != null) q.enqueue(x.right);
        }
        StdOut.println();
    }
    public static void main(String[] args) {
        BST<String, Integer> bst;
        bst = new BST<String, Integer>();
        String[] a = StdIn.readAllStrings();
        for (String s: a)
            bst.put(s, null);

        StdOut.println("size: " + bst.size());
        Stopwatch s = new Stopwatch();
        for (String key: bst)
            bst.get(key);
        StdOut.println("Took: " + s.elapsedTime());
        StdOut.println("height: " + bst.height());
    }
}
