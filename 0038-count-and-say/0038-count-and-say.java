class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        
        // The maximum length for n=30 is mathematically capped at 4462 characters.
        // 5000 provides a perfectly safe, flat memory buffer.
        char[] curr = new char[5000];
        char[] next = new char[5000];
        
        curr[0] = '1';
        int len = 1;
        
        for (int i = 2; i <= n; i++) {
            int nextLen = 0;
            int count = 1;
            
            for (int j = 1; j < len; j++) {
                if (curr[j] == curr[j - 1]) {
                    count++;
                } else {
                    // Write the count and the character to the next buffer
                    next[nextLen++] = (char) (count + '0');
                    next[nextLen++] = curr[j - 1];
                    count = 1; // Reset count for the new character
                }
            }
            
            // Write the final sequence of the current string
            next[nextLen++] = (char) (count + '0');
            next[nextLen++] = curr[len - 1];
            
            // Swap the pointers (Zero-allocation state transfer)
            char[] temp = curr;
            curr = next;
            next = temp;
            
            len = nextLen;
        }
        
        // Instantiate the ONLY object in the entire algorithm
        return new String(curr, 0, len);
    }
}