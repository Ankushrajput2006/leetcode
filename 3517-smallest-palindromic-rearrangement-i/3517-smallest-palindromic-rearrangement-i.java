class Solution {
    public String smallestPalindrome(String s) {
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder left = new StringBuilder();
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if ((freq[i] & 1) == 1) {
                mid = (char) ('a' + i);
            }
            while (freq[i] > 1) {
                left.append((char) ('a' + i));
                freq[i] -= 2;
            }
        }

        StringBuilder ans = new StringBuilder(left.length() * 2 + (mid == 0 ? 0 : 1));
        ans.append(left);
        if (mid != 0) ans.append(mid);

        for (int i = left.length() - 1; i >= 0; i--) {
            ans.append(left.charAt(i));
        }

        return ans.toString();
    }
}