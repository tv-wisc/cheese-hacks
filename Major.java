import java.util.ArrayList;
import java.util.HashSet;

public class Major {
  private HashSet<Requirement> requirements;
  private String name;

  public Major(HashSet<Requirement> requirements, String name) {
    this.requirements = requirements;
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public ArrayList<Requirement> getRequirements() {
    ArrayList<Requirement> requirements = new ArrayList<Requirement>();

    for (var r : this.requirements) {
      requirements.add(r);
    }

    return requirements;
  }
}
