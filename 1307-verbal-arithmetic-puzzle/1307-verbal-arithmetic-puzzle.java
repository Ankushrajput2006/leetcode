class Solution {

    String[] words;
    String result;

    int[] value = new int[26];
    boolean[] used = new boolean[10];
    boolean[] leading = new boolean[26];

    public boolean isSolvable(String[] words, String result) {

        this.words = words;
        this.result = result;

        // Result cannot have fewer digits than the longest word
        for (String word : words) {
            if (word.length() > result.length()) {
                return false;
            }
        }

        for (int i = 0; i < 26; i++) {
            value[i] = -1;
        }

        // Leading letters cannot be zero
        for (String word : words) {
            if (word.length() > 1) {
                leading[word.charAt(0) - 'A'] = true;
            }
        }

        if (result.length() > 1) {
            leading[result.charAt(0) - 'A'] = true;
        }

        return dfs(0, 0, 0);
    }

    private boolean dfs(int col, int row, int sum) {

        // All result columns processed
        if (col == result.length()) {
            return sum == 0;
        }

        // Process words for current column
        if (row < words.length) {

            String word = words[row];

            // This word has no digit in this column
            if (col >= word.length()) {
                return dfs(col, row + 1, sum);
            }

            int idx = word.charAt(word.length() - 1 - col) - 'A';

            // Letter already assigned
            if (value[idx] != -1) {
                return dfs(col, row + 1, sum + value[idx]);
            }

            // Try every unused digit
            for (int digit = 0; digit <= 9; digit++) {

                if (used[digit]) {
                    continue;
                }

                if (digit == 0 && leading[idx]) {
                    continue;
                }

                value[idx] = digit;
                used[digit] = true;

                if (dfs(col, row + 1, sum + digit)) {
                    return true;
                }

                value[idx] = -1;
                used[digit] = false;
            }

            return false;
        }

        // Now process result digit
        int idx = result.charAt(result.length() - 1 - col) - 'A';

        int digit = sum % 10;
        int carry = sum / 10;

        // Result letter already assigned
        if (value[idx] != -1) {

            if (value[idx] != digit) {
                return false;
            }

            return dfs(col + 1, 0, carry);
        }

        // Assign result letter
        if (used[digit]) {
            return false;
        }

        if (digit == 0 && leading[idx]) {
            return false;
        }

        value[idx] = digit;
        used[digit] = true;

        if (dfs(col + 1, 0, carry)) {
            return true;
        }

        value[idx] = -1;
        used[digit] = false;

        return false;
    }
}