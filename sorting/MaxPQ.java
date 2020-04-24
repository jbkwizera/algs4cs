import java.util.NoSuchElementException;
import java.util.Comparator;

public class MaxPQ<Key extends Comparable<Key>> {
    private Comparator comparator = null;
    private Key[] pq = (Key[]) new Comparable[2];
    private int N = 0;

    public MaxPQ() {}
    public MaxPQ(int max) {
        pq = (Key[]) new Comparable[max+1];
    }
    public MaxPQ(int max, Comparator c) {
        pq = (Key[]) new Comparable[max+1];
        comparator = c;
    }
    // Resize the heap.
    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max+1];
        for (int i = 0; i <= N; i++)
            temp[i]= pq[i];
        pq = temp;
    }
    // Reheapify up the heap.
    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k/2, k);
            k = k/2;
        }
    }
    // Reheapify down the heap.
    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }
    public int size()        {   return N;      }
    public boolean isEmpty() {   return N == 0; }

    private boolean less(int i, int j) {
        if (comparator == null)
            return pq[i].compareTo(pq[j]) < 0;
        return comparator.compare(pq[i], pq[j]) < 0;
    }
    private void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
    public void insert(Key v) {
        if (N+1 == pq.length) resize(2*N);
        pq[++N] = v;
        swim(N);
    }
    public Key delMax() {
        if (isEmpty()) throw new NoSuchElementException("queue is empty.");
        Key mx = pq[1];
        exch(1, N--);
        pq[N+1] = null;
        sink(1);
        if (N > 0 && N < pq.length/4) resize(pq.length/2);
        return mx;
    }
    public static void main(String[] args) {
        // Write a client.

    }
}
