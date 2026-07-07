import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        
        if (n < 3) {
            return res;
        }
        
        Arrays.sort(nums);
        
        for (int i = 0; i < n - 2; i++) {
            int a = nums[i];
            
            if (a > 0) {
                break;
            }
            if (i > 0 && a == nums[i - 1]) {
                continue;
            }
            if (a + nums[n - 1] + nums[n - 2] < 0) {
                continue;
            }
            if (a + nums[i + 1] + nums[i + 2] > 0) {
                break;
            }
            
            int left = i + 1;
            int right = n - 1;
            
            while (left < right) {
                int b = nums[left];
                int c = nums[right];
                int sum = a + b + c;
                
                if (sum == 0) {
                    res.add(Arrays.asList(a, b, c));
                    
                    while (left < right && nums[left] == b) {
                        left++;
                    }
                    while (left < right && nums[right] == c) {
                        right--;
                    }
                } else if (sum < 0) {
                    while (left < right && nums[left] == b) {
                        left++;
                    }
                } else {
                    while (left < right && nums[right] == c) {
                        right--;
                    }
                }
            }
        }
        
        return res;
    }
}