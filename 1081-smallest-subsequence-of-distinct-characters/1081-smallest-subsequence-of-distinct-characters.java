class Solution {
    public String smallestSubsequence(String s) {
        int[] count = new int[26];

        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }

        boolean[] visited = new boolean[26];
        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {
            count[ch - 'a']--;

            if (visited[ch - 'a']) {
                continue;
            }

            while (stack.length() > 0 &&
                   stack.charAt(stack.length() - 1) > ch &&
                   count[stack.charAt(stack.length() - 1) - 'a'] > 0) {

                visited[stack.charAt(stack.length() - 1) - 'a'] = false;
                stack.deleteCharAt(stack.length() - 1);
            }

            stack.append(ch);
            visited[ch - 'a'] = true;
        }

        return stack.toString();
    }
}