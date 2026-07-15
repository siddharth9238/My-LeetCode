class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // Fast-Fail: If the spawn point or destination is blocked, exit immediately
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        
        // Flat 1D DP array mapping exactly to the column width
        int[] dp = new int[n];
        
        // Base case: There is exactly 1 valid path to the starting square
        dp[0] = 1;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                
                if (obstacleGrid[i][j] == 1) {
                    // Obstacle encountered: zero out the paths for this column
                    dp[j] = 0;
                } else if (j > 0) {
                    // Accumulate paths: Paths from Above (existing dp[j]) + Paths from Left (dp[j-1])
                    dp[j] += dp[j - 1];
                }
                
            }
        }
        
        return dp[n - 1];
    }
}