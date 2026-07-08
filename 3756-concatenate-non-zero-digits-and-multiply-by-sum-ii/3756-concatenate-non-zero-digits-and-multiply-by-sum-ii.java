class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();
        int MOD = 1000000007;
        
        // Precompute powers of 10 modulo 10^9 + 7 to prevent repeated math
        int[] p10 = new int[n + 1];
        p10[0] = 1;
        for (int i = 1; i <= n; i++) {
            p10[i] = (int) ((p10[i - 1] * 10L) % MOD);
        }
        
        // 1-based index prefix arrays to cleanly handle edge cases
        int[] count = new int[n + 1];
        int[] prefVal = new int[n + 1];
        int[] prefSum = new int[n + 1];
        
        char[] chars = s.toCharArray();
        
        // Pre-processing step: O(N)
        for (int i = 0; i < n; i++) {
            int d = chars[i] - '0';
            int next = i + 1;
            
            if (d != 0) {
                count[next] = count[i] + 1;
                prefVal[next] = (int) ((prefVal[i] * 10L + d) % MOD);
                prefSum[next] = prefSum[i] + d;
            } else {
                // If 0, carry over previous states identically
                count[next] = count[i];
                prefVal[next] = prefVal[i];
                prefSum[next] = prefSum[i];
            }
        }
        
        int q = queries.length;
        int[] ans = new int[q];
        
        // Query resolution step: O(1) per query
        for (int i = 0; i < q; i++) {
            int[] curr = queries[i];
            int l = curr[0];
            int r = curr[1] + 1; // Map to 1-based indexing for the right bound
            
            int c = count[r] - count[l];
            long valL = prefVal[l];
            long valR = prefVal[r];
            
            // Mathematically extract the substring integer value x
            long x = (valR - valL * p10[c]) % MOD;
            
            // Java's modulo operator can return negative values, wrap it to positive
            if (x < 0) {
                x += MOD;
            }
            
            long sum = prefSum[r] - prefSum[l];
            
            ans[i] = (int) ((x * sum) % MOD);
        }
        
        return ans;
    }
}