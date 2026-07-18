class Solution {
    public int findGCD(int[] arr) {
        int high = 0;
        int low = arr[0];
        int gcd = 0;
        int n =0;
        for(int i=0;i<arr.length;i++){
            if(arr[i]>high){
                high = arr[i];
            }
            if(arr[i]<low){
                low = arr[i];
            }
        }
        for(int k=1;k<=low;k++){
            if(high%k==0 && low%k==0){
                n=k;
            }
            if(n>gcd){
                gcd=n;
            }
        }
        return gcd;
    }
}