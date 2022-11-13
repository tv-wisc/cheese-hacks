

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CourseAid {

  public static void main(String[] args) throws FileNotFoundException {
    ILoader Loader = new Loader();
    // load the books from the data file

    HashMap<String, Course> allCourses = Loader.coursesLoader("./cs.txt");
    
    HashMap<String, Major> guideMajors = Loader.majorLoader("./Majors.txt");
    
//    System.out.println(guideMajors.get("CS").getRequirements().get(0).getTitle());
    
    IBackend backend = new Backend(guideMajors, allCourses);

    Scanner userInputScanner = new Scanner(System.in);
    // instantiate the front end and pass references to the scanner, backend, and isbn validator to
    // it
    IFrontend frontend = new Frontend(userInputScanner, backend);
    // start the input loop of the front end
    frontend.runCommandLoop();
  }

}
