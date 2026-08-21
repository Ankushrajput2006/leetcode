class Solution {

    public long findKthSmallest(int[] coins, int k) {
        long left = 1;
        long right = (long) 1e11;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (count(mid, coins) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private long count(long x, int[] coins) {
        int n = coins.length;
        long count = 0;

        // Inclusion-exclusion over all non-empty subsets
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    bits++;

                    lcm = lcm(lcm, coins[i]);

                    // No multiple of this LCM is <= x
                    if (lcm > x) {
                        break;
                    }
                }
            }

            if (lcm > x) {
                continue;
            }

            long multiples = x / lcm;

            if ((bits & 1) == 1) {
                count += multiples;
            } else {
                count -= multiples;
            }
        }

        return count;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}