class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();

        // Count characters
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // Check if palindrome is possible
        int odd = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) {
                odd++;
                mid = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        // Build counts for the first half
        int halfLen = n / 2;
        int[] halfCnt = new int[26];

        for (int i = 0; i < 26; i++) {
            halfCnt[i] = cnt[i] / 2;
        }

        /*
         * We need the smallest half such that its palindrome
         * is strictly greater than target.
         */

        // First try to make the first half equal to target's prefix.
        char[] half = new char[halfLen];

        for (int i = 0; i < halfLen; i++) {
            int x = target.charAt(i) - 'a';

            if (halfCnt[x] == 0) {
                break;
            }

            half[i] = target.charAt(i);
            halfCnt[x]--;
        }

        // Restore counts and find answer using backtracking.
        // Since alphabet size is only 26, we can greedily try
        // characters and backtrack when necessary.
        int[] original = new int[26];
        for (int i = 0; i < 26; i++) {
            original[i] = cnt[i] / 2;
        }

        String result = build(original, target, halfLen, mid);

        return result == null ? "" : result;
    }

    private String build(
            int[] cnt,
            String target,
            int len,
            char mid) {

        char[] half = new char[len];

        /*
         * DFS tries characters in increasing order.
         *
         * relation:
         *  - -1 : our half is already smaller than target prefix
         *  -  0 : equal so far
         *  -  1 : already greater
         *
         * Once greater, simply use the smallest remaining chars.
         */
        String ans = dfs(cnt, target, half, 0, 0, mid);

        return ans;
    }

    private String dfs(
            int[] cnt,
            String target,
            char[] half,
            int pos,
            int relation,
            char mid) {

        if (pos == half.length) {
            String palindrome = makePalindrome(half, mid);

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }

            return null;
        }

        int targetChar = target.charAt(pos) - 'a';

        for (int c = 0; c < 26; c++) {
            if (cnt[c] == 0) {
                continue;
            }

            int newRelation = relation;

            if (relation == 0) {
                if (c < targetChar) {
                    newRelation = -1;
                } else if (c > targetChar) {
                    newRelation = 1;
                }
            }

            // If our first half is already smaller than target's
            // first half, this choice cannot recover.
            if (newRelation == -1) {
                continue;
            }

            half[pos] = (char) ('a' + c);
            cnt[c]--;

            String result;

            if (newRelation == 1) {
                // Already greater: fill remaining positions minimally.
                result = finish(cnt, half, pos + 1, mid);

                if (result != null && result.compareTo(target) > 0) {
                    cnt[c]++;
                    return result;
                }
            } else {
                result = dfs(
                        cnt,
                        target,
                        half,
                        pos + 1,
                        newRelation,
                        mid
                );

                if (result != null) {
                    cnt[c]++;
                    return result;
                }
            }

            cnt[c]++;
        }

        return null;
    }

    private String finish(
            int[] cnt,
            char[] half,
            int pos,
            char mid) {

        int p = pos;

        for (int c = 0; c < 26; c++) {
            while (cnt[c] > 0) {
                half[p++] = (char) ('a' + c);
                cnt[c]--;
            }
        }

        String result = makePalindrome(half, mid);

        // Restore counts
        for (int i = pos; i < half.length; i++) {
            cnt[half[i] - 'a']++;
        }

        return result;
    }

    private String makePalindrome(char[] half, char mid) {
        StringBuilder sb = new StringBuilder();

        for (char c : half) {
            sb.append(c);
        }

        if (mid != 0) {
            sb.append(mid);
        }

        for (int i = half.length - 1; i >= 0; i--) {
            sb.append(half[i]);
        }

        return sb.toString();
    }
}