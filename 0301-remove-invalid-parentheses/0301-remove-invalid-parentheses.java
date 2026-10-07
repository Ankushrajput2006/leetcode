import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s, 0, 0, new char[]{'(', ')'}, result);

        return result;
    }

    private void backtrack(String s, int start, int lastRemove,
                            char[] par, List<String> result) {

        int balance = 0;

        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) {
                balance++;
            } else if (s.charAt(i) == par[1]) {
                balance--;
            }

            if (balance >= 0) {
                continue;
            }

            // Extra closing parenthesis found
            for (int j = lastRemove; j <= i; j++) {
                if (s.charAt(j) == par[1] &&
                    (j == lastRemove || s.charAt(j - 1) != par[1])) {

                    backtrack(
                        s.substring(0, j) + s.substring(j + 1),
                        i,
                        j,
                        par,
                        result
                    );
                }
            }

            return;
        }

        // No invalid ')' remaining.
        // Now check the opposite direction for extra '('.
        String reversed = new StringBuilder(s).reverse().toString();

        if (par[0] == '(') {
            backtrack(reversed, 0, 0,
                      new char[]{')', '('}, result);
        } else {
            result.add(reversed);
        }
    }
}