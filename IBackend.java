import java.util.ArrayList;
import java.util.HashSet;

public interface IBackend {

  public boolean setMajor(String userMajor);

  public void addUserCourse(String userCourse);

  public ArrayList<Requirement> getMajorRequirements();

  public HashSet<Course> checkRequirement(Requirement requirement);
  
  public void allRequiredCourses(Requirement requirement);

}

