
/**
 * @author Saksham Jain
 */
public interface IFrontend {

  /**
   * The constructor that the implementation this interface will provide. It takes the Scanner that
   * will read user input as a parameter as well as the backend.
   * 
   * @return
   */
  // Frontend(Scanner userInputScanner, ICustomDictionaryBackend backend);

  /**
   * This method starts the command loop for the frontend, and will terminate when the user exists
   * the app.
   */
  public void runCommandLoop();

  public void getMajor();

  public void enterCourses();

  public void checkRequirements();
}
