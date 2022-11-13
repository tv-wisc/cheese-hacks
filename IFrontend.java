
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

  // to help make it easier to test the functionality of this program,
  // the following helper methods will help support runCommandLoop():

  public void displayMainMenu(); // prints command options to System.out

  public void enterCourses(); // modifies the definition of the word

  public void checkRequirements(); // deletes the chosen word

}