class Solution {
    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        
        // dp[i] stores the maximum score difference for the current subarray length
        int[] dp = new int[n];
        
        // Base case: Subarray of length 1 (only one choice available)
        for (int i = 0; i < n; i++) {
            dp[i] = nums[i];
        }
        
        // Process subarrays of length 2 up to n
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                // dp[i + 1] corresponds to taking nums[i] (left choice)
                // dp[i] corresponds to taking nums[j] (right choice)
                dp[i] = Math.max(nums[i] - dp[i + 1], nums[j] - dp[i]);
            }
        }
        
        // Player 1 wins if the overall score difference is non-negative
        return dp[0] >= 0;
    }
}