public class AFPrez {
    public static void main(String[] args) {
        ST<String, ST<String, String>> st;
        st = new ST<String, ST<String, String>>();

        int[][] ages = new int[3][54];
        int k = 0;
        while (!StdIn.isEmpty()) {
            String[] a = StdIn.readLine().split(",");
            String count = a[0].trim();

            st.put(count, new ST<String, String>());
            st.get(count).put("name",  a[1].trim());
            st.get(count).put("birth", a[2].trim());
            st.get(count).put("time",  a[3].trim());

            int born = Integer.parseInt(a[2].trim());
            int time = Integer.parseInt(a[3].trim());
            int asm  = time - born;
            int cur  = 2018 - time;
            int age  = 2018 - born;
            ages[0][k] = asm;
            ages[1][k] = cur;
            ages[2][k] = age;
            k++;
            StdOut.printf("%-20s %-26s %7s %7s %2d %2d %2d\n", a[0], a[1], a[2], a[3], asm, cur, age);
            President prez = new President(a[0], a[1], cur, age);

        }
        Histogram.draw(ages[1]);
    }
}
