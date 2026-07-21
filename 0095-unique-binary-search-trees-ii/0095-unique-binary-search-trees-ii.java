/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        // Allocate a memoization matrix with padding to prevent out-of-bounds checks
        List<TreeNode>[][] memo = new List[n + 2][n + 2];
        return buildTrees(1, n, memo);
    }
    
    private List<TreeNode> buildTrees(int start, int end, List<TreeNode>[][] memo) {
        if (start > end) {
            List<TreeNode> base = new ArrayList<>();
            base.add(null);
            return base;
        }
        
        // Return instantly if the subtree permutations for this range are already calculated
        if (memo[start][end] != null) {
            return memo[start][end];
        }
        
        List<TreeNode> result = new ArrayList<>();
        
        // Iterate through every number to treat it as the root
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = buildTrees(start, i - 1, memo);
            List<TreeNode> rightTrees = buildTrees(i + 1, end, memo);
            
            // Cartesian product of the permutations
            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    result.add(root);
                }
            }
        }
        
        // Cache the dynamically built permutations
        memo[start][end] = result;
        return result;
    }
}