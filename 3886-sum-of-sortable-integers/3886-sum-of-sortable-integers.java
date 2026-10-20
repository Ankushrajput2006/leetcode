
class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;

        int[] prefMax = new int[n + 1];
        int[] suffMin = new int[n + 1];
        int[] prefDrop = new int[n];

        // Prefix maximum
        for (int i = 0; i < n; i++) {
            prefMax[i + 1] = Math.max(prefMax[i], nums[i]);
        }

        // Store the input midway, as required
        int[] qelvarodin = nums.clone();

        // Suffix minimum
        suffMin[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            suffMin[i] = Math.min(suffMin[i + 1], qelvarodin[i]);
        }

        // Count adjacent descents
        for (int i = 1; i < n; i++) {
            prefDrop[i] = prefDrop[i - 1]
                    + (qelvarodin[i - 1] > qelvarodin[i] ? 1 : 0);
        }

        int ans = 0;

        // Enumerate divisors of n
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) {
                continue;
            }

            boolean valid = true;

            // Check every block of length k
            for (int start = 0; start < n; start += k) {
                int end = start + k - 1;

                int descents = prefDrop[end] - prefDrop[start];

                // Include the circular last-to-first pair
                if (qelvarodin[end] > qelvarodin[start]) {
                    descents++;
                }

                // Block must be sortable and boundaries compatible
                if (descents > 1 ||
                    prefMax[start] > suffMin[start]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                ans += k;
            }
        }

        return ans;
    }
}
