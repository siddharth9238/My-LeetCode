class Solution {
    public int uniquePaths(int m, int n) {
        // Total steps required to reach the bottom-right corner
        int totalSteps = m + n - 2;
        
        // Find the smaller dimension to optimize the combinatorics loop
        int k = m < n ? m - 1 : n - 1;
        
        long paths = 1;
        
        // Calculate (totalSteps) choose (k) using an iterative combinatorial formula
        for (int i = 1; i <= k; i++) {
            // Multiply by the decreasing numerator and divide by the increasing denominator
            paths = paths * (totalSteps - i + 1) / i;
        }
        
        return (int) paths;
    }
}