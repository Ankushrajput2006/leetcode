import java.util.HashMap;
class Solution {
    public int singleNumber(int[] nums) {
       HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }
        for (int num : countMap.keySet()) {
            if (countMap.get(num) == 1) {
                return num;
            }
        }
        return -1; // Return -1 if no unique number is found
    }
}