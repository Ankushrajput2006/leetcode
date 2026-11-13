class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) pre[i + 1] = pre[i] + nums[i];

        long[] vals = new long[n + 2];
        long[] cnts = new long[n + 2];
        int head = n + 1, tail = n + 1; // deque occupies [head, tail)

        long sumPrefMax = 0;
        long ans = 0;
        int r = n - 1;

        for (int l = n - 1; l >= 0; l--) {
            long cnt = 1;
            // merge segments dominated by nums[l]
            while (head < tail && vals[head] <= nums[l]) {
                sumPrefMax -= vals[head] * cnts[head];
                cnt += cnts[head];
                head++;
            }
            head--;
            vals[head] = nums[l];
            cnts[head] = cnt;
            sumPrefMax += (long) nums[l] * cnt;

            // shrink r until cost <= k
            while (sumPrefMax - (pre[r + 1] - pre[l]) > k) {
                int back = tail - 1;
                sumPrefMax -= vals[back];
                if (--cnts[back] == 0) tail--;
                r--;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}