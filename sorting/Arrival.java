import java.util.Comparator;
import java.util.Arrays;

public class Arrival implements Comparable<Arrival> {
    // comparators.
    private static final WhatTime TIME_SORT = new WhatTime();
    private static final WhatName NAME_SORT = new WhatName();

    private static class WhatTime implements Comparator<Arrival> {
        public int compare(Arrival x, Arrival y) {
            int xsecs = x.hr*3600 + x.mn*60 + x.sc;
            int ysecs = y.hr*3600 + y.mn*60 + y.sc;
            return xsecs - ysecs;
        }
    }
    public static class WhatName implements Comparator<Arrival> {
        public int compare(Arrival x, Arrival y) {
            return x.name.compareTo(y.name);
        }
    }

    // instance variables and methods
    private String name, time;
    private int hr, mn, sc;
    public Arrival(String data) {
        String[] dfields = data.split(" ");
        String[] tfields = dfields[1].split(":");

        name = dfields[0];
        time = dfields[1];

        hr = Integer.parseInt(tfields[0]);
        mn = Integer.parseInt(tfields[1]);
        sc = Integer.parseInt(tfields[2]);
    }
    @Override
    public int compareTo(Arrival that) {
        return this.name.compareTo(that.name);
    }

    @Override
    public String toString() {
        return name + " " + time;
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        Arrival[] a = new Arrival[N];

        for (int i = 0; i < N; i++)
            a[i] = new Arrival(StdIn.readLine());

        Quick.sort(a, Arrival.NAME_SORT);
        for (Arrival arr: a)
            StdOut.println(arr);
        StdOut.println("----------------");
    }
}
