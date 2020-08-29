import java.util.Arrays;

public class Alphabet {

    // class constants
    public static final Alphabet BINARY      = new Alphabet("01");
    public static final Alphabet DNA         = new Alphabet("ACTG");
    public static final Alphabet OCTAL       = new Alphabet("01234567");
    public static final Alphabet DECIMAL     = new Alphabet("0123456789");
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");
    public static final Alphabet PROTEIN     = new Alphabet("ACDEFGHIKLMNPQRSTVWY");
    public static final Alphabet LOWERCASE   = new Alphabet("abcdefghijklmnopqrstuvwxyz");
    public static final Alphabet UPPERCASE   = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    public static final Alphabet BASE64      = new Alphabet("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789+/");
    public static final Alphabet ASCII       = new Alphabet(128);
    public static final Alphabet EXTENDED_ASCII= new Alphabet(256);
    public static final Alphabet UNICODE16   = new Alphabet(65536);

    private char[] chr;
    private int[] ord;
    private int R;

    public Alphabet(String s) {
        chr = s.toCharArray();
        R   = chr.length;

        boolean[] seen = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < chr.length; i++) {
            char c = chr[i];
            if (seen[c])
                throw new IllegalArgumentException("Illegal alphabet string: " + c + " repeated.");
            seen[c] = true;
        }

        ord = new int[Character.MAX_VALUE];
        Arrays.fill(ord, -1);

        for (int c = 0; c < R; c++)
            ord[chr[c]] = c;
    }
    private Alphabet(int radix) {
        chr = new char[radix];
        ord = new int[radix];
        R = radix;
        for (int i = 0; i < radix; i++) {
            chr[i] = (char) i;
            ord[i] = i;
        }
    }
    public Alphabet() { this(256); }


    public char toChar(int index) {
        if (index < 0 || index >= R)
            throw new IllegalArgumentException("Index out of range.");
        return chr[index];
    }

    public int toIndex(char c) {
        if (ord[c] == -1)
            throw new IllegalArgumentException("Character " + c + " not in alphabet.");
        return ord[c];
    }

    public boolean contains(char c) {
        return ord[c] != -1;
    }

    public int R() {
        return R;
    }

    public int lgR() {
        double x = Math.log(R);
        return (int)(x / Math.log(2));
    }

    public int[] toIndices(String s) {
        int[] indices = new int[s.length()];
        for (int i = 0; i < s.length(); i++)
            indices[i] = toIndex(s.charAt(i));
        return indices;
    }
    public String toChars(int[] indices) {
        char[] chars = new char[indices.length];
        for (int i = 0; i < indices.length; i++)
            chars[i] = toChar(indices[i]);
        return new String(chars);
    }
    public static void main(String[] args) {
        String s = "nGb6BgkLzwI3dWE9/r5vi1A8pXMS0tYCy7fUTaOxmNjRDHQuhloZFKcPe4sV2J+q-! ";
        Alphabet a = new Alphabet(s);

        int[] indices = new int[args[0].length()];
        for (int i = 0; i < args[0].length(); i++)
            indices[i] = a.toIndex(args[0].charAt(i));

        StdOut.println(a.toChars(indices));
    }
}
