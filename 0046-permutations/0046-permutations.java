import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        // Begin the in-place permutation generation
        backtrack(res, nums, 0);
        
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, int start) {
        // Base case: the 'locked' partition has reached the end of the array
        if (start == nums.length) {
            // Allocate the final object and map the primitive array into it
            List<Integer> current = new ArrayList<>(nums.length);
            for (int num : nums) {
                current.add(num);
            }
            res.add(current);
            return;
        }

        // Branch out by swapping available elements into the 'start' position
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            
            // Move the boundary forward to lock in the element
            backtrack(res, nums, start + 1);
            
            // Backtrack by restoring the array to its previous state
            swap(nums, start, i);
        }
    }

    // Extracted primitive swap method for clean, fast pointer manipulation
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}