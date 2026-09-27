class Solution {
    public String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();

        for (char ch : s.toCharArray()) {
            if (ch == ')') {
                int i = sb.length() - 1;

                while (sb.charAt(i) != '(') {
                    i--;
                }

                StringBuilder temp = new StringBuilder(sb.substring(i + 1));
                temp.reverse();

                sb.delete(i, sb.length());
                sb.append(temp);
            } else {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}