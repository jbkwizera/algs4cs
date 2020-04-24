public class QuickFindUF {
    private int[]  id;
    private int count;
    // Initialize N sites with integer names (0 to N-1).
    public QuickFindUF(int N) {
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
        int pID = find(p);
        int qID = find(q);
        if (pID == qID) return;

        for (int i = 0; i < id.length; i++)
            if (id[i] == pID) id[i] = qID;
        count--;
    }
    // Component identifier for p (0 to N-1).
    public int find(int p) {
        return id[p];
    }
    // Print pairs that are not connected.
    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickFindUF qf = new QuickFindUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (qf.connected(p, q)) continue;
            qf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(qf.count() + " components.");
    }
}
