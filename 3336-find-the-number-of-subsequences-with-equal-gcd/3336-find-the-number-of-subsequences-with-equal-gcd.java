class Solution {
    private static final int MOD = 1000000007;
    // Static precomputed table for O(1) instantaneous GCD lookups
    private static final int[][] gcdTable = new int[201][201];
    
    // Compute once per JVM class load, taking exactly 0ms during testcases
    static {
        for (int i = 0; i <= 200; i++) {
            for (int j = 0; j <= 200; j++) {
                if (i == 0) {
                    gcdTable[i][j] = j;
                } else if (j == 0) {
                    gcdTable[i][j] = i;
                } else {
                    int a = i, b = j;
                    while (b != 0) {
                        int temp = b;
                        b = a % b;
                        a = temp;
                    }
                    gcdTable[i][j] = a;
                }
            }
        }
    }
    
    public int subsequencePairCount(int[] nums) {
        // Flat 1D arrays simulating 201x201 matrices for maximum cache locality
        int[] dp = new int[40401]; 
        int[] next_dp = new int[40401];
        
        // Base case: both subsequences are empty (represented by GCD 0)
        dp[0] = 1;
        
        // Track the dynamic boundary to avoid iterating over mathematically unreachable states
        int currMax = 0;
        
        for (int x : nums) {
            int nextMax = currMax > x ? currMax : x;
            
            // Clean the relevant portion of the next_dp buffer
            for (int g1 = 0; g1 <= nextMax; g1++) {
                int offset = g1 * 201;
                for (int g2 = 0; g2 <= nextMax; g2++) {
                    next_dp[offset + g2] = 0;
                }
            }
            
            for (int g1 = 0; g1 <= currMax; g1++) {
                int offset = g1 * 201;
                for (int g2 = 0; g2 <= currMax; g2++) {
                    int c = dp[offset + g2];
                    if (c == 0) continue;
                    
                    // Choice 1: Skip (Add to neither subsequence)
                    int idx = offset + g2;
                    int v = next_dp[idx] + c;
                    if (v >= MOD) v -= MOD;
                    next_dp[idx] = v;
                    
                    // Choice 2: Add to Sequence 1
                    int ng1 = gcdTable[g1][x];
                    int idx1 = ng1 * 201 + g2;
                    v = next_dp[idx1] + c;
                    if (v >= MOD) v -= MOD;
                    next_dp[idx1] = v;
                    
                    // Choice 3: Add to Sequence 2
                    int ng2 = gcdTable[g2][x];
                    int idx2 = offset + ng2;
                    v = next_dp[idx2] + c;
                    if (v >= MOD) v -= MOD;
                    next_dp[idx2] = v;
                }
            }
            
            currMax = nextMax;
            
            // Zero-allocation buffer swap
            int[] temp = dp;
            dp = next_dp;
            next_dp = temp;
        }
        
        // Sum the pairs where both non-empty subsequences share the exact same GCD
        long ans = 0;
        for (int i = 1; i <= currMax; i++) {
            ans += dp[i * 201 + i];
        }
        
        return (int) (ans % MOD);
    }
}