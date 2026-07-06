function longestBalanced(nums: number[]): number {
    const n = nums.length;
    if (n === 0) return 0;

    const prev = new Int32Array(100005);
    prev.fill(-1);
    
    const minTree = new Int32Array(524288);
    const maxTree = new Int32Array(524288);
    const lazy = new Int32Array(524288);

    function push(node: number) {
        const l = lazy[node];
        if (l !== 0) {
            const left = node << 1;
            const right = left | 1;
            
            minTree[left] += l;
            maxTree[left] += l;
            lazy[left] += l;
            
            minTree[right] += l;
            maxTree[right] += l;
            lazy[right] += l;
            
            lazy[node] = 0;
        }
    }

    function update(node: number, start: number, end: number, l: number, r: number, val: number) {
        if (start > r || end < l) return;
        if (start >= l && end <= r) {
            minTree[node] += val;
            maxTree[node] += val;
            lazy[node] += val;
            return;
        }
        push(node);
        const mid = (start + end) >> 1;
        const left = node << 1;
        const right = left | 1;
        
        update(left, start, mid, l, r, val);
        update(right, mid + 1, end, l, r, val);
        
        const minL = minTree[left];
        const minR = minTree[right];
        minTree[node] = minL < minR ? minL : minR;
        
        const maxL = maxTree[left];
        const maxR = maxTree[right];
        maxTree[node] = maxL > maxR ? maxL : maxR;
    }

    function findFirstZero(node: number, start: number, end: number): number {
        while (start < end) {
            push(node);
            const mid = (start + end) >> 1;
            const left = node << 1;
            
            if (minTree[left] <= 0 && maxTree[left] >= 0) {
                node = left;
                end = mid;
            } else {
                node = left | 1;
                start = mid + 1;
            }
        }
        return start;
    }

    function query0(node: number, start: number, end: number, ql: number, qr: number): number {
        if (start > qr || end < ql) return -1;
        
        if (start >= ql && end <= qr) {
            if (minTree[node] > 0 || maxTree[node] < 0) return -1;
            return findFirstZero(node, start, end);
        }
        
        push(node);
        const mid = (start + end) >> 1;
        const left = node << 1;
        const right = left | 1;
        
        const res = query0(left, start, mid, ql, qr);
        if (res !== -1) return res;
        
        return query0(right, mid + 1, end, ql, qr);
    }

    let maxLen = 0;
    
    for (let i = 0; i < n; i++) {
        const x = nums[i];
        const p = prev[x];
        const val = (x & 1) === 0 ? 1 : -1;
        
        update(1, 0, n - 1, p + 1, i, val);
        prev[x] = i;
        
        const L = query0(1, 0, n - 1, 0, i);
        if (L !== -1) {
            const len = i - L + 1;
            if (len > maxLen) {
                maxLen = len;
            }
        }
    }
    
    return maxLen;
}