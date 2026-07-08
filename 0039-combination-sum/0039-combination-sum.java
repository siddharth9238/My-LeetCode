import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        // Sort to enable aggressive mathematical branch pruning
        Arrays.sort(candidates);
        
        // Max depth is target (40) / min candidate (2) = 20.
        int[] path = new int[20];
        
        dfs(candidates, target, 0, path, 0, res);
        
        return res;
    }
    
    private void dfs(int[] candidates, int remain, int start, int[] path, int pathLen, List<List<Integer>> res) {
        if (remain == 0) {
            // Only allocate objects when a guaranteed valid combination is found
            List<Integer> validCombo = new ArrayList<>(pathLen);
            for (int i = 0; i < pathLen; i++) {
                validCombo.add(path[i]);
            }
            res.add(validCombo);
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            // Because candidates are sorted, if this number overshoots the remaining target,
            // all subsequent numbers will definitely overshoot. Abort this branch.
            if (remain - candidates[i] < 0) {
                break;
            }
            
            // Overwrite the primitive array instead of doing dynamic object insertion
            path[pathLen] = candidates[i];
            
            // Pass 'i' instead of 'i + 1' because candidates can be reused infinitely
            dfs(candidates, remain - candidates[i], i, path, pathLen + 1, res);
        }
    }
}