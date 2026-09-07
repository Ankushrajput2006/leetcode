class Solution {
    public int distinctSubseqII(String s) {
        final int MOD = 1_000_000_007;

        long[] dp = new long[s.length() + 1];
        int[] last = new int[26];

        dp[0] = 1;

        for (int i = 0; i < 26; i++) {
            last[i] = -1;
        }

        for (int i = 1; i <= s.length(); i++) {
            int ch = s.charAt(i - 1) - 'a';

            dp[i] = (2 * dp[i - 1]) % MOD;

            if (last[ch] != -1) {
                dp[i] = (dp[i] - dp[last[ch] - 1] + MOD) % MOD;
            }

            last[ch] = i;
        }

        return (int) ((dp[s.length()] - 1 + MOD) % MOD);
    }
}