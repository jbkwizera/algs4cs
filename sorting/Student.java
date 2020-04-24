import java.util.Comparator;
import java.util.Arrays;

public class Student implements Comparable<Student>{
    private double pts = 0.0;
    private String ID  = "";

    // comparators...
    private static final PTS_COMPARATOR PTS_SORT = new PTS_COMPARATOR();
    private static final ID_COMPARATOR  ID_SORT  = new ID_COMPARATOR();

    public Student(String pts, String ID) {
        this.pts = Double.parseDouble(pts);
        this.ID  = ID;
    }

    public double pts() { return pts; }
    public String ID()  { return ID;  }


    @Override
    public int compareTo(Student that) {
        if (this.pts > that.pts) return +1;
        if (this.pts < that.pts) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%8s  %5.2f", ID, pts);
    }

    //---------------------------------------------------------------
    // comparators static classes...
    private static class PTS_COMPARATOR implements Comparator<Student> {
        public int compare(Student x, Student y) {
            if (x.pts > y.pts) return +1;
            if (x.pts < y.pts) return -1;
            return 0;
        }
    }
    private static class ID_COMPARATOR  implements Comparator<Student> {
        public int compare(Student x, Student y) {
            return (x.ID).compareTo(y.ID);
        }
    }
    public static void main(String[] args) {
        String[] fields = new String[112];

        for (int i = 0; i < 112; i++)
            fields[i] = StdIn.readString();

        Student[] students = new Student[56];
        for (int i = 0; i < 56; i++)
            students[i] = new Student(fields[i], fields[i+56]);

        Quick.sort(students, PTS_SORT);
        double sum = 0.0;
        for (Student st: students) {
            StdOut.println(st);
            sum += st.pts();
        }
        StdOut.printf("avg: %5.2f\n", sum/students.length);
    }
}
