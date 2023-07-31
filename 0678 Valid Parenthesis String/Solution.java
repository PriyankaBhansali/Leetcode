// https://leetcode.com/problems/valid-parenthesis-string/

/* METHOD 1:
Check from left to right and then check from right to left.
When check from left to right, take all '*'s as '(', to see whether can match all ')'s.
And, When check from right to left, take all '*'s as ')', to see whether can match all '('s.
If both checks are valid, then the string is valid.
We can return true if the first check returns bal=0.

Logic: This solution works because
In the first pass, we're trying to ensure that for every ')' there is a '*' or '(' to balance it.
If at any point there is no '(' or '*' to balance the ')' the string is clearly not balanced.

In the second pass, we're trying to ensure that for every '(' there is a '*' or ')' to balance it. 
If at any point there is no ')' or '*' to balance the '(' the string is clearly not balanced.

Now we may think that with this algorithm, some '(' and ')' may need the same '*' to balance it, and since a '*' cannot be a '(' and ')' at the same time the algorithm would not work. But it's easy to prove that this cannot be.
Because then we'll have something like (X*Y)
X and Y are balanced strings. We can convert the '*' to empty (instead of sharing it) so that the '(' and ')' can match themselves.
*/
class Solution {
  public boolean checkValidString(String s) {
    int bal = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(' || s.charAt(i) == '*') {
        bal++;
      } else if (bal-- == 0) {
        return false;
      }
    }
    if (bal == 0) {
      return true;
    }
    
    bal = 0;
    for (int i = s.length() - 1; i >= 0; i--) { // at this point, start from the end of the string
      if (s.charAt(i) == ')' || s.charAt(i) == '*') {
        bal++;
      } else if (bal-- == 0) {
        return false;
      }
    }

    return true;
  }
}

// METHOD 2:
class Solution {
  public static boolean checkValidString(String s) {
    int low = 0;
    int high = 0;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(') {
        low++;
        high++;
      } else if (s.charAt(i) == ')') {
        if (low > 0) {
            low--;
        }
        high--;
      } else {
        if (low > 0) {
            low--;
        }
        high++;
      }
      if (high < 0) {
        return false;
      }
      System.out.println("low " + low + " high " + high);
    }
    return low == 0;
  }

  public static void main(String[] args) {
    System.out.println(checkValidString("((**(((()*))*))")); // true
    System.out.println(checkValidString("(()((*(*)")); // false
  }
}

/* OUTPUT:
low 1 high 1
low 2 high 2
low 1 high 3
low 0 high 4
low 1 high 5
low 2 high 6
low 3 high 7
low 4 high 8
low 3 high 7
low 2 high 8
low 1 high 7
low 0 high 6
low 0 high 7
low 0 high 6
low 0 high 5
true
low 1 high 1
low 2 high 2
low 1 high 1
low 2 high 2
low 3 high 3
low 2 high 4
low 3 high 5
low 2 high 6
low 1 high 5
false
*/

/* METHOD 3: Using 2 stacks
The basic idea is to track the index of the left bracket and star position.
Push all the indices of the star and left bracket to their stack respectively.
STEP 1
Once a right bracket comes, pop left bracket stack first if it is not empty. 
If the left bracket stack is empty, pop the star stack if it is not empty. 
A false return can be made provided that both stacks are empty.

STEP 2
Now attention is paid to the remaining stuff in these two stacks. 
Note that the left bracket CANNOT appear after the star as there is NO way to balance the bracket. 
In other words, whenever there is a left bracket index appears after the Last star, 
a false statement can be made. Otherwise, pop out each from the left bracket and star stack.

STEP 3
A correct sequence should have an empty left bracket stack. You don't need to take care of the star stack.

Final Remarks:
Greedy algorithm is used here. We always want to use left brackets to balance the right one first as the * symbol is a wild card.
*/

class Solution {
  public static boolean checkValidString(String s) {
    Stack<Integer> leftID = new Stack<>();
    Stack<Integer> starID = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      if (ch == '(')
        leftID.push(i);
      else if (ch == '*')
        starID.push(i);
      else {
        if (leftID.isEmpty() && starID.isEmpty()) {
          return false;
        }
        if (!leftID.isEmpty())
          leftID.pop();
        else 
          starID.pop();
      }
    }
    while (!leftID.isEmpty() && !starID.isEmpty()) {
      int left = leftID.pop();
      int star = starID.pop();
      System.out.println(left + "  " + star);
      if (left > star) 
        return false;
    }
    return leftID.isEmpty();
  }

  public static void main(String[] args) {
    System.out.println(checkValidString("((**(((()*))*))"));
    System.out.println(checkValidString("(()((*(*)")); 
  }
}

/* OUTPUT:
0  12
true
4  7
3  5
false
*/