public class Teacher extends Person {
    private static int numCourses = 0;
    private String[] courses = {};

    public Teacher(String name, String address) {
        super(name, address);
    }

    @Override
    public String toString() {
        return "Teacher: " + getName() + "(" + getAddress() + ")";
    }

    public boolean addCourse(String course) {
        // Return false jika course sudah ada
        for (String c : courses) {
            if (c != null && c.equals(course)) {
                return false;
            }
        }
        String[] newCourses = new String[courses.length + 1];
        for (int i = 0; i < courses.length; i++) {
            newCourses[i] = courses[i];
        }
        newCourses[courses.length] = course;
        courses = newCourses;
        numCourses++;
        return true;
    }

    public boolean removeCourse(String course) {
        // Return false jika course tidak ada
        int removeIndex = -1;
        for (int i = 0; i < courses.length; i++) {
            if (courses[i].equals(course)) {
                removeIndex = i;
                break;
            }
        }
        if (removeIndex == -1) return false;

        String[] newCourses = new String[courses.length - 1];
        int idx = 0;
        for (int i = 0; i < courses.length; i++) {
            if (i != removeIndex) {
                newCourses[idx++] = courses[i];
            }
        }
        courses = newCourses;
        numCourses--;
        return true;
    }

    public static int getNumCourses() {
        return numCourses;
    }

    public String[] getCourses() {
        return courses;
    }
}
