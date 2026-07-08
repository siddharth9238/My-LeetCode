import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Pre-allocate exact capacity to prevent dynamic array resizing overhead
        List<Integer> res = new ArrayList<>(m * n);
        
        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;
        
        while (top <= bottom && left <= right) {
            // 1. Traverse top row (Left to Right)
            for (int j = left; j <= right; j++) {
                res.add(matrix[top][j]);
            }
            top++; // Shrink top boundary
            
            // 2. Traverse right column (Top to Bottom)
            for (int i = top; i <= bottom; i++) {
                res.add(matrix[i][right]);
            }
            right--; // Shrink right boundary
            
            // Validate boundaries before attempting reverse traversals
            if (top <= bottom) {
                // 3. Traverse bottom row (Right to Left)
                for (int j = right; j >= left; j--) {
                    res.add(matrix[bottom][j]);
                }
                bottom--; // Shrink bottom boundary
            }
            
            if (left <= right) {
                // 4. Traverse left column (Bottom to Top)
                for (int i = bottom; i >= top; i--) {
                    res.add(matrix[i][left]);
                }
                left++; // Shrink left boundary
            }
        }
        
        return res;
    }
}