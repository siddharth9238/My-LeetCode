class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        // dp[i][j] stores the max score difference for subarray piles[i...j]
        int[][] dp = new int[n][n];
        
        // Base case: subarray of length 1
        for (int i = 0; i < n; i++) {
            dp[i][i] = piles[i];
        }
        
        // Build the DP table for subarrays of length 2 to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                
                // Maximize the current player's score minus the opponent's score
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        
        // Alice wins if her score difference against Bob is greater than 0
        return dp[0][n - 1] > 0;
    }
}