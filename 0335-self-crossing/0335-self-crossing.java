class Solution {
    public boolean isSelfCrossing(int[] distance) {
        for (int i = 3; i < distance.length; i++) {

            // Case 1
            if (distance[i] >= distance[i - 2] &&
                distance[i - 1] <= distance[i - 3]) {
                return true;
            }

            // Case 2
            if (i >= 4 &&
                distance[i - 1] == distance[i - 3] &&
                distance[i] >= distance[i - 2] - distance[i - 4]) {
                return true;
            }

            // Case 3
            if (i >= 5 &&
                distance[i - 2] >= distance[i - 4] &&
                distance[i - 1] <= distance[i - 3] &&
                distance[i] >= distance[i - 2] - distance[i - 4] &&
                distance[i - 1] >= distance[i - 3] - distance[i - 5]) {
                return true;
            }
        }

        return false;
    }
}