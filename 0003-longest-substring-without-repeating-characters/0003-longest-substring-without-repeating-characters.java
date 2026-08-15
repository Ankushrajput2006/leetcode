class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[128];
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            left = Math.max(left, lastSeen[c]);

            maxLen = Math.max(maxLen, right - left + 1);

            lastSeen[c] = right + 1;
        }

        return maxLen;
    }
}