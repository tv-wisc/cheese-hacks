import java.util.ArrayList;

public class Test {
  ArrayList<String> courseList = new ArrayList<>();

  public Test() {
    courseList.add("MATH 222");
    courseList.add("MATH 240");
    courseList.add("ECE 252");
    courseList.add("CS 300");
  };

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
    if(obj.charAt(0) == '(') {
      obj = (String) obj.subSequence(1, obj.length() - 1);
    }
    obj = obj.trim();
    return obj;
  }


  public boolean rec(String prereq) {
    if (checkSingle(prereq)) {
      if (courseList.contains(prereq)) {
        return true;
      }
      return false;
    } else if (checkAnd(prereq)) {
      int count = 0;
      while (count < prereq.length()) {
        if (returnValue(prereq, '&', count) == null) {
          continue;
        }
        if(!rec(returnValue(prereq, '&', count))) {
          return false;
        }
        if (prereq.charAt(count) == '(') {
          count+= 2 + returnValue(prereq, '&', count).length();
        }else {
          count += returnValue(prereq, '&', count).length();
        }
        if(count == prereq.length()) {
          return true;
        }
        if(prereq.charAt(count) == '&') {
          count++;
        }
      }
      return true;
    } else if (checkOr(prereq)) {
      int count = 0;
      while (count < prereq.length()) {
        if (returnValue(prereq, '|', count) == null) {
          continue;
        }
        if(rec(returnValue(prereq, '|', count))) {
          return true;
        }
        if (prereq.charAt(count) == '(') {
          count+= 2 + returnValue(prereq, '|', count).length();
        }else {
          count += returnValue(prereq, '|', count).length();
        }
        if(count == prereq.length()) {
          return false;
        }
        if(prereq.charAt(count) == '|') {
          count++;
        }
      }
      
      return false;
    }
    return true;
  }

}
