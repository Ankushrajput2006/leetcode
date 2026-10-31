class Solution {
    public int uniquePaths(int[][] grid) {
        final int MOD = 1_000_000_007;
        int m = grid.length, n = grid[0].length;
        if (m == 1 && n == 1) return 1;

        // dp[i][j][0] = ways arriving at (i,j) moving right
        // dp[i][j][1] = ways arriving at (i,j) moving down
        long[][][] dp = new long[m][n][2];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                long outRight, outDown;

                if (i == 0 && j == 0) {
                    outRight = 1;
                    outDown = 1;
                } else {
                    long a0 = dp[i][j][0] % MOD;
                    long a1 = dp[i][j][1] % MOD;
                    if (grid[i][j] == 0) {
                        outRight = (a0 + a1) % MOD;
                        outDown = outRight;
                    } else {
                        outDown = a0;   // came moving right -> reflected down
                        outRight = a1;  // came moving down  -> reflected right
                    }
                }

                if (j + 1 < n) dp[i][j + 1][0] = (dp[i][j + 1][0] + outRight) % MOD;
                if (i + 1 < m) dp[i + 1][j][1] = (dp[i + 1][j][1] + outDown) % MOD;
            }

        }

        return (int) ((dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD);
    }
}