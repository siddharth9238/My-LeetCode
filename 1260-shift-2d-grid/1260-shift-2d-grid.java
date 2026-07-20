import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;
        
        // Normalize k to prevent redundant full-grid wraps
        k = k % total;
        
        List<List<Integer>> result = new ArrayList<>(m);
        
        for (int r = 0; r < m; r++) {
            List<Integer> row = new ArrayList<>(n);
            for (int c = 0; c < n; c++) {
                // Calculate the 1D index of the current cell
                int newLinearIndex = r * n + c;
                
                // Traverse backward mathematically to find the source element
                int oldLinearIndex = (newLinearIndex - k + total) % total;
                
                int oldR = oldLinearIndex / n;
                int oldC = oldLinearIndex % n;
                
                row.add(grid[oldR][oldC]);
            }
            result.add(row);
        }
        
        return result;
    }
}