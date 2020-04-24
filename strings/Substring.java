public class Substring {
    public static int search(String txt, String pat) {
        int N = txt.length(), i;
        int M = pat.length(), j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (txt.charAt(i) == pat.charAt(j)) j++;
            else {  i -= j; j = 0; }
        }
        if (j == M) return i - M;
        return N;
    }
    public static void main(String[] args) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 100000000; i++)
            s.append("a");
        s.append("b");

        StringBuilder t = new StringBuilder();
        for (int i = 0; i < 1000000; i++)
            t.append("a");
        t.append("b");

        String txt = new String(s);
        String pat = new String(t);

        KMP kmp = new KMP(pat);
        StdOut.println(kmp.search(txt));

        StdOut.println(txt.indexOf(pat));
    }
}
