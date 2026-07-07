import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        
        int closestSum = nums[0] + nums[1] + nums[2];
        int minDiff = closestSum - target;
        minDiff = minDiff < 0 ? -minDiff : minDiff;

        for (int i = 0; i < n - 2; i++) {
            // Skip duplicate anchors
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            int a = nums[i];
            
            // Pruning 1: Minimum possible sum for this anchor overshoots target
            int minSum = a + nums[i + 1] + nums[i + 2];
            if (minSum >= target) {
                int diff = minSum - target;
                if (diff < minDiff) {
                    minDiff = diff;
                    closestSum = minSum;
                }
                break; // All subsequent combinations will only be larger
            }
            
            // Pruning 2: Maximum possible sum for this anchor falls short of target
            int maxSum = a + nums[n - 2] + nums[n - 1];
            if (maxSum <= target) {
                int diff = target - maxSum;
                if (diff < minDiff) {
                    minDiff = diff;
                    closestSum = maxSum;
                }
                continue; // Move to the next anchor to find larger sums
            }
            
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int sum = a + nums[left] + nums[right];
                
                if (sum == target) {
                    return target; // Perfect match, absolute closest possible
                }
                
                int diff = sum - target;
                int absDiff = diff < 0 ? -diff : diff;
                
                if (absDiff < minDiff) {
                    minDiff = absDiff;
                    closestSum = sum;
                }
                
                if (sum < target) {
                    left++;
                    // Fast-forward identical left pointers
                    while (left < right && nums[left] == nums[left - 1]) left++;
                } else {
                    right--;
                    // Fast-forward identical right pointers
                    while (left < right && nums[right] == nums[right + 1]) right--;
                }
            }
        }
        
        return closestSum;
    }
}