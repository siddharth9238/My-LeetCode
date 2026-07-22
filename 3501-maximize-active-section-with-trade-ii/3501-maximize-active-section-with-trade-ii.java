import java.util.ArrayList;
import java.util.List;

class Solution {
    private int[] tree_max_Z;
    private int[] tree_min_O;
    private int[] tree_max_Z_adj;

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int global_ones = 0;
        
        int[] first_zero = new int[n];
        int[] last_zero = new int[n];
        int[] block_idx = new int[n];
        int[] start_B = new int[n];
        int[] end_B = new int[n];
        int[] Z = new int[n];
        int[] O = new int[n];
        
        int K = 0;
        int i = 0;
        
        // 1. Run-Length Compression for '0' Blocks
        while (i < n) {
            if (chars[i] == '1') {
                global_ones++;
                i++;
            } else {
                int start = i;
                while (i < n && chars[i] == '0') {
                    block_idx[i] = K;
                    i++;
                }
                start_B[K] = start;
                end_B[K] = i - 1;
                Z[K] = i - start;
                K++;
            }
        }
        
        // 2. Compute internal gaps and adjacencies
        int[] Z_adj = new int[K];
        for (int k = 0; k < K - 1; k++) {
            O[k] = start_B[k + 1] - end_B[k] - 1;
            Z_adj[k] = Z[k] + Z[k + 1];
        }
        
        // 3. Fast boundary snapping maps
        int last = -1;
        for (int j = n - 1; j >= 0; j--) {
            if (chars[j] == '0') last = j;
            first_zero[j] = last;
        }
        last = -1;
        for (int j = 0; j < n; j++) {
            if (chars[j] == '0') last = j;
            last_zero[j] = last;
        }
        
        // 4. Build Segment Trees
        if (K > 0) {
            tree_max_Z = new int[4 * K + 4];
            tree_min_O = new int[4 * K + 4];
            tree_max_Z_adj = new int[4 * K + 4];
            build(1, 0, K - 1, Z, O, Z_adj);
        }
        
        // 5. Evaluate Queries directly into a pre-allocated List
        List<Integer> ans = new ArrayList<>(queries.length);
        
        for (int q = 0; q < queries.length; q++) {
            int l = queries[q][0];
            int r = queries[q][1];
            
            int p1 = first_zero[l];
            // Prune: No '0's in range, so no trade is mathematically possible
            if (p1 == -1 || p1 > r) {
                ans.add(global_ones);
                continue;
            }
            
            int p2 = last_zero[r];
            int a = block_idx[p1];
            int b = block_idx[p2];
            
            // Prune: Only 1 '0' block in range. No bounded '1' exists to flip.
            if (a == b) {
                ans.add(global_ones);
                continue;
            }
            
            // Re-calculate the dynamically truncated bounds of the boundary blocks
            int Z_a = Math.min(r, end_B[a]) - p1 + 1;
            int Z_b = p2 - Math.max(l, start_B[b]) + 1;
            
            int M_internal = 0;
            if (a + 1 <= b - 1) {
                M_internal = query_max_Z(1, 0, K - 1, a + 1, b - 1);
            }
            int M = Math.max(Math.max(Z_a, Z_b), M_internal);
            
            int min_O = query_min_O(1, 0, K - 1, a, b - 1);
            int M_invO = -min_O;
            
            int M_adj = 0;
            if (b == a + 1) {
                M_adj = Z_a + Z_b;
            } else {
                M_adj = Math.max(Z_a + Z[a + 1], Z[b - 1] + Z_b);
                if (a + 1 <= b - 2) {
                    M_adj = Math.max(M_adj, query_max_Z_adj(1, 0, K - 1, a + 1, b - 2));
                }
            }
            
            int gain = Math.max(M_adj, M + M_invO);
            ans.add(global_ones + gain);
        }
        
        return ans;
    }
    
    // --- Flat Array Segment Tree Implementation ---
    
    private void build(int node, int l, int r, int[] Z, int[] O, int[] Z_adj) {
        if (l == r) {
            tree_max_Z[node] = Z[l];
            tree_min_O[node] = (l < O.length) ? O[l] : Integer.MAX_VALUE / 2;
            tree_max_Z_adj[node] = (l < Z_adj.length) ? Z_adj[l] : 0;
            return;
        }
        int mid = (l + r) / 2;
        build(node * 2, l, mid, Z, O, Z_adj);
        build(node * 2 + 1, mid + 1, r, Z, O, Z_adj);
        tree_max_Z[node] = Math.max(tree_max_Z[node * 2], tree_max_Z[node * 2 + 1]);
        tree_min_O[node] = Math.min(tree_min_O[node * 2], tree_min_O[node * 2 + 1]);
        tree_max_Z_adj[node] = Math.max(tree_max_Z_adj[node * 2], tree_max_Z_adj[node * 2 + 1]);
    }

    private int query_max_Z(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree_max_Z[node];
        int mid = (l + r) / 2;
        return Math.max(query_max_Z(node * 2, l, mid, ql, qr),
                        query_max_Z(node * 2 + 1, mid + 1, r, ql, qr));
    }

    private int query_min_O(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return Integer.MAX_VALUE / 2;
        if (ql <= l && r <= qr) return tree_min_O[node];
        int mid = (l + r) / 2;
        return Math.min(query_min_O(node * 2, l, mid, ql, qr),
                        query_min_O(node * 2 + 1, mid + 1, r, ql, qr));
    }

    private int query_max_Z_adj(int node, int l, int r, int ql, int qr) {
        if (ql > r || qr < l) return 0;
        if (ql <= l && r <= qr) return tree_max_Z_adj[node];
        int mid = (l + r) / 2;
        return Math.max(query_max_Z_adj(node * 2, l, mid, ql, qr),
                        query_max_Z_adj(node * 2 + 1, mid + 1, r, ql, qr));
    }
}