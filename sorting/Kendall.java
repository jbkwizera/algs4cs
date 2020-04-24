public class Kendall {
    public static int eval(int[] A, int[] B) {
        int N = A.length;
        int[] Ai = new int[N];
        int[] Bi = new int[N];
        for (int i = 0; i < N; i++) {
            Ai[A[i]] = i;
            Bi[B[i]] = i;
        }
        int count = 0;
        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                if ((Ai[i]-Ai[j]) * (Bi[i]-Bi[j]) < 0)
                    count++;
        return count;
    }
    public static void main(String[] args) {
        int[] A = {0, 3, 1, 6, 2, 5, 4};
        int[] B = {1, 0, 3, 6, 4, 2, 5};

        StdOut.println(eval(A, B));
        StdOut.println(Inversion.eval(A));
        StdOut.println(Inversion.eval(B));
    }
}
