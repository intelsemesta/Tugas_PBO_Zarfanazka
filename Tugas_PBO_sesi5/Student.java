public class Student extends Person {
    private static int numCourses = 0;
    private String[] courses = {};
    private int[] grades = {};

    public Student(String name, String address) {
        super(name, address);
    }

    @Override
    public String toString() {
        return "Student: " + getName() + "(" + getAddress() + ")";
    }

    public void addCourseGrade(String course, int grade) {
        // Cek apakah course sudah ada
        for (String c : courses) {
            if (c != null && c.equals(course)) {
                System.out.println("  [!] Mata kuliah '" + course + "' sudah ada.");
                return;
            }
        }
        // Tambah course dan grade baru
        String[] newCourses = new String[courses.length + 1];
        int[] newGrades = new int[grades.length + 1];
        for (int i = 0; i < courses.length; i++) {
            newCourses[i] = courses[i];
            newGrades[i] = grades[i];
        }
        newCourses[courses.length] = course;
        newGrades[grades.length] = grade;
        courses = newCourses;
        grades = newGrades;
        numCourses++;
        System.out.println("  [+] Mata kuliah '" + course + "' dengan nilai " + grade + " berhasil ditambahkan.");
    }

    public void printGrades() {
        if (courses.length == 0) {
            System.out.println("  Tidak ada mata kuliah yang diambil.");
            return;
        }
        System.out.println("  Daftar Nilai " + getName() + ":");
        for (int i = 0; i < courses.length; i++) {
            System.out.printf("    - %-20s : %d%n", courses[i], grades[i]);
        }
    }

    public double getAverageGrade() {
        if (grades.length == 0) return 0.0;
        int total = 0;
        for (int g : grades) {
            total += g;
        }
        return (double) total / grades.length;
    }

    public static int getNumCourses() {
        return numCourses;
    }

    public String[] getCourses() {
        return courses;
    }

    public int[] getGrades() {
        return grades;
    }
}
