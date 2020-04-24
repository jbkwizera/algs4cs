import java.util.NoSuchElementException;

public class IndexMinPQ<Item extends Comparable<Item>> {
    private int maxN;       // Max. number of items on PQ.
    private int N;          // Number of items on PQ.
    private int[] pq;       // Bin. heap using 1-based indexing.
    private int[] qp;       // Inverse of pq - qp[pq[i]] = pq[qp[i]] = i
    private Item[] items;   // items[i] = priority of i.

    public IndexMinPQ(int maxN) {
        this.maxN = maxN;
        N = 0;
        items = (Item[]) new Comparable[maxN+1];
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }
    public int size() { return N; }
    public boolean isEmpty() { return N == 0; }

    public boolean contains(int k) {
        return qp[k] != -1;
    }
    public void insert(int k, Item item) {
        N++;
        qp[k] = N;
        pq[N] = k;
        items[k] = item;
        swim(N);
    }
    public int minIndex() {
        return pq[1];
    }
    public Item min() {
        return items[pq[1]];
    }
    public int delMin() {
        int min = pq[1];
        exch(1, N--);
        sink(1);
        qp[min]   = -1;
        items[min]= null;
        pq[N+1]   = -1;
        return min;
    }
    public void change(int k, Item item) {
        items[k] = item;
        swim(qp[k]);
        sink(qp[k]);
    }
    public Item delete(int k) {
        int index = qp[k];
        Item item = items[k];
        swim(index);
        sink(index);
        items[k] = null;
        qp[k] = -1;
        return item;
    }

    /*-----------------------------helper methods---------------------*/
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        return items[pq[i]].compareTo(items[pq[j]]) > 0;
    }
    private void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    public static void main(String[] args) {
        // Write a client.
    }
}
