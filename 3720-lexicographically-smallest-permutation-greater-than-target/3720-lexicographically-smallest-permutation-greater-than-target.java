class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        // Find the maximum length of prefix from target we can match using characters in s
        int matchLen = 0;
        int[] tempCount = count.clone();
        for (int i = 0; i < n; i++) {
            int c = target.charAt(i) - 'a';
            if (tempCount[c] > 0) {
                tempCount[c]--;
                matchLen++;
            } else {
                break;
            }
        }
        
        // Try diverging from the maximum valid match down to index 0
        // We do it backwards so we get the longest matching prefix (which ensures the lexicographically smallest result)
        for (int i = Math.min(matchLen, n - 1); i >= 0; i--) {
            // Count array to track available characters for the current divergence attempt
            int[] avail = count.clone();
            
            // Subtract the characters used to form the prefix target[0..i-1]
            for (int j = 0; j < i; j++) {
                avail[target.charAt(j) - 'a']--;
            }
            
            // Find the smallest character strictly greater than target[i]
            char targetChar = target.charAt(i);
            int placeChar = -1;
            for (int c = targetChar - 'a' + 1; c < 26; c++) {
                if (avail[c] > 0) {
                    placeChar = c;
                    break;
                }
            }
            
            // If we found a valid character to place, construct the final string
            if (placeChar != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.substring(0, i)); // 1. Matched prefix
                sb.append((char) (placeChar + 'a')); // 2. The strictly greater character
                avail[placeChar]--; 
                
                // 3. Append remaining available characters in alphabetical order
                for (int c = 0; c < 26; c++) {
                    while (avail[c] > 0) {
                        sb.append((char) (c + 'a'));
                        avail[c]--;
                    }
                }
                return sb.toString();
            }
        }
        
        return ""; // No valid permutation exists
    }
}