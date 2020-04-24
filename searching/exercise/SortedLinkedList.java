// @source: https://www.geeksforgeeks.org/binary-search-on-singly-linked-list/
public class SortedLinkedList<Item extends Comparable<Item>> {
    private Node first;
    private int N;

    private class Node {
        Item item;
        Node next;
        private Node(Item item, Node next)
        {   this.item = item; this.next = next; }
    }

    public void add(Item item)
    {   first = new Node(item, first); N++; }

    public Item binarySearch(Item item) {
        Node lo = first;
        Node hi = null;

        do {
            Node mid = middle(lo, hi);
            if (mid == null) return null;
            int cmp = mid.item.compareTo(item);
            if      (cmp > 0) lo = mid.next;
            else if (cmp < 0) hi = mid;
            else    return mid.item;
        } while (hi == null || hi != lo);
        return null;
    }
    private Node middle(Node lo, Node hi) {
        Node slow = lo;
        Node fast = lo.next;
        while (fast != hi) {
            fast = fast.next;
            if (fast != hi)
            {   slow = slow.next; fast = fast.next; }
        }
        return slow;
    }
    public static void main(String[] args) {

    }
}
