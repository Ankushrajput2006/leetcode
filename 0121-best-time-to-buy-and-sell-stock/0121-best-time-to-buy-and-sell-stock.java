class Solution {
    public int maxProfit(int[] prices) {
       int mini = Integer.MAX_VALUE;
       int profit = 0;
       for(int i=0;i<prices.length;i++){
         if(prices[i]<mini){
            mini = prices[i];
         }
         else{
            profit = Math.max(profit,prices[i]-mini);
         }
       }
       return profit;
    }
}