class Solution {
    public int divide(int dividend, int divisor) {
        // Overflow case
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        // Convert to long to safely handle Integer.MIN_VALUE
        long a = Math.abs((long) dividend);
        long b = Math.abs((long) divisor);

        int result = 0;

        // Try powers of 2 from largest to smallest
        for (int i = 31; i >= 0; i--) {
            if ((a >> i) >= b) {
                a -= (b << i);
                result += (1 << i);
            }
        }

        // Determine sign
        if ((dividend < 0) ^ (divisor < 0)) {
            result = -result;
        }

        return result;
    }
}