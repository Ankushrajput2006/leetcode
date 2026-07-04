class Solution {
    private int[] parent;
    private int[] rank_;

    public int minScore(int n, int[][] roads) {
        parent = new int[n + 1];
        rank_ = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        for (int[] road : roads) {
            union(road[0], road[1]);
        }

        int root1 = find(1);
        int minDist = Integer.MAX_VALUE;

        for (int[] road : roads) {
            if (find(road[0]) == root1) {
                minDist = Math.min(minDist, road[2]);
            }
        }

        return minDist;
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // path compression
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;

        // union by rank
        if (rank_[rootX] < rank_[rootY]) {
            parent[rootX] = rootY;
        } else if (rank_[rootX] > rank_[rootY]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank_[rootX]++;
        }
    }
}