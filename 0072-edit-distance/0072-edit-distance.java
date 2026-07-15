class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        
        // Fast-fail edge cases
        if (n == 0) return m;
        if (m == 0) return n;
        
        // Extract to primitive arrays for bound-free sequential memory access
        char[] w1 = word1.toCharArray();
        char[] w2 = word2.toCharArray();
        
        // Compress the 2D DP matrix into a single 1D array sized to word2
        int[] dp = new int[m + 1];
        
        // Initialize the base case (transforming an empty word1 into word2)
        for (int j = 0; j <= m; j++) {
            dp[j] = j;
        }
        
        for (int i = 1; i <= n; i++) {
            // 'prev' tracks the diagonal value (top-left) in the virtual 2D matrix
            int prev = dp[0];
            
            // The first column represents deleting characters to match an empty string
            dp[0] = i;
            
            for (int j = 1; j <= m; j++) {
                // Store the value above us before we overwrite it; it becomes the diagonal for the next column
                int temp = dp[j]; 
                
                if (w1[i - 1] == w2[j - 1]) {
                    // Characters match: cost is whatever it took to match the prefix
                    dp[j] = prev;
                } else {
                    // Characters differ: evaluate Insert (dp[j-1]), Delete (dp[j]), Replace (prev)
                    int min = prev;
                    if (dp[j] < min) {
                        min = dp[j];
                    }
                    if (dp[j - 1] < min) {
                        min = dp[j - 1];
                    }
                    dp[j] = min + 1;
                }
                
                // Shift the diagonal pointer forward
                prev = temp;
            }
        }
        
        // The final element mathematically guarantees the absolute minimum edit distance
        return dp[m];
    }
}