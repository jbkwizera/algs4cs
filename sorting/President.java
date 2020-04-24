import java.util.Comparator;
import java.util.Arrays;

public class President {
    // static comparators.
    private static final CountrySort COUNTRY_SORT = new CountrySort();
    private static final NameSort    NAME_SORT    = new NameSort();
    private static final AgeSort     AGE_SORT     = new AgeSort();
    private static final YearsSort   YEARS_SORT   = new YearsSort();

    private String country;
    private String name;
    private int years;
    private int age;

    private static class CountrySort implements Comparator<President> {
        public int compare(President x, President y) {
            return x.country.compareTo(y.country);
        }
    }
    private static class NameSort implements Comparator<President> {
        public int compare(President x, President y) {
            return x.name.compareTo(y.name);
        }
    }
    private static class AgeSort implements Comparator<President> {
        public int compare(President x, President y) {
            return x.age - y.age;
        }
    }
    private static class YearsSort implements Comparator<President> {
        public int compare(President x, President y) {
            return x.years - y.years;
        }
    }
    public President(String country, String name, int years, int age) {
        this.country = country;
        this.name    = name;
        this.years   = years;
        this.age     = age;
    }
    @Override
    public String toString() {
        return String.format("%-20s %-26s %2d %2d", country, name, years, age);
    }
    public static void main(String[] args) {
        President[] a = new President[54];
        for (int i = 0; i < 54; i++) {
            String[] fields = StdIn.readLine().split(",");
            int age   = 2019 - Integer.parseInt(fields[2].trim());
            int years = 2019 - Integer.parseInt(fields[3].trim());
            a[i] = new President(fields[0].trim(), fields[1].trim(), years, age);
        }

        Arrays.sort(a, President.YEARS_SORT);
        for (President p: a)
            StdOut.println(p);
    }
}
