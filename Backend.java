import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Backend implements IBackend {

  private HashMap<String, Major> guideMajors;
  private Major userMajor;
  private HashSet<Course> userCourses;
  private HashMap<String, Course> allCourses;

  public Backend(HashMap<String, Major> guideMajors, HashMap<String, Course> allCourses) {
    this.allCourses = allCourses;
    this.guideMajors = guideMajors;
  }

  @Override
  public boolean setMajor(String userMajorEntered) {
    if (this.guideMajors.containsKey(userMajorEntered)) {
      this.userMajor = this.guideMajors.get(userMajorEntered);
      return true;
    }
    return false;
  }

  @Override
  public void addUserCourse(String userCourse) {
    if (this.allCourses.containsKey(userCourse)) {
      this.userCourses.add(this.allCourses.get(userCourse));
    }
  }

  @Override
  public ArrayList<Requirement> getMajorRequirements() {
    return this.userMajor.getRequirements();
  }

  @Override
  public HashSet<Course> checkRequirement(Requirement requirement) {
    HashSet<Course> toReturn = new HashSet<Course>();
    if (requirement.metBy(this.userCourses)) {
      return toReturn;
    } else {
      HashSet<Course> toCheck = requirement.couldTake(this.userCourses);

      for (Course c : toCheck) {
        if (c.getPrereqs().length() != 0) {
          if (meetPrereqs(c.getPrereqs())) {
            toReturn.add(c);
          }
        } else {
          toReturn.add(c);
        }
      }

    }
    return toReturn;
  }

  public boolean checkSingle(String prereq) {
    for (int i = 0; i < prereq.length(); i++) {
      if (prereq.charAt(i) == '&' || prereq.charAt(i) == '|' || prereq.charAt(i) == '(') {
        return false;
      }
    }
    return true;
  }

  public boolean checkAnd(String prereq) {
    int count = 0;
    for (int i = 0; i < prereq.length(); i++) {
      if (prereq.charAt(i) == '&' && count == 0) {
        return true;
      } else if (prereq.charAt(i) == '(') {
        count++;
      } else if (prereq.charAt(i) == ')') {
        count--;
      }
    }
    return false;
  }

  public boolean checkOr(String prereq) {
    int count = 0;
    for (int i = 0; i < prereq.length(); i++) {
      if (prereq.charAt(i) == '|' && count == 0) {
        return true;
      } else if (prereq.charAt(i) == '(') {
        count++;
      } else if (prereq.charAt(i) == ')') {
        count--;
      }
    }
    return false;
  }

  public String returnValue(String prereq, char delim, int count) {
    String obj = new String();
    if (count >= prereq.length()) {
      return null;
    }
    while (count < prereq.length() && prereq.charAt(count) != delim) {
      obj += prereq.charAt(count);
      count++;
      continue;
    }
    if (obj.charAt(0) == '(') {
      obj = (String) obj.subSequence(1, obj.length() - 1);
    }
    obj = obj.trim();
    return obj;
  }

  public boolean meetPrereqs(String prereqs) {
    if (checkSingle(prereqs)) {
      for (Course c : this.userCourses) {
        if (c.getName().equals(prereqs)) {
          return true;
        }
      }
      return false;
    } else if (checkAnd(prereqs)) {
      int count = 0;
      while (count < prereqs.length()) {
        if (returnValue(prereqs, '&', count) == null) {
          continue;
        }
        if (!meetPrereqs(returnValue(prereqs, '&', count))) {
          return false;
        }
        if (prereqs.charAt(count) == '(') {
          count += 2 + returnValue(prereqs, '&', count).length();
        } else {
          count += returnValue(prereqs, '&', count).length();
        }
        if (count == prereqs.length()) {
          return true;
        }
        if (prereqs.charAt(count) == '&') {
          count++;
        }
      }
      return true;
    } else if (checkOr(prereqs)) {
      int count = 0;
      while (count < prereqs.length()) {
        if (returnValue(prereqs, '|', count) == null) {
          continue;
        }
        if (meetPrereqs(returnValue(prereqs, '|', count))) {
          return true;
        }
        if (prereqs.charAt(count) == '(') {
          count += 2 + returnValue(prereqs, '|', count).length();
        } else {
          count += returnValue(prereqs, '|', count).length();
        }
        if (count == prereqs.length()) {
          return false;
        }
        if (prereqs.charAt(count) == '|') {
          count++;
        }
      }

      return false;
    }
    return true;
  }


}
