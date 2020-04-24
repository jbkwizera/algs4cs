import java.util.Comparator;
import java.util.Arrays;

public class Scratch {
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        StdOut.println(N);
        for (int i = 0; i < N; i++) {
            int x = StdRandom.uniform(0, 100);
            int y = StdRandom.uniform(0, 100);
            StdOut.println(x + " " + y);
        }
    }
}
