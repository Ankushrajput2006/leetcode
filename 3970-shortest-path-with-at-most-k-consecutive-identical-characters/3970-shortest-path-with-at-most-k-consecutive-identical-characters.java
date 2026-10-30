import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // Build adjacency list
        List<int[]>[] g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] e : edges) g[e[0]].add(new int[]{e[1], e[2]});

        char[] c = labels.toCharArray();
        int W = k + 1;

        // dist[node * W + run]
        long[] dist = new long[n * W];
        Arrays.fill(dist, Long.MAX_VALUE);

        // heap entries: {distance, node, run}
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[0], b[0]));
        dist[0 * W + 1] = 0;
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            int run = (int) cur[2];

            if (d > dist[u * W + run]) continue; // stale entry
            if (u == n - 1) return (int) d;      // first pop of target is optimal

            for (int[] nx : g[u]) {
                int v = nx[0];

                int nr = (c[v] == c[u]) ? run + 1 : 1;
                if (nr > k) continue;

                long nd = d + nx[1];
                int idx = v * W + nr;
                if (nd < dist[idx]) {
                    dist[idx] = nd;
                    pq.offer(new long[]{nd, v, nr});
                }
            }
        }
        return -1;
    }
}