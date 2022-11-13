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
    int numCoursesTakenByUser = 0;
    int numCreditsTakenByUser = 0;

    for (var c : courses) {
      if (this.courses.contains(c)) {
        numCoursesTakenByUser++;
        numCreditsTakenByUser += c.getCredits();

        if (numCoursesTakenByUser >= this.numCourses && numCreditsTakenByUser >= this.numCredits) {
          return true;
        }
      }
    }

    return false;
  }

  public HashSet<Course> couldTake(HashSet<Course> courses) {
    @SuppressWarnings("unchecked")
    HashSet<Course> reqCourses = (HashSet<Course>) this.courses.clone();
    reqCourses.removeAll(courses);
    return reqCourses;
  }
}
