function balanceBST(root: TreeNode | null): TreeNode | null {
    const nodes = new Array<TreeNode>(10005);
    let count = 0;
    
    const stack = new Array<TreeNode>(10005);
    let top = 0;
    
    let curr = root;
    while (curr !== null || top > 0) {
        while (curr !== null) {
            stack[top++] = curr;
            curr = curr.left;
        }
        curr = stack[--top];
        nodes[count++] = curr;
        curr = curr.right;
    }
    
    function build(start: number, end: number): TreeNode | null {
        if (start > end) {
            return null;
        }
        
        const mid = (start + end) >> 1;
        const node = nodes[mid];
        
        node.left = build(start, mid - 1);
        node.right = build(mid + 1, end);
        
        return node;
    }
    
    return build(0, count - 1);
}