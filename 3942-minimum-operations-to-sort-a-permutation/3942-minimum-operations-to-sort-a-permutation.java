class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        if (n == 2) return nums[0] == 0 ? 0 : 1;

        boolean inc = true, dec = true;
        int z = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) z = i;
            int next = nums[(i + 1) % n];
            if (next != (nums[i] + 1) % n) inc = false;
            if (next != (nums[i] - 1 + n) % n) dec = false;
        }

        if (inc) {
            // rotate left z, or (reverse, rotate, reverse) which is a right rotation
            int a = z;
            int b = (n - z) % n + 2;
            return Math.min(a, b);
        }
        if (dec) {
            // rotate then reverse, or reverse then rotate
            int a = (z + 1) % n + 1;
            int b = (n - 1 - z) + 1;
            return Math.min(a, b);
        }
        return -1;
    }
}