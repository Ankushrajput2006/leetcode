class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        long[] freq = new long[100001];
        long total = (long) k1 + k2;
        long sum = 0;

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            freq[diff]++;
            sum += diff;
        }

        if (sum <= total) {
            return 0;
        }

        for (int i = 100000; i > 0 && total > 0; i--) {
            if (freq[i] == 0) {
                continue;
            }

            long move = Math.min(total, freq[i]);
            freq[i] -= move;
            freq[i - 1] += move;
            total -= move;
        }

        long result = 0;

        for (int i = 1; i <= 100000; i++) {
            result += freq[i] * i * i;
        }

        return result;
    }
}