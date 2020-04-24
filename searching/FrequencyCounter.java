public class FrequencyCounter {

    public static void main(String[] args) {
        int min = Integer.parseInt(args[0]);
        SeparateChainingHashST<String, Integer> st;
        st = new SeparateChainingHashST<String, Integer>();
        String[] a = StdIn.readAllStrings();
        for (String s: a) {
            if (s.length() <  min) continue;
            if (st.get(s) == null) st.put(s, 0);
            st.put(s, st.get(s) + 1);
        }
        String max = "";
        if (st.get(max) == null) st.put(max, 0);
        for (String s: a)
            if (s.length() >= min)
                if (st.get(s) > st.get(max))
                    max = s;
    }
}
