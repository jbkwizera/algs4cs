import java.util.Iterator;
import java.util.Arrays;
import java.util.Scanner;

public class SkipList<Item extends Comparable<Item>> implements Iterable<Item> {
    private Node main;
    private Node auxi;
    private int N = 0;
    private int k;

    private class Node {
        Item item;
        Node next;
        Node conn;
        private Node(Item item, Node next)
        {   this.item = item; this.next = next; }
        private Node(Item item, Node next, Node conn)
        {   this.item = item; this.next = next; this.conn = conn; }
    }
    public SkipList(int capacity)
    {   int lg = (int) Math.log(capacity); k = capacity/lg;}

    public int size()
    {   return N;   }
    public void add(Item item) {
        main = new Node(item, main);
        if (N % k == 0)
        { auxi = new Node(item, auxi, main); }
        N++;
    }
    public boolean contains(Item item) {
        if (size() == 0) return false;
        Node prev = null;
        Node curr = auxi;
        while (curr != null && curr.item.compareTo(item) > 0)
        {   prev = curr.conn; curr = curr.next; }

        if (item.compareTo(auxi.item) >= 0) {
            for (Node x = main; x.item.compareTo(auxi.item) >= 0; x = x.next)
                if (x.item.compareTo(item) == 0)
                    return true;
        }
        else {
            while (prev != null && prev.item.compareTo(curr.item) >= 0) {
                if (prev.item.compareTo(item) == 0) return true;
                    prev = prev.next;
            }
        }
        return false;
    }
    public Iterator<Item> iterator()
    {   return new SLIterator();   }

    private class SLIterator implements Iterator<Item> {
        Node x = main;
        private SLIterator() {}
        public boolean hasNext()
        {   return x != null; }
        public Item next()
        {   Item item = x.item; x = x.next; return item; }
    }
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        SkipList<String> sl = new SkipList<String>(N);

        String[] a = StdIn.readAllStrings();
        Quick.sort(a);
        Stopwatch sw = new Stopwatch();
        for (String s: a) sl.add(s);
        StdOut.println("Took: " + sw.elapsedTime());
    }
}
