import java.util.*;

class Solution {
    long[] sumBit;
    int[] cntBit;
    int m;

    void add(int i, int c, long v) {
        for (; i <= m; i += i & -i) {
            cntBit[i] += c;
            sumBit[i] += c * v;
        }
    }

    // smallest index whose prefix count >= k
    int kth(int k) {
        int pos = 0, LOG = Integer.highestOneBit(m);
        for (int step = LOG; step > 0; step >>= 1) {
            if (pos + step <= m && cntBit[pos + step] < k) {
                pos += step;
                k -= cntBit[pos];
            }
        }
        return pos + 1;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;

        // coordinate compression
        int[] vals = Arrays.stream(nums).distinct().sorted().toArray();
        m = vals.length;
        int[] rank = new int[n];
        for (int i = 0; i < n; i++) {
            rank[i] = Arrays.binarySearch(vals, nums[i]) + 1;
        }
        cntBit = new int[m + 1];
        sumBit = new long[m + 1];

        // cost[i] = min ops to make window nums[i..i+x-1] all equal
        long[] cost = new long[n - x + 1];
        long total = 0;
        for (int i = 0; i < n; i++) {
            add(rank[i], 1, nums[i]);
            total += nums[i];
            if (i >= x) {
                add(rank[i - x], -1, nums[i - x]);
                total -= nums[i - x];
            }
            if (i >= x - 1) {
                int r = kth((x + 1) / 2);
                long med = vals[r - 1];
                int leftCnt = 0;
                long leftSum = 0;
                for (int p = r; p > 0; p -= p & -p) {
                    leftCnt += cntBit[p];
                    leftSum += sumBit[p];
                }
                cost[i - x + 1] = med * leftCnt - leftSum
                        + (total - leftSum) - med * (x - leftCnt);
            }
        }

        // dp[j][i]: min cost using first i elements with j chosen windows
        long INF = Long.MAX_VALUE / 4;
        long[] prev = new long[n + 1]; // j = 0
        for (int j = 1; j <= k; j++) {
            long[] cur = new long[n + 1];
            Arrays.fill(cur, INF);
            for (int i = 1; i <= n; i++) {
                cur[i] = cur[i - 1];
                if (i >= x && prev[i - x] < INF) {
                    cur[i] = Math.min(cur[i], prev[i - x] + cost[i - x]);
                }
            }
            prev = cur;
        }
        return prev[n];
    }
}