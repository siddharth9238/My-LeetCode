import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        
        // Primitive array to track the current sequence without object overhead
        int[] current = new int[k];
        
        backtrack(n, k, 1, 0, current, result);
        
        return result;
    }

    private void backtrack(int n, int k, int start, int index, int[] current, List<List<Integer>> result) {
        // Base case: the primitive array has been fully populated
        if (index == k) {
            // Allocate exact capacity to prevent resizing
            List<Integer> combination = new ArrayList<>(k);
            for (int i = 0; i < k; i++) {
                combination.add(current[i]);
            }
            result.add(combination);
            return;
        }

        // Strict mathematical bound to prune impossible branches
        int limit = n - (k - index) + 1;
        
        for (int i = start; i <= limit; i++) {
            // Overwrite the array in-place, bypassing the need to "pop" elements
            current[index] = i;
            backtrack(n, k, i + 1, index + 1, current, result);
        }
    }
}