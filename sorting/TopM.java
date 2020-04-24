public class TopM {
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);
        MinPQ<Transaction> pq;
        pq = new MinPQ<Transaction>(M+1, new Transaction.HowMuchOrder());

        while (StdIn.hasNextLine()) {
            pq.insert(new Transaction(StdIn.readLine()));
            if (pq.size() > M) pq.delMin();
        }
        Stack<Transaction> stack;
        stack = new Stack<Transaction>();
        while (!pq.isEmpty()) stack.push(pq.delMin());
        for (Transaction t: stack)
            StdOut.println(t);
    }
}
