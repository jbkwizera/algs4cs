public class KMP {
    private int[][] dfa;
    private String pat;
    public KMP(String pat) {
        this.pat = pat;
        int M = pat.length();
        int R = 256;
        dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        for (int j = 1, x = 0; j < M; j++) {
            // dfa[][j]
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][x];
            dfa[pat.charAt(j)][j] = j+1;
            x = dfa[pat.charAt(j)][x];
        }
    }
    public int search(String txt) {
        int N = txt.length(), i;
        int M = pat.length(), j;
        for (i = 0, j = 0; i < N && j < M; i++)
            j = dfa[txt.charAt(i)][j];
        if (j == M) return i - M;
        return N;
    }
    public static void main(String[] args) {
        String pat = (new In(args[0])).readAll().trim();
        String txt = (new In(args[1])).readAll().trim();

        pat = txt.substring(txt.length()-1001);
        KMP kmp = new KMP(pat);
        StdOut.println(kmp.search(txt));
    }
}
