class Solution {
    public boolean winnerSquareGame(int n) {
        boolean[] dp = new boolean[n + 1];

        // dp[0] = false:
        // If there are 0 stones, the player whose turn it is loses.

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                // If the opponent loses after our move,
                // then we can win.
                if (!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}