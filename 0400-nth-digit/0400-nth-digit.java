class Solution {
    public int findNthDigit(int n) {
        long digitLength = 1;
        long count = 9;
        long start = 1;

        // Find the range where the nth digit lies
        while (n > digitLength * count) {
            n -= digitLength * count;
            digitLength++;
            count *= 10;
            start *= 10;
        }

        // Find the actual number containing the nth digit
        start += (n - 1) / digitLength;

        // Find the digit within that number
        String num = Long.toString(start);
        return num.charAt((int) ((n - 1) % digitLength)) - '0';
    }
}