public class QuickUnionUF {
    private int[]  id;
    private int count;
    // Initialize N sites with integer names (0 to N-1).
    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++)
            id[i] = i;
    }
    // Number of components.
    public int count() {
        return count;
    }
    // Return true if p and q are in the same component.
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    // Add connection between p and q.
    public void union(int p, int q) {
        // Give p and q the same root.
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) return;

        id[pRoot] = qRoot;
        count--;
    }
    // Component identifier for p (0 to N-1).
    public int find(int p) {
        // Follow links to find a root.
        while (p != id[p]) p = id[p];
        return p;
    }
    // Print pairs that are not connected.
    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnionUF qu = new QuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (qu.connected(p, q)) continue;
            qu.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(qu.count() + " components.");
    }
}
