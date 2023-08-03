// https://leetcode.com/problems/baseball-game/

// METHOD 1: Using stack
import java.util.*;
class Solution {
  public static int calPoints(String[] operations) {
    Stack<Integer> stack = new Stack<>();
    for (int i = 0; i < operations.length; i++) {
      System.out.println(operations[i]);
      if (operations[i].equals("C")) {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else if (operations[i].equals("D")) {
        if (!stack.isEmpty()) {
          stack.push(2 * stack.peek());
        }
      } else if (operations[i].equals("+")) {
        if (stack.size() >= 2) {
          int value1 = stack.pop();
          int value2 = stack.pop();
          int sum = value1 + value2;
          stack.push(value2);
          stack.push(value1);
          stack.push(sum);
        }
      } else {
        stack.push(Integer.parseInt(operations[i]));
      }
    }
    System.out.println("Stack" + stack);
    int total = 0;
    while (!stack.isEmpty()) {
      total += stack.pop();
    }
    return total;
  }
  
  public static void main(String[] args) {
    String operations[] = {"5","2","C","D","+"};
    System.out.println("total = " + calPoints(operations)); 
  }
}

// METHOD 2: Using stack with switch case
class Solution {
  public int calPoints(String[] operations) {
    Stack<Integer> stack = new Stack<>();
    for (String step : operations) {
      switch (step) {
        case "+": {
          int second = stack.pop();
          int first = stack.peek();
          stack.push(second);
          stack.push(second + first);
          break;
        }
        case "D": {
          stack.push(stack.peek() * 2);
          break;
        }
        case "C": {
          stack.pop();
          break;
        }
        default:
          stack.push(Integer.parseInt(step));
      }
    }
    int sum = 0;
    while (stack.size() > 0) {
      sum += stack.pop();
    }
    return sum;
  }
}