public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        
        for (int i = 0; i < 32; i++) {
            // Shift the result left to make room for the next bit
            res <<= 1;
            
            // Extract the lowest bit of n and merge it into the result
            res |= (n & 1);
            
            // Shift n to the right (unsigned) to process the next bit
            n >>>= 1;
        }
        
        return res;
    }
}