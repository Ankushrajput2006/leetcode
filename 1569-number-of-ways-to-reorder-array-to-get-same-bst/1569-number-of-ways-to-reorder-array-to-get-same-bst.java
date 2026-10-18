class Solution {
    static final int MOD = 1_000_000_007;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        long[][] comb = new long[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            comb[i][0] = comb[i][i] = 1;
            for (int j = 1; j < i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        return (int) ((solve(nums, comb) - 1 + MOD) % MOD);
    }

    private long solve(int[] nums, long[][] comb) {
        int n = nums.length;

        if (n <= 2) {
            return 1;
        }

        java.util.List<Integer> left = new java.util.ArrayList<>();
        java.util.List<Integer> right = new java.util.ArrayList<>();

        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[0]) {
                left.add(nums[i]);
            } else {
                right.add(nums[i]);
            }
        }

        long leftWays = solve(
            left.stream().mapToInt(Integer::intValue).toArray(), comb
        );

        long rightWays = solve(
            right.stream().mapToInt(Integer::intValue).toArray(), comb
        );

        long ways = comb[n - 1][left.size()];
        ways = ways * leftWays % MOD;
        ways = ways * rightWays % MOD;

        return ways;
    }
}