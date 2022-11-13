import java.util.ArrayList;
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
    System.out.println("Welcome to the Custom searcher!\n" + "*******************************\n");

    boolean run = true;// to check if the loop should be run or exited

    while (run) {
      displayMainMenu();

      String userMajor = userInputScanner.next();
      backend.setMajor(userMajor);

      enterCourses();

      checkRequirements();
    }

  }

  @Override
  public void displayMainMenu() {
    System.out.println("Please enter your major: \n");
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
    ArrayList<Requirements> reqs = backend.getMajorRequirements();

    for (int i = 0; i < reqs.size(); ++i) {
      System.out.println(i + 1 + ") " + reqs.get(i).getTitle());
    }

    String userReq = userInputScanner.next();

    ArrayList<Course> toTake = backend.checkRequirement(reqs.get(Integer.parseInt(userReq)));

    System.out.println();
    if (toTake.size() == 0) {
      System.out.println("Requirement met!");
    } else {
      System.out.println("Requirement not met!\n" + "You may take these courses");
      for (int i = 0; i < toTake.size(); ++i) {
        System.out.println(i + 1 + ") " + toTake.get(i));
      }
    }

  }

}
