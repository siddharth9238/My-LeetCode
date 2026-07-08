class Solution {
    public double myPow(double x, int n) {
        // Upgrade to 64-bit integer to prevent Integer.MIN_VALUE overflow
        long N = n;
        
        // Handle negative powers mathematically
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double ans = 1.0;
        
        // Iterative binary exponentiation
        while (N > 0) {
            // If the lowest bit is 1, the current power is odd
            if ((N & 1) == 1) {
                ans *= x;
            }
            
            // Square the base
            x *= x;
            
            // Unsigned right shift to divide the power by 2
            N >>>= 1;
        }
        
        return ans;
    }
}