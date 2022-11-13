import java.util.ArrayList;

public interface IBackend {
  
  public void setMajor(String userMajor);
  
  public void addUserCourse(String userCourse);

  public ArrayList<Requirements> getMajorRequirements();

  public ArrayList<Course> checkRequirement(Requirements requirements);

}

