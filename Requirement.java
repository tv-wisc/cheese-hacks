import java.util.HashSet;

public class Requirement {
    private HashSet<Course> courses;
    private String title;
    private int numCourses;
    private int numCredits;

    public Requirement(HashSet<Course> courses, int numCourses, int numCredits) {
        this.courses = courses;
        this.numCourses = numCourses;
        this.numCredits = numCredits;
    }

    public String getTitle() {
        return this.title;
    }

    public int getNumCourses() {
        return this.numCourses;
    }

    public int getNumCredits() {
        return this.numCredits;
    }

    public boolean metBy(HashSet<Course> courses) {
        int numCourses = 0;
        int numCredits = 0;

        for (var c : courses) {
            if (this.courses.contains(c)) {
                numCourses++;
                numCredits += c.getCredits();

                if (numCourses >= this.numCourses &&
                    numCredits >= this.numCredits) {
                    return true;
                }
            }
        }

        return false;
    }

    public HashSet<Course> couldTake(HashSet<Course> courses) {
        HashSet<Course> reqCourses = this.courses.clone();
        reqCourses.removeAll(courses);
        return reqCourses;
    }
}
