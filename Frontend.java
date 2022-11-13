import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Frontend implements IFrontend {

  private Scanner userInputScanner; // Scanner object that is declared as private variable
  private IBackend backend; // Object for access to the backend methods

  /**
   * The constructor. It takes the Scanner that will read user input as a parameter as well as the
   * backend.
   * 
   * @param userInputScanner
   * @param backend
   */
  public Frontend(Scanner userInputScanner, IBackend backend) {// TODO change to Backend Object
    this.userInputScanner = userInputScanner;
    this.backend = backend;
  }

  @Override
  public void runCommandLoop() {
    System.out.println("Welcome to the Course Aid!\n" + "**************************\n");

    boolean run = true;// to check if the loop should be run or exited

    while (run) {

      getMajor();

      enterCourses();

      checkRequirements();

      System.out.println("Run Again?(enter q to quit)");
    }

  }

  @Override
  public void getMajor() {
    while (true) {
      System.out.println("Please enter your major: \n");
      String userMajor = userInputScanner.next();
      if (backend.setMajor(userMajor)) {
        break;
      } else {
        System.out.println("Please enter valid major.\n");
      }
    }
  }

  @Override
  public void enterCourses() {
    while (true) {
      System.out.println("Please enter course you have taken(enter q when no more left): \n");
      String userCourse = userInputScanner.next();
      if (userCourse.equalsIgnoreCase("q")) {
        break;
      } else {
        backend.addUserCourse(userCourse);
      }
    }
  }

  @Override
  public void checkRequirements() {
    System.out.println("Please choose requirement to verify: \n");
    ArrayList<Requirement> reqs = backend.getMajorRequirements();

    for (int i = 0; i < reqs.size(); ++i) {
      System.out.println(i + 1 + ") " + reqs.get(i).getTitle());
    }

    String userReq = userInputScanner.next();

    HashSet<Course> toTake = backend.checkRequirement(reqs.get(Integer.parseInt(userReq)));

    System.out.println();
    if (toTake.size() == 0) {
      System.out.println("Requirement met!");
    } else {
      System.out.println("Requirement not met!\n" + "You may take these courses");
      for (Course e : toTake) {
        System.out.println(e);
      }
    }

  }

}
