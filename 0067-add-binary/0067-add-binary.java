class Solution {
    public String addBinary(String a, String b) {
        int lenA = a.length();
        int lenB = b.length();
        
        // Inline max length evaluation to avoid Math.max() function overhead
        int maxLen = lenA > lenB ? lenA : lenB;
        
        // The output can be at most 1 bit longer than the longest string
        char[] result = new char[maxLen + 1];
        
        int i = lenA - 1;
        int j = lenB - 1;
        int k = maxLen; // Start filling from the end of the array
        int carry = 0;
        
        while (i >= 0 || j >= 0) {
            int sum = carry;
            
            if (i >= 0) {
                // Subtracting '0' converts the ascii character to integer 0 or 1
                sum += a.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            
            // sum & 1 extracts the lowest bit (same as sum % 2)
            result[k] = (char) ((sum & 1) + '0');
            
            // sum >> 1 shifts the bits right (same as sum / 2)
            carry = sum >> 1;
            k--;
        }
        
        if (carry > 0) {
            result[0] = '1';
            return new String(result);
        }
        
        // If there is no final carry, skip the empty 0th index 
        return new String(result, 1, maxLen);
    }
}