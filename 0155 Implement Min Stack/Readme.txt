From Leetcode:

Complexity Analysis
Let n be the total number of operations performed.

Time Complexity : O(1) for all operations.

push(...): Checking the top of a Stack, comparing numbers, and pushing to the top of a Stack (or adding to the end of an Array or List) are all O(1) operations. 
Therefore, this overall is an O(1) operation.

pop(...): Popping from a Stack (or removing from the end of an Array, or List) is an O(1) operation.

top(...): Looking at the top of a Stack is an O(1) operation.

getMin(...): Same as above. This operation is O(1) because we do not need to compare values to find it. If we had not kept track of it on the Stack, 
and instead had to search for it each time, the overall time complexity would have been O(n).

Space Complexity : O(n).

Worst case is that all the operations are push. In this case, there will be O(2⋅n)=O(n) space used.


Approach 3: Improved Two Stacks
Intuition
In the 2 stack approach, we pushed a new number onto the min-tracker Stack if, and only if, it was less than or equal to the current minimum.

One downside of this solution is that if the same number is pushed repeatedly onto MinStack, and that number also happens to be the current minimum, 
there will be a lot of needless repetition on the min-tracker Stack.

Repetition that can occur on the min-tracker Stack.
stack ->     2, 3, 1, -3, -3, 8, -3, -3, 7 -3
minStack ->  2, 1, -3, -3, -3, -3, -3

An improvement is to put pairs onto the min-tracker Stack. The first value of the pair would be the same as before, 
and the second value would be how many times that minimum was repeated. For example, this is how the min-tracker Stack for the example just above would appear.

Min-tracker Stack with counts.
stack ->     2, 3, 1, -3, -3, 8, -3, -3, 7 -3
minStack ->  [2, 1] [1, 1] [-3, 5]

The push(...) and pop(...) operations of MinStack need to be slightly modified to work with the new representation.



class MinStack {
  private Stack<Integer> stack = new Stack<>();
  private Stack<int[]> minStack = new Stack<>();

  public MinStack() { }

  public void push(int x) {
    // We always put the number onto the main stack.
    stack.push(x);

    // If the min stack is empty, or this number is smaller than
    // the top of the min stack, put it on with a count of 1.
    if (minStack.isEmpty() || x < minStack.peek()[0]) {
        minStack.push(new int[]{x, 1});
    }

    // Else if this number is equal to what's currently at the top
    // of the min stack, then increment the count at the top by 1.
    else if (x == minStack.peek()[0]) {
      minStack.peek()[1]++;
    }
  }
  
  public void pop() {
    // If the top of min stack is the same as the top of stack
    // then we need to decrement the count at the top by 1.
    if (stack.peek().equals(minStack.peek()[0])) {
      minStack.peek()[1]--;
    }

    // If the count at the top of min stack is now 0, then remove
    // that value as we're done with it.
    if (minStack.peek()[1] == 0) {
      minStack.pop();
    }

    // And like before, pop the top of the main stack.
    stack.pop();
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minStack.peek()[0];
  }
}