import java.util.Arrays;

class Solution {
    public int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) {
            return intervals;
        }

        // 1. Extract into 1D primitive arrays for zero-allocation sorting
        int[] starts = new int[n];
        int[] ends = new int[n];
        
        for (int i = 0; i < n; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        
        // 2. Blazing fast primitive sorts
        Arrays.sort(starts);
        Arrays.sort(ends);
        
        // Pre-allocate the maximum possible size for our result array
        int[][] res = new int[n][2];
        int count = 0;
        int startIndex = 0;
        
        // 3. Evaluate boundaries
        for (int i = 0; i < n; i++) {
            // If we are at the last element, or a clean break is found between clumps
            if (i == n - 1 || starts[i + 1] > ends[i]) {
                res[count][0] = starts[startIndex];
                res[count][1] = ends[i];
                count++;
                
                // The next overlapping cluster will begin at i + 1
                startIndex = i + 1;
            }
        }
        
        // 4. Trim the flat array to the exact count of merged intervals
        int[][] finalRes = new int[count][2];
        for (int i = 0; i < count; i++) {
            finalRes[i] = res[i];
        }
        
        return finalRes;
    }
}