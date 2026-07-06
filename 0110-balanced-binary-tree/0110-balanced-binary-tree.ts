/**
 * Definition for a binary tree node.
 * class TreeNode {
 *     val: number
 *     left: TreeNode | null
 *     right: TreeNode | null
 *     constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
 *         this.val = (val===undefined ? 0 : val)
 *         this.left = (left===undefined ? null : left)
 *         this.right = (right===undefined ? null : right)
 *     }
 * }
 */

function isBalanced(root: TreeNode | null): boolean {
    return checkHeight(root) !== -1;
}

function checkHeight(node: TreeNode | null): number {
    if (node === null) {
        return 0;
    }

    const leftHeight = checkHeight(node.left);
    if (leftHeight === -1) {
        return -1;
    }

    const rightHeight = checkHeight(node.right);
    if (rightHeight === -1) {
        return -1;
    }

    const diff = leftHeight > rightHeight ? leftHeight - rightHeight : rightHeight - leftHeight;
    if (diff > 1) {
        return -1;
    }

    return (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
}