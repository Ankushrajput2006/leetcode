import java.util.HashSet;
import java.util.Set;

class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        int[] f = new int[MAX];

        // Only distinct values matter
        Set<Integer> set = new HashSet<>();
        for (int x : nums) {
            set.add(x);
        }

        for (int x : set) {
            f[x] = 1;
        }

        fwht(f);

        // Cube each coefficient (XOR convolution of 3 arrays)
        for (int i = 0; i < MAX; i++) {
            f[i] = f[i] * f[i] * f[i];
        }

        fwht(f);

        int ans = 0;
        for (int x : f) {
            if (x != 0) ans++;
        }
        return ans;
    }

    private void fwht(int[] a) {
        int n = a.length;
        for (int len = 1; len < n; len <<= 1) {
            for (int i = 0; i < n; i += len << 1) {
                for (int j = 0; j < len; j++) {
                    int u = a[i + j];
                    int v = a[i + j + len];
                    a[i + j] = u + v;
                    a[i + j + len] = u - v;
                }
            }
        }
    }
}