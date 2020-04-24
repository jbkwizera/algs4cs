import java.util.NoSuchElementException;

public class ElemMinPQ<Key extends Comparable<Key>> {
    private Key[] pq = (Key[]) new Comparable[1];
    private int N = 0;

    public ElemMinPQ() { }
    public ElemMinPQ(int max) {
        pq = (Key[]) new Comparable[max];
    }
    public boolean isEmpty() { return N == 0; }
    public int size() { return N; }

    private void resize(int max) {
        Key[] temp = (Key[]) new Comparable[max];
        for (int i = 0; i < N; i++)
            temp[i]= pq[i];
        pq = temp;
    }
    public void insert(Key val) {
        // as push for stack.
        if (N == pq.length) resize(2*N);
        pq[N++] = val;
    }
    public Key delMin() {
        // as pop for stack.
        if (isEmpty()) throw new NoSuchElementException("queue is empty.");
        int j = 0;
        for (int i = 0; i < N; i++)
            if (pq[i].compareTo(pq[j]) < 0) j = i;
        Key val = pq[j];
        pq[j] = pq[--N];
        if (N > 0 && N < pq.length/4) resize(pq.length/2);
        return val;
    }
    public static void main(String[] args) {

    }
}
