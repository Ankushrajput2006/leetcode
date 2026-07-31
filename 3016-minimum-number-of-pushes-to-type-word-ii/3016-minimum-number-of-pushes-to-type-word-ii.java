import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];

        // Count frequency
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }

        // Sort frequencies
        Arrays.sort(freq);

        int ans = 0;

        // Traverse from highest frequency
        for (int i = 0; i < 26; i++) {
            ans += (i / 8 + 1) * freq[25 - i];
        }

        return ans;
    }
}