import java.util.*;

public class Count {
    public static void main(String[] args) {
        String source = "nGb6BgkLzwI3dWE9/r5vi1A8pXMS0tYCy7fUTaOxmNjRDHQuhloZFKcPe4sV2J+q-!";
        StdOut.println(source);
        Alphabet alpha = new Alphabet(source);

        int R = alpha.R();
        int[] count = new int[R];

        String s = args[0];
        int N = s.length();
        for (int i = 0; i < N; i++)
            if (alpha.contains(s.charAt(i)))
                count[alpha.toIndex(s.charAt(i))]++;

        StdOut.println(alpha.R());
        for (int c = 0; c < R; c++)
            if (count[c] != 0)
                StdOut.println(alpha.toChar(c) + ": " + count[c]);
    }
}
