class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        HashMap<Integer, Integer> prefixSumMap = new HashMap<>();
        int sum = 0;
        prefixSumMap.put(0, 1); // Initialize with sum 0 at index -1

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (prefixSumMap.containsKey(sum - k)) {
               count += prefixSumMap.get(sum - k);
            }
             prefixSumMap.put(sum, prefixSumMap.getOrDefault(sum, 0) + 1);
        }
         return count;  
    }
}