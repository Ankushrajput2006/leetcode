class Solution {
    int m, n;
    char[][] grid;
    Boolean[][][] dp;

    public boolean hasValidPath(char[][] grid) {
        this.grid = grid;
        m = grid.length;
        n = grid[0].length;

        // Path length must be even
        if ((m + n - 1) % 2 != 0) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        dp = new Boolean[m][n][m + n];

        return dfs(0, 0, 0);
    }

    private boolean dfs(int r, int c, int balance) {
        // Add current character
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }

        // Invalid if balance becomes negative
        if (balance < 0) {
            return false;
        }

        // At destination, balance must be zero
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }

        // Too much balance to close with remaining cells
        int remaining = (m - 1 - r) + (n - 1 - c);

        if (balance > remaining) {
            return false;
        }

        if (dp[r][c][balance] != null) {
            return dp[r][c][balance];
        }

        boolean ans = false;

        // Move down
        if (r + 1 < m) {
            ans = dfs(r + 1, c, balance);
        }

        // Move right
        if (!ans && c + 1 < n) {
            ans = dfs(r, c + 1, balance);
        }

        return dp[r][c][balance] = ans;
    }
}