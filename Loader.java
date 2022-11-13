import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Loader implements ILoader {

  private HashMap<String, Major> allMajors;
  private HashMap<String, Course> allCourses;

  public Loader() {
    this.allMajors = new HashMap<String, Major>();
    this.allCourses = new HashMap<String, Course>();
  }

  @Override
  public HashMap<String, Course> coursesLoader(String filePath) throws FileNotFoundException {
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

      String firstLine = br.readLine();
      String title = firstLine.trim();

      for (String line; (line = br.readLine()) != null;) {

        String[] temp = line.split(",");

        String name = temp[0];
        int numCredits = Integer.valueOf(temp[1]);
        String prereqs = "";
        if (temp.length > 2) {
        prereqs = temp[2];
        }

        Course toAdd = new Course(name, numCredits, prereqs);

        this.allCourses.put(name, toAdd);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return this.allCourses;
  }

  @Override
  public HashMap<String, Major> majorLoader(String filePath) throws FileNotFoundException {

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

      String firstLine = br.readLine();
      String title = firstLine.trim();


      HashSet<Requirement> reqs = new HashSet<Requirement>();

      for (String line; (line = br.readLine()) != null;) {

        String[] temp = line.split(",");

        String reqName = temp[0];
        int numCredits = Integer.valueOf(temp[1]);
        int numCourses = Integer.valueOf(temp[2]);
        String coursesAvailable = temp[3];

        coursesAvailable = coursesAvailable.replaceAll("[&|]", ",").replaceAll("[()]", "");
        HashSet<Course> courses = new HashSet<Course>();

        for (String e : coursesAvailable.split(",")) {
          courses.add(allCourses.get(e));
        }

        reqs.add(new Requirement(courses, numCourses, numCredits, reqName));
      }
      this.allMajors.put(title, new Major(reqs, title));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return this.allMajors;
  }

}
