class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        // The maximum possible size is the original length + 1 (no overlaps)
        int[][] res = new int[n + 1][2];
        
        int i = 0;
        int count = 0;
        
        // 1. Phase 1: Copy all intervals that end BEFORE the new interval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            res[count++] = intervals[i++];
        }
        
        // 2. Phase 2: Merge all overlapping intervals into the newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            // Inline min/max to bypass JVM method call overhead
            newInterval[0] = newInterval[0] < intervals[i][0] ? newInterval[0] : intervals[i][0];
            newInterval[1] = newInterval[1] > intervals[i][1] ? newInterval[1] : intervals[i][1];
            i++;
        }
        // Lock in the final merged interval
        res[count++] = newInterval;
        
        // 3. Phase 3: Copy all remaining intervals that start AFTER the new interval ends
        while (i < n) {
            res[count++] = intervals[i++];
        }
        
        // 4. Truncate the buffer to the exact count of processed intervals
        int[][] finalRes = new int[count][2];
        for (int j = 0; j < count; j++) {
            finalRes[j] = res[j];
        }
        
        return finalRes;
    }
}