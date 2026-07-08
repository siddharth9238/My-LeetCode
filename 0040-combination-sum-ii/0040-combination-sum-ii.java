import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        // Sort to enable both deep pruning and sibling deduplication
        Arrays.sort(candidates);
        
        // Max depth is target (30) / min candidate (1) = 30.
        int[] path = new int[30];
        
        dfs(candidates, target, 0, path, 0, res);
        
        return res;
    }
    
    private void dfs(int[] candidates, int remain, int start, int[] path, int pathLen, List<List<Integer>> res) {
        if (remain == 0) {
            // Only allocate an object when a valid combination is confirmed
            List<Integer> validCombo = new ArrayList<>(pathLen);
            for (int i = 0; i < pathLen; i++) {
                validCombo.add(path[i]);
            }
            res.add(validCombo);
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            // 1. Deep Pruning: Abort branch if target is exceeded
            if (remain - candidates[i] < 0) {
                break;
            }
            
            // 2. Sibling Deduplication: Skip identical elements at the same tree depth
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            // Overwrite the primitive array instead of doing dynamic object insertion
            path[pathLen] = candidates[i];
            
            // Pass 'i + 1' to ensure each candidate is only used once
            dfs(candidates, remain - candidates[i], i + 1, path, pathLen + 1, res);
        }
    }
}