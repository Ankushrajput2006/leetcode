class Solution {
    public boolean isValid(String s) {
        if (s == null) return false;
        int n = s.length();
        if (n % 2 == 1) return false; // odd length can't be valid

        Stack<Character> st = new Stack<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                st.push(')');
            } else if (c == '{') {
                st.push('}');
            } else if (c == '[') {
                st.push(']');
            } else {
                // c is a closing bracket
                if (st.isEmpty() || st.pop() != c) {
                    return false;
                }
            }
        }

        return st.isEmpty();
    }
}