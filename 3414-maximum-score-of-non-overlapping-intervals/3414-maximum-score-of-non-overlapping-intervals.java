import java.util.*;

class Solution {

    static class State {
        long score;
        List<Integer> indices;

        State(long score, List<Integer> indices) {
            this.score = score;
            this.indices = indices;
        }
    }

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] arr = new int[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // left
            arr[i][1] = intervals.get(i).get(1); // right
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        // Sort by starting position
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        /*
         * dp[i][k] = best answer using intervals from i onward,
         * choosing at most k intervals.
         */
        State[][] dp = new State[n + 1][5];

        // Choosing 0 intervals gives score 0 and empty list
        for (int i = 0; i <= n; i++) {
            dp[i][0] = new State(0, new ArrayList<>());
        }

        // At i == n, no intervals remain
        for (int k = 1; k <= 4; k++) {
            dp[n][k] = new State(0, new ArrayList<>());
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {

                // Option 1: don't take this interval
                State skip = dp[i + 1][k];

                // Option 2: take this interval
                int next = findNext(arr, i);

                State nextState = dp[next][k - 1];

                List<Integer> takeIndices =
                        new ArrayList<>(nextState.indices);

                takeIndices.add(arr[i][3]);

                Collections.sort(takeIndices);

                State take = new State(
                        arr[i][2] + nextState.score,
                        takeIndices
                );

                dp[i][k] = better(take, skip);
            }
        }

        List<Integer> answer = dp[0][4].indices;

        int[] result = new int[answer.size()];

        for (int i = 0; i < answer.size(); i++) {
            result[i] = answer.get(i);
        }

        return result;
    }

    // First interval whose start > current interval's end
    private int findNext(int[][] arr, int i) {
        int target = arr[i][1];

        int left = i + 1;
        int right = arr.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (arr[mid][0] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    // Higher score wins.
    // If scores are equal, lexicographically smaller indices win.
    private State better(State a, State b) {

        if (a.score != b.score) {
            return a.score > b.score ? a : b;
        }

        return compare(a.indices, b.indices) <= 0 ? a : b;
    }

    private int compare(List<Integer> a, List<Integer> b) {

        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {
            if (!a.get(i).equals(b.get(i))) {
                return Integer.compare(a.get(i), b.get(i));
            }
        }

        return Integer.compare(a.size(), b.size());
    }
}