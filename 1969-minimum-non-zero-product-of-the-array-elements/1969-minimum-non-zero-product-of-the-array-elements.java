class Solution {
    private static final long MOD = 1_000_000_007L;

    public int minNonZeroProduct(int p) {
        long max = (1L << p) - 1;        // 2^p - 1
        long secondMax = max - 1;       // 2^p - 2
        long exponent = (1L << (p - 1)) - 1;

        long result = (max % MOD) * modPow(secondMax % MOD, exponent) % MOD;

        return (int) result;
    }

    private long modPow(long base, long exp) {
        long result = 1;

        while (exp > 0) {
            if ((exp & 1) == 1) {
                result = (result * base) % MOD;
            }

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return result;
    }
}