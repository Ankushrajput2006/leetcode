class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        // prefix sum of all stones
        int[] prefix = new int[n];
        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // dp represents the best score difference
        // for the current suffix.
        int dp = prefix[n - 1];

        // We can choose i from 1 to n - 2.
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, prefix[i] - dp);
        }

        return dp;
    }
}