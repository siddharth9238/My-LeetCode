class Solution {
    public int maxCoins(int[] nums) {
        int[] val = new int[nums.length + 2];
        int n = 1;
        
        for (int x : nums) {
            if (x > 0) {
                val[n++] = x;
            }
        }
        
        val[0] = 1;
        val[n++] = 1;
        
        int[][] dp = new int[n][n];
        
        for (int len = 2; len < n; len++) {
            for (int left = 0; left < n - len; left++) {
                int right = left + len;
                int max = 0;
                
                for (int k = left + 1; k < right; k++) {
                    int coins = val[left] * val[k] * val[right] + dp[left][k] + dp[k][right];
                    if (coins > max) {
                        max = coins;
                    }
                }
                
                dp[left][right] = max;
            }
        }
        
        return dp[0][n - 1];
    }
}