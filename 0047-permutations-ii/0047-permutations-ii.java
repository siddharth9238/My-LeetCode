import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        // Sort to group duplicates, enabling O(1) sibling deduplication
        Arrays.sort(nums);
        
        boolean[] used = new boolean[nums.length];
        int[] path = new int[nums.length];
        
        backtrack(nums, res, path, used, 0);
        
        return res;
    }

    private void backtrack(int[] nums, List<List<Integer>> res, int[] path, boolean[] used, int depth) {
        // Base case: the path is full
        if (depth == nums.length) {
            List<Integer> current = new ArrayList<>(nums.length);
            for (int num : path) {
                current.add(num);
            }
            res.add(current);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            // Skip if this specific element is already in our current path
            if (used[i]) {
                continue;
            }
            
            // Sibling Deduplication: 
            // If it's a duplicate and the previous identical element was NOT used in this 
            // specific depth slot, we've already generated all permutations for this state.
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            // Lock the element into the path
            used[i] = true;
            path[depth] = nums[i];
            
            // Branch out to the next depth
            backtrack(nums, res, path, used, depth + 1);
            
            // Backtrack: Release the element
            used[i] = false;
        }
    }
}