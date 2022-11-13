
public class Course {
  private String name;
  private int credits;
  private String prereqs;
  
  public Course(String name, int credits, String prereqs) {
    this.name = name;
    this.credits = credits;
    this .prereqs = prereqs;
    
  }
  
  public String getName() {
    return this.name;
  }
  
  public int getCredits() {
    return this.credits;
  }
  
  public String getPrereqs() {
    return this.prereqs;
  }

}
