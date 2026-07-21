class Solution {
    public int majorityElement(int[] nums) {
        int cnt = 0;
        Integer cand = null;
        for(int j : nums){
           if(cnt==0){
              cand = j;
           }
          if (j == cand) {
                cnt++;
            } else {
                cnt--;
            }
    }
     cnt = 0;
        for (int num : nums) {
            if (num == cand) {
                cnt++;
            }
        }
    return cnt > nums.length / 2 ? cand : -1;
}
}