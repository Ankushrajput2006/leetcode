class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        // Base cases for small arrays
        if (n == 1) return 1;
        if (n == 2) return 2;
        
        // Find the smallest power of 2 strictly greater than n
        // Integer.highestOneBit(n) gives the most significant bit of n
        int highestBit = Integer.highestOneBit(n);
        
        return highestBit << 1;
    }
}