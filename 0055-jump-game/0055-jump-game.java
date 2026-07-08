class Solution {
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        int target = nums.length - 1;
        
        for (int i = 0; i <= target; i++) {
            // Fast-Fail: If we arrive at an index beyond our max reach, we are stuck.
            if (i > maxReach) {
                return false;
            }
            
            // Inline primitive max calculation (bypassing Math.max method overhead)
            int currentReach = i + nums[i];
            if (currentReach > maxReach) {
                maxReach = currentReach;
            }
            
            // Early Exit: If our reach covers the final index, stop processing immediately.
            if (maxReach >= target) {
                return true;
            }
        }
        
        return true;
    }
}