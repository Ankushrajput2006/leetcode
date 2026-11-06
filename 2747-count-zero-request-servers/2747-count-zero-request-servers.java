import java.util.*;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> a[1] - b[1]);

        int m = queries.length;
        Integer[] idx = new Integer[m];
        for (int i = 0; i < m; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> queries[a] - queries[b]);

        int[] cnt = new int[n + 1];
        int active = 0;
        int add = 0, remove = 0;
        int[] ans = new int[m];

        for (int id : idx) {
            int q = queries[id];

            // add logs with time <= q
            while (add < logs.length && logs[add][1] <= q) {
                if (cnt[logs[add][0]]++ == 0) active++;
                add++;
            }

            // remove logs with time < q - x
            while (remove < logs.length && logs[remove][1] < q - x) {
                if (--cnt[logs[remove][0]] == 0) active--;
                remove++;
            }

            ans[id] = n - active;
        }
        return ans;
    }
}