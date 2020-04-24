public class WeightedQuickUnionUF {
    private int[]  id;
    private int[]  sz;
    private int count;

    // Initialize N sites with integer names (0 to N-1).
    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
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
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        // Make smaller root point to large one.
        if (sz[i] < sz[j]) { id[i] = j; sz[j] += sz[i]; }
        else               { id[j] = i; sz[i] += sz[j]; }
        count--;
    }
    // Component identifier for p (0 to N-1).
    public int find(int p) {
        // Follow links to find a root.
        while (p != id[p]) {
            id[p] = id[id[p]]; // For path compression.
            p = id[p];
        }
        return p;
    }
	public int size(int p) {
		int root = find(p);
		return sz[root];
	}
    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            //StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components.");
		boolean[] comp = new boolean[N];
		for (int i = 0; i < N; i++) {
			int id = uf.find(i);
			comp[id] = true;
		}
		for (int i = 0; i < N; i++)
			if (comp[i])
                StdOut.printf("%7d %6d\n", i, uf.size(i));
    }
}
