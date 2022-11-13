import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Backend implements IBackend {

  private HashMap<String, Major> guideMajors;
  private Major userMajor;
  private HashSet<Course> userCourses;
  private HashMap<String, Course> allCourses;

  public Backend() {
    this.userMajor = null;
  }

  @Override
  public boolean setMajor(String userMajorEntered) {
    if (this.guideMajors.containsKey(userMajorEntered)) {
      this.userMajor = this.guideMajors.get(userMajorEntered);
      return true;
    }
    return false;
  }

  @Override
  public void addUserCourse(String userCourse) {
    if (this.allCourses.containsKey(userCourse)) {
      this.userCourses.add(this.allCourses.get(userCourse));
    }
  }

  @Override
  public ArrayList<Requirement> getMajorRequirements() {
    return this.userMajor.getRequirements();
  }

  @Override
  public HashSet<Course> checkRequirement(Requirement requirement) {
    HashSet<Course> toReturn = new HashSet<Course>();
    if (requirement.metBy(this.userCourses)) {
      return toReturn;
    } else {
      return requirement.couldTake(this.userCourses);
    }
  }
}
