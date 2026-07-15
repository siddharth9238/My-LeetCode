class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        int val = 1;
        
        // Loop runs exactly n * n times
        while (val <= n * n) {
            // 1. Traverse top row (Left to Right)
            for (int j = left; j <= right; j++) {
                matrix[top][j] = val++;
            }
            top++; // Shrink top boundary
            
            // 2. Traverse right column (Top to Bottom)
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = val++;
            }
            right--; // Shrink right boundary
            
            // 3. Traverse bottom row (Right to Left)
            for (int j = right; j >= left; j--) {
                matrix[bottom][j] = val++;
            }
            bottom--; // Shrink bottom boundary
            
            // 4. Traverse left column (Bottom to Top)
            for (int i = bottom; i >= top; i--) {
                matrix[i][left] = val++;
            }
            left++; // Shrink left boundary
        }
        
        return matrix;
    }
}