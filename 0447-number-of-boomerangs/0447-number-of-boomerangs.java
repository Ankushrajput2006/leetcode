import java.util.*;

class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;

        for (int i = 0; i < points.length; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();

            for (int j = 0; j < points.length; j++) {
                if (i == j) continue;

                int dx = points[i][0] - points[j][0];
                int dy = points[i][1] - points[j][1];

                int distance = dx * dx + dy * dy;

                int count = map.getOrDefault(distance, 0);

                ans += count * 2;

                map.put(distance, count + 1);
            }
        }

        return ans;
    }
}