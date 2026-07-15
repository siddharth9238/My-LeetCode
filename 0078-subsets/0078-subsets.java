import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        
        List<List<Integer>> result = new ArrayList<>(1 << n);
        
        int[] current = new int[n];
        
        backtrack(nums, 0, 0, current, result);
        
        return result;
    }
    
    private void backtrack(int[] nums, int start, int index, int[] current, List<List<Integer>> result) {
        
        List<Integer> subset = new ArrayList<>(index);
        for (int i = 0; i < index; i++) {
            subset.add(current[i]);
        }
        result.add(subset);
        
        
        for (int i = start; i < nums.length; i++) {
            
            current[index] = nums[i];
            backtrack(nums, i + 1, index + 1, current, result);
        }
    }
}