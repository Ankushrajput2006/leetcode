class Solution {
    public String smallestPalindrome(String s) {
        int[] cnt = new int[26];
        char[] arr = s.toCharArray();

        for (char c : arr) cnt[c - 'a']++;

        int halfLen = s.length() / 2;
        char[] ans = new char[s.length()];
        int l = 0, r = s.length() - 1;

        for (int i = 0; i < 26; i++) {
            while (cnt[i] >= 2) {
                char ch = (char) ('a' + i);
                ans[l++] = ch;
                ans[r--] = ch;
                cnt[i] -= 2;
            }
            if (cnt[i] == 1)
                ans[halfLen] = (char) ('a' + i);
        }

        return new String(ans);
    }
}