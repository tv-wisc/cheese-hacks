import java.io.FileNotFoundException;
import java.util.HashMap;

public interface ILoader {

  public HashMap<String, Major> majorLoader(String filePath) throws FileNotFoundException;

  public HashMap<String, Course> coursesLoader(String filePath) throws FileNotFoundException;

}
