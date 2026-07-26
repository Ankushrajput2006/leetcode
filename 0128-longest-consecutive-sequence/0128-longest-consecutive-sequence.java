class Solution {
    public int longestConsecutive(int[] nums) {
       Arrays.sort(nums);
        int longestStreak = 1;
        int currentStreak = 1;
        if(nums.length==0){return 0; }
        for (int i = 1; i < nums.length; i++) {
            
            if (nums[i] == nums[i - 1]) {
                continue;
            }
            if (nums[i] == nums[i - 1] + 1) {
                currentStreak++;
            } else {
                longestStreak = Math.max(longestStreak, currentStreak);
                currentStreak = 1;
            }
        }
        longestStreak = Math.max(longestStreak, currentStreak);
        return longestStreak;
    }
}