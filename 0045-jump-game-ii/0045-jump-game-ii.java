class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        // Edge case: if the array length is 1, we are already at the end.
        if (n <= 1) {
            return 0;
        }

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        // Iterate up to the second-to-last element
        for (int i = 0; i < n - 1; i++) {
            
            // Inline Math.max() to avoid JVM method call overhead
            int nextReach = i + nums[i];
            if (nextReach > farthest) {
                farthest = nextReach;
            }

            // If we have reached the end of the current jump window
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                
                // Fast-fail: If our current window covers the end of the array, we are done
                if (currentEnd >= n - 1) {
                    break;
                }
            }
        }
        
        return jumps;
    }
}