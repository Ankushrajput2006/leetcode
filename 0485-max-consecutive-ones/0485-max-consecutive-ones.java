class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int cnt = 0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==1){
                cnt++;
                max = max(cnt,max);
            }
            else{
                cnt = 0;
            }
        }
        return max;
    }
    public int max(int n ,int m){
        int max1 = 0;
        if(n>m){
            max1 = n;
        }
        else{
            max1 = m;
        }
        return max1;
    }
}