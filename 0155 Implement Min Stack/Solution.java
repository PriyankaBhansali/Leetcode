// https://leetcode.com/problems/min-stack/

import java.io.*;
import java.util.*;

// USING TWO STACKS
class MinStack1 {
  Stack<Integer> stack = new Stack<Integer>();
  Stack<Integer> minStack = new Stack<Integer>();

  // we don't need a constructor here
  // public MinStack() {
  // }
  
  public void push(int val) {
    stack.push(val);
    // when pushing into minStack it is important to check if minStack is empty ORRRR if the value is LESS THAN EQUAL TO it's peek/top value
    if (minStack.isEmpty() || val <= minStack.peek()) {
      minStack.push(val);
    }
  }
  
  public void pop() {
    if (minStack.peek() == stack.pop()) {
      minStack.pop();
    }
  }
  
  public int top() {
    return stack.peek();
  }
  
  public int getMin() {
    return minStack.peek();
  }
}

// USING ONE STACK
class MinStack2 {
  Stack<Integer> stack = new Stack<Integer>();
  int min = Integer.MAX_VALUE;
  
  //you are adding extra elements to stack that you wouldn't want in there if you want to just keep track of all elements.
  // eg; if you print all elements of stack, there would be extra min elements in the stack and not just the stack elements
  public void push(int val) {

    // when you find the minimum, you are going to push the OLD minimum on top of the stack FISRTTTT - BEFORE you change min or push val to stack
    // because when you pop off and it's the min
    // you have to make sure you have the underlying element is the SECOND min as that will be your new min

    if (val <= min) {
      stack.push(min);
      min = val;
    }

    stack.push(val); 
  }
  
  public void pop() {
    if (min == stack.pop()) {
      min = stack.pop();
    }
  }
  
  public int top() {
    return stack.peek();
  }
  
  public int getMin() {
    return min;
  }
}


// MODIFIED VERSION OF USING ONE STACK - ALWAYS PUSH MIN AND VALUE TO STACK AND POP VALUE AND MIN ALL THE TIME -- LOTS OF REPETITIONS HERE THOUGH!!!!!!
// PUSH POP FUNCTIONS WILL LOOK AS BELOW REST EVERYTHING IS SAME AS ONE STACK CASE
  public void push(int val) {
    stack.push(min);
    stack.push(val);
    if (val <= min) {
      min = val;
    }
  }

  public void pop() {
    stack.pop();
    min = stack.pop();
  }


// USE TWO STACKS to avoid repetitions - check read me file

class Solution {
  public static void main(String[] args) {
    MinStack1 obj1 = new MinStack1();
    System.out.println("MinStack 1");
    obj1.push(2);
    obj1.push(3);
    obj1.push(1);
    obj1.push(-2);
    obj1.push(5);
    obj1.push(-2);
    obj1.push(-3);
    System.out.println("Top = " + obj1.top());
    System.out.println("Min = " + obj1.getMin());
    obj1.pop();
    System.out.println("Top = " + obj1.top());
    System.out.println("Min = " + obj1.getMin());
    obj1.pop();
    System.out.println("Top = " + obj1.top());
    System.out.println("Min = " + obj1.getMin());
    obj1.pop();
    System.out.println("Top = " + obj1.top());
    System.out.println("Min = " + obj1.getMin());
    obj1.pop();
    System.out.println("Top = " + obj1.top());
    System.out.println("Min = " + obj1.getMin());
    obj1.pop();
    System.out.println("Top = " + obj1.top());
    System.out.println("Min = " + obj1.getMin());
    obj1.pop();
    System.out.println("Top = " + obj1.top());
    System.out.println("Min = " + obj1.getMin());
    obj1.pop();

    MinStack2 obj2 = new MinStack2();
    System.out.println("MinStack 2");
    obj2.push(2);
    obj2.push(3);
    obj2.push(1);
    obj2.push(-2);
    obj2.push(5);
    obj2.push(-2);
    obj2.push(-3);
    System.out.println("Top = " + obj2.top());
    System.out.println("Min = " + obj2.getMin());
    obj2.pop();
    System.out.println("Top = " + obj2.top());
    System.out.println("Min = " + obj2.getMin());
    obj2.pop();
    System.out.println("Top = " + obj2.top());
    System.out.println("Min = " + obj2.getMin());
    obj2.pop();
    System.out.println("Top = " + obj2.top());
    System.out.println("Min = " + obj2.getMin());
    obj2.pop();
    System.out.println("Top = " + obj2.top());
    System.out.println("Min = " + obj2.getMin());
    obj2.pop();
    System.out.println("Top = " + obj2.top());
    System.out.println("Min = " + obj2.getMin());
    obj2.pop();
    System.out.println("Top = " + obj2.top());
    System.out.println("Min = " + obj2.getMin());
    obj2.pop();
  }
}

// 2, 3, 1, -2, 5, -2, -3

// stack ->     2, 3, 1, -2, 5, -2, -3
// minStack ->  2, 1, -2, -2, -3

// stack ->  2147483647, 2,   3,   2, 1,   1, -2,   5,   -2, -2,   -2, -3 
//             ^ = MAX_VALUE