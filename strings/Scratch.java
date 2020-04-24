import java.util.*;
public class Scratch {
    public static void dfa(String pat) {
        int M = pat.length();
        int R = 256;
        int[][] dfa = new int[R][M];
        dfa[pat.charAt(0)][0] = 1;
        StdOut.print("  ");
        for (int j = 0; j < M; j++)
            StdOut.print(j + " ");
        StdOut.println();
        for (int j = 1, X = 0; j < M; j++) {
            // dfa[][j]
            for (int c = 0; c < R; c++)
                dfa[c][j] = dfa[c][X];
            dfa[pat.charAt(j)][j] = j+1;
            X = dfa[pat.charAt(j)][X];
        }
        for (int i = 0; i < R; i++) {
            boolean nonzero = false;
            for (int j = 0; j < M; j++) {
                if (dfa[i][j] != 0)
                {   nonzero = true; break; }
            }
            if (nonzero) {
                StdOut.print((char) i + " ");
                for (int j = 0; j < M; j++)
                    StdOut.print(dfa[i][j] + " ");
                StdOut.println();
            }
        }
    }
    public static void main(String[] args) {
        String pat = args[0];
        dfa(pat);
    }
}
