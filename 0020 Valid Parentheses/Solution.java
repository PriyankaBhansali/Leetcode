// https://leetcode.com/problems/valid-parentheses/

import java.util.*;

// METHOD 1:
public class Solution {
  public static boolean isValid(String s) {
    Stack<Character> stack = new Stack<Character>();

    // we are comparing it with char and not string so c == ' ' and not c == " "
    for (char c: s.toCharArray()) {
      if (c == '(') {
        stack.push(')');
      } else if (c == '[') {
        stack.push(']');
      } else if ( c == '{') {
        stack.push('}');
      } else if (stack.isEmpty() || c != stack.pop()) {
        return false;
      }
    }
    return stack.isEmpty();
  }
  
  public static void main(String[] args) {
    System.out.println(isValid("([)]")); // false
    System.out.println(isValid("([]){}")); // true
  }
}

// METHOD 2:
public class Solution {
  public static String isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(' || c == '[' ||c == '{')
        stack.push(c);
      else if (c == ')' && !stack.empty() && stack.peek() == '(')
        stack.pop();
      else if (c == ']' && !stack.empty() && stack.peek() == '[')
        stack.pop();
      else if (c == '}' && !stack.empty() && stack.peek() == '{')
        stack.pop();
      else 
        return "NO";
    }
    if (stack.empty()) {
      return "YES";
    } else {
      return "NO";
    }
  }

  public static String[] validStrings(String[] strings) {
    String[] res = new String[strings.length];
    for (int i = 0; i < strings.length; i++) {
      res[i] = isValid(strings[i]);
    }
    return res;
  }
  
  public static void main(String[] args) {
    String[] list = {"[][]", "{}{}}"};
    String[] resultList = validStrings(list);
    System.out.println(Arrays.toString(resultList)); // [YES, NO]
  }
}


// METHOD 3:
public class Solution {
  public static boolean isValid(String s) {
    Stack<Character> stack = new Stack<Character>();
    System.out.println(Math.abs('(')); // 40
    System.out.println(Math.abs(')')); // 41
    System.out.println(Math.abs('[')); // 91
    System.out.println(Math.abs(']')); // 93
    System.out.println(Math.abs('{')); // 123
    System.out.println(Math.abs('}')); // 125
    
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c == '{') {
        stack.push(c);
      } else if (stack.empty() || Math.abs(c - stack.pop()) > 2) { // parentheses difference is never greater than 2
        return false;
      }
    }
    
    return stack.empty(); // or stack.isEmpty() .. both return same!! https://stackoverflow.com/questions/24986945/empty-vs-isempty-in-java-stack-class
  }
  
  public static void main(String[] args) {
    System.out.println(isValid("([)]")); // false
    System.out.println(isValid("([]){}")); // true
  }
}


// METHOD 4:
public class Solution {
  public static boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    HashMap<Character,Character> map = new HashMap<>();
    map.put('(', ')');
    map.put('[', ']');
    map.put('{', '}');
    for (int i = 0; i < s.length(); i++) {
      if (stack.isEmpty())
        stack.push(s.charAt(i));
      else if (map.containsKey(stack.peek()) && map.get(stack.peek()) == s.charAt(i))
        stack.pop();
      else
        stack.push(s.charAt(i));
    }
    return stack.isEmpty();
  }
  
  public static void main(String[] args) {
    System.out.println(isValid("([)]")); // false
    System.out.println(isValid("([]){}")); // true
  }
}
