class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // 1. Initialize the top row (paths can only come from the left)
        for (int j = 1; j < n; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        
        // 2. Initialize the left column (paths can only come from above)
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        
        // 3. Process the remainder of the grid
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // Inline min calculation to bypass JVM method call overhead
                int fromAbove = grid[i - 1][j];
                int fromLeft = grid[i][j - 1];
                
                grid[i][j] += (fromAbove < fromLeft) ? fromAbove : fromLeft;
            }
        }
        
        // The bottom-right cell now mathematically guarantees the minimum path sum
        return grid[m - 1][n - 1];
    }
}