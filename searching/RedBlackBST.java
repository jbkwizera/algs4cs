import java.util.NoSuchElementException;
public class RedBlackBST<Key extends Comparable<Key>, Value> extends BST<Key, Value> {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;
    private class Node {
        Key key;            // key.
        Value val;          // associated data.
        Node left, right;   // subtrees.
        int N;              // # nodes in this subtree.
        boolean color;      // color of link from parent.
        private Node(Key key, Value val, int N, boolean color) {
            this.key   = key;
            this.val   = val;
            this.N     = N;
            this.color = color;
        }
    }
    public int size()
    {   return size(root); }
    private int size(Node x)
    {   return (x == null)? 0: x.N; }

    public int height()
    {   return height(root); }
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }
    public boolean isEmpty()
    {   return size(root) == 0; }
    /*-------------------------------------------*/
    // get + put. get is inherited from BST
    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }
    private Node put(Node h, Key key, Value val) {
        if (h == null)
            return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if      (cmp < 0) h.left = put(h.left,  key, val);
        else if (cmp > 0) h.right= put(h.right, key, val);
        else h.val = val;
        h = balance(h);
        return h;
    }


    /**********************deletion*************************/
    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST is empty.");
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
    }
    private Node deleteMin(Node h) {
        if (h.left == null) return null;
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    /***********************************************************/
    /*  private helper functions
    /***********************************************************/
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = 1 + size(h.left) + size(h.right);
        return x;
    }
    private void flipColors(Node h) {
        h.color = !h.color;
        h.left.color  = !h.left.color;
        h.right.color = !h.right.color;
    }
    private Node balance(Node h) {
        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.N = 1 + size(h.left) + size(h.right);
        return h;
    }
    private Node moveRedLeft(Node h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }
    private Node moveRedRight(Node h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }
    public static void main(String[] args) {
        RedBlackBST<String, Integer> bst;
        bst = new RedBlackBST<String, Integer>();
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
