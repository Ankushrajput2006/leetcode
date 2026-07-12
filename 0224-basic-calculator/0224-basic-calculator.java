import java.util.*;

class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();

        int result = 0;
        int number = 0;
        int sign = 1;

        for (char c : s.toCharArray()) {

            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
            }
            else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            }
            else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            }
            else if (c == '(') {
                // Save current result and sign
                stack.push(result);
                stack.push(sign);

                // Reset for new expression
                result = 0;
                sign = 1;
            }
            else if (c == ')') {
                // Complete current expression
                result += sign * number;
                number = 0;

                // Apply previous sign
                result *= stack.pop();

                // Add previous result
                result += stack.pop();
            }
        }

        // Add the last number
        result += sign * number;

        return result;
    }
}