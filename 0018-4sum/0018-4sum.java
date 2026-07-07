import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        
        if (n < 4) {
            return res;
        }
        
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 3; i++) {
            // Skip duplicate outer anchors
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            // L1 Pruning: Minimum possible sum overshoots target
            long min1 = (long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3];
            if (min1 > target) {
                break;
            }
            
            // L1 Pruning: Maximum possible sum falls short of target
            long max1 = (long) nums[i] + nums[n - 1] + nums[n - 2] + nums[n - 3];
            if (max1 < target) {
                continue;
            }
            
            for (int j = i + 1; j < n - 2; j++) {
                // Skip duplicate inner anchors
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                
                // L2 Pruning: Minimum possible sum overshoots target
                long min2 = (long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2];
                if (min2 > target) {
                    break;
                }
                
                // L2 Pruning: Maximum possible sum falls short of target
                long max2 = (long) nums[i] + nums[j] + nums[n - 1] + nums[n - 2];
                if (max2 < target) {
                    continue;
                }
                
                int left = j + 1;
                int right = n - 1;
                
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    
                    if (sum == target) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        left++;
                        right--;
                        
                        // Fast-forward identical pointers
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        
        return res;
    }
}