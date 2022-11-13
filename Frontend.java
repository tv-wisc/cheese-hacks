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
  public Frontend(Scanner userInputScanner, IBackend backend) {
    this.userInputScanner = userInputScanner;
    this.backend = backend;
  }

  @Override
  public void runCommandLoop() {
    System.out.println("Welcome to the Course Aid!\n" + "**************************\n");

    boolean run = true;// to check if the loop should be run or exited

    while (run) {

      getMajor();
      userInputScanner.nextLine();

      enterCourses();

      checkRequirements();

      System.out.println("\nRun Again?(y/n)\n");

      if (userInputScanner.next().equalsIgnoreCase("n")) {
        System.out.println("Goodbye!");
        break;
      }
    }

  }

  @Override
  public void getMajor() {
    while (true) {
      System.out.println("\nPlease enter your major: \n");
      String userMajor = userInputScanner.next();
      if (backend.setMajor(userMajor)) {
        break;
      } else {
        System.out.print("Major not valid. ");
      }
    }
  }

  @Override
  public void enterCourses() {
    while (true) {
      System.out.println("\nPlease enter course you have taken(enter q when no more left): \n");
      String userCourse = userInputScanner.nextLine().trim();
      if (userCourse.equalsIgnoreCase("q")) {
        break;
      } else {
        backend.addUserCourse(userCourse);
      }
    }
  }

  @Override
  public void checkRequirements() {
    System.out.println("\nPlease choose requirement to verify: \n");
    ArrayList<Requirement> reqs = backend.getMajorRequirements();

    for (int i = 0; i < reqs.size(); ++i) {
      System.out.println(i + 1 + ") " + reqs.get(i).getTitle());
    }
    System.out.println();

    String userReq = userInputScanner.next();

    // System.out.println(reqs.get(Integer.parseInt(userReq) - 1));

    HashSet<Course> toTake = backend.checkRequirement(reqs.get(Integer.parseInt(userReq) - 1));

    System.out.println();
    if (toTake.size() == 0) {
      System.out.println("Requirement met!");
    } else {
      System.out.println("Requirement not met!\n" + "You may take these courses: \n");
      for (Course e : toTake) {
        System.out.println(e);
      }

      System.out.println("\nAll courses left to complete requirement: \n");
      backend.allRequiredCourses(reqs.get(Integer.parseInt(userReq) - 1));
    }

  }

}
