class Solution {
    // digit -> exponents of {2,3,5,7} it contributes
    private static final int[][] DIGIT_FACTORS = {
        {0,0,0,0}, {0,0,0,0}, {1,0,0,0}, {0,1,0,0}, {2,0,0,0}, {0,0,1,0},
        {1,1,0,0}, {0,0,0,1}, {3,0,0,0}, {0,2,0,0}
    };

    public String smallestNumber(String num, long t) {
        int[] need = primeCountOf(t);
        if (need == null) return "-1"; // t has a prime factor other than 2,3,5,7

        int[] minFactors = greedyFactors(need);
        int minLen = sum(minFactors);
        if (minLen > num.length()) return build(minFactors);

        // prefix factor sums
        int n = num.length();
        int[][] prefix = new int[n + 1][4];
        for (int i = 0; i < n; i++) {
            int d = num.charAt(i) - '0';
            for (int k = 0; k < 4; k++) prefix[i + 1][k] = prefix[i][k] + DIGIT_FACTORS[d][k];
        }

        int firstZero = num.indexOf('0');
        if (firstZero == -1) {
            firstZero = n;
            if (covers(prefix[n], need)) return num; // num itself works
        }

        // try replacing digit at position i with a larger digit, fill rest optimally
        for (int i = n - 1; i >= 0; i--) {
            if (i > firstZero) continue;
            int d = num.charAt(i) - '0';
            int spaceAfter = n - 1 - i;
            // remaining need using prefix[i] (factors before position i)
            for (int big = d + 1; big < 10; big++) {
                int[] remain = new int[4];
                for (int k = 0; k < 4; k++) {
                    remain[k] = need[k] - prefix[i][k] - DIGIT_FACTORS[big][k];
                    if (remain[k] < 0) remain[k] = 0;
                }
                int[] factors = greedyFactors(remain);
                int cnt = sum(factors);
                if (cnt <= spaceAfter) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, i);
                    sb.append((char) ('0' + big));
                    for (int f = 0; f < spaceAfter - cnt; f++) sb.append('1');
                    sb.append(build(factors));
                    return sb.toString();
                }
            }
        }

        // need one more digit: prepend '1's, place required digits at the end
        int[] factors = greedyFactors(need);
        int cnt = sum(factors);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n + 1 - cnt; i++) sb.append('1');
        sb.append(build(factors));
        return sb.toString();
    }

    // returns exponent counts [e2,e3,e5,e7] of t, or null if t has other prime factors
    private int[] primeCountOf(long t) {
        int[] primes = {2, 3, 5, 7};
        int[] count = new int[4];
        for (int i = 0; i < 4; i++) {
            while (t % primes[i] == 0) {
                t /= primes[i];
                count[i]++;
            }
        }
        return t == 1 ? count : null;
    }

    // Greedily convert exponent counts of {2,3,5,7} into digit counts (2..9)
    // that minimize total digit count, returned as int[8] for digits 2..9.
    private int[] greedyFactors(int[] need) {
        int e2 = need[0], e3 = need[1], e5 = need[2], e7 = need[3];
        int c8 = e2 / 3, r2 = e2 % 3;
        int c9 = e3 / 2, c3 = e3 % 2;
        int c4 = r2 / 2, c2 = r2 % 2;
        int c6 = 0;
        if (c2 == 1 && c3 == 1) { c2 = 0; c3 = 0; c6 = 1; }
        if (c3 == 1 && c4 == 1) { c2 = 1; c6 = 1; c3 = 0; c4 = 0; }
        // digits 2..9 -> indices 0..7
        return new int[]{c2, c3, c4, e5, c6, e7, c8, c9};
    }

    private int sum(int[] factors) {
        int s = 0;
        for (int f : factors) s += f;
        return s;
    }

    private String build(int[] factors) {
        StringBuilder sb = new StringBuilder();
        for (int d = 2; d <= 9; d++) {
            int cnt = factors[d - 2];
            for (int i = 0; i < cnt; i++) sb.append((char) ('0' + d));
        }
        return sb.toString();
    }

    private boolean covers(int[] have, int[] need) {
        for (int k = 0; k < 4; k++) if (have[k] < need[k]) return false;
        return true;
    }
}