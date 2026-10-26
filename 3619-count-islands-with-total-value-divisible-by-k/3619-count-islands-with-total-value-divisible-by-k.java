import java.util.*;

class Solution {
    public int countIslands(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int count = 0;
        int[] dirs = {0, 1, 0, -1, 0};
        int[] queue = new int[m * n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;

                long sum = 0;
                int head = 0, tail = 0;
                queue[tail++] = i * n + j;
                sum += grid[i][j];
                grid[i][j] = 0; // mark visited

                while (head < tail) {
                    int cur = queue[head++];
                    int r = cur / n, c = cur % n;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dirs[d], nc = c + dirs[d + 1];
                        if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] != 0) {
                            sum += grid[nr][nc];
                            grid[nr][nc] = 0;
                            queue[tail++] = nr * n + nc;
                        }
                    }
                }


                if (sum % k == 0) count++;
            }
        }
        return count;
    }
}