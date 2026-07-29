class Solution {
    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) cnt[c - 'a']++;

        char mid = 0;
        int[] half = new int[26];
        int halfLen = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                mid = (char) ('a' + i);
            }
            half[i] = cnt[i] / 2;
            halfLen += half[i];
        }

        if (countWays(half) < k) return "";

        StringBuilder left = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {
            for (int c = 0; c < 26; c++) {
                if (half[c] == 0) continue;

                half[c]--;
                long ways = countWays(half);

                if (ways >= k) {
                    left.append((char) ('a' + c));
                    break;
                } else {
                    k -= ways;
                    half[c]++;
                }
            }
        }

        StringBuilder ans = new StringBuilder(left);
        if (mid != 0) ans.append(mid);
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countWays(int[] half) {
        int total = 0;
        for (int x : half) total += x;

        long res = 1;

        for (int c : half) {
            if (c == 0) continue;
            res = Math.min(LIMIT, res * combLimited(total, c));
            total -= c;
            if (res >= LIMIT) return LIMIT;
        }

        return res;
    }

    private long combLimited(int n, int r) {
        r = Math.min(r, n - r);
        long res = 1;

        for (int i = 1; i <= r; i++) {
            res = res * (n - r + i) / i;
            if (res >= LIMIT) return LIMIT;
        }

        return res;
    }
}