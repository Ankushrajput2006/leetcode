
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        char min = 'z';
        char max = 'a';
        boolean sorted = true;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            min = (char) Math.min(min, c);
            max = (char) Math.max(max, c);

            if (i > 0 && s.charAt(i - 1) > c) {
                sorted = false;
            }
        }

        // Already sorted
        if (sorted) {
            return 0;
        }

        // Cannot sort the entire string in one operation
        if (n == 2) {
            return -1;
        }

        // Minimum is at the beginning or maximum at the end
        if (s.charAt(0) == min || s.charAt(n - 1) == max) {
            return 1;
        }

        // Minimum or maximum appears in the middle
        for (int i = 1; i < n - 1; i++) {
            char c = s.charAt(i);
            if (c == min || c == max) {
                return 2;
            }
        }

        return 3;
    }
}
