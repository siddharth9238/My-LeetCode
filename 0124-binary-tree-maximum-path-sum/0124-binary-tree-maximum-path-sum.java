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
class Solution {
    private int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int left = dfs(node.left);
        if (left < 0) {
            left = 0;
        }
        
        int right = dfs(node.right);
        if (right < 0) {
            right = 0;
        }
        
        int currentSum = node.val + left + right;
        if (currentSum > maxSum) {
            maxSum = currentSum;
        }
        
        return node.val + (left > right ? left : right);
    }
}