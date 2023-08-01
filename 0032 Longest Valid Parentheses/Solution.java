// https://leetcode.com/problems/longest-valid-parentheses/

// Time Complexity: O(n)
// Space Complexity: O(n) - it takes space to hold an extra value in the stack

/* METHOD 1:
We need to find the longest length here so we need the index's of the parentheses
For every open bracket, push the element's index into the stack and 
For every close bracket, pop the element's index from the stack - we need to push -1 at the beginning
of the stack so that we don't get an error if we encounter the first element as a pop/close bracket
  2 cases on pop:
  We cannot let the stack be empty. So after popping, if the stack is empty then 
  we need to push the current index into the stack. Pushing this index when stack is empty is to keep
  track of where we want to start the count to get the new length

  If the stack is not empty - there must be some length of the string where the parenthesis has matched.
  So we find the length here, which is current index - index at the top (after we did the pop)
*/
class Solution {
  public int longestValidParentheses(String s) {
    Stack<Integer> st = new Stack<>();
    st.push(-1);
    int max = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c = '(') {
        st.push(i);
      } else {
        st.pop();
        if (st.isEmpty()) {
          st.push(i);
        } else {
          int len = i - st.peek();
          max = Math.max(max, len);
        }
      }
    }

    return max;
  }
}

// Time Complexity: O(n)
// Space Complexity: O(1)

/* METHOD 2:
For a valid string: Number of open parenthesis = Number of closed parenthesis
no matter from which direction we find it
*/

class Solution {
  public static int longestValidParentheses(String s) {
    int open = 0, close = 0;
    int max = 0;
    
    // Traverse 0 -- n ==> to take care of the right/close orphan bracket
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(') {
        open++;
      } else {
        close++;
      }
      
      if (open == close) {
        int len = open + close;
        max = Math.max(max, len);
      } else if (close > open) {
        open = close = 0;
      }
    }
    
    open = close = 0;
    // Traverse n -- 0 ==> to take care of the left/open orphan bracket
    for (int i = s.length() - 1; i >= 0; i--) {
      char c = s.charAt(i);
      if (c == '(') {
        open++;
      } else {
        close++;
      }
      
      if (open == close) {
        int len = open + close;
        max = Math.max(max, len);
      } else if (open > close) {
        open = close = 0;
      }
    }
    
    return max;
  }

  public static void main(String[] args) {
    System.out.println(longestValidParentheses("((()"));
    System.out.println(longestValidParentheses(")()())"));
    System.out.println(longestValidParentheses("()(()))))"));    
  }
}

/* Output:
2
4
6
*/