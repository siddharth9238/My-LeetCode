class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        int initialOnes = 0;
        
        int[] runs = new int[n];
        boolean[] isZero = new boolean[n];
        int runCount = 0;
        
        // 1. Run-Length Compression
        int i = 0;
        while (i < n) {
            char c = chars[i];
            int count = 0;
            while (i < n && chars[i] == c) {
                if (c == '1') initialOnes++;
                count++;
                i++;
            }
            runs[runCount] = count;
            isZero[runCount] = (c == '0');
            runCount++;
        }
        
        // 2. Map the 0-blocks
        int[] zIndices = new int[runCount];
        int m = 0;
        for (int j = 0; j < runCount; j++) {
            if (isZero[j]) {
                zIndices[m++] = j;
            }
        }
        
        // If there are less than two 0-blocks, no internal 1-blocks exist. No trade possible.
        if (m < 2) {
            return initialOnes;
        }
        
        // 3. Precalculate Top 3 largest 0-blocks for instant O(1) lookups
        int top1 = -1, top2 = -1, top3 = -1;
        int v1 = -1, v2 = -1, v3 = -1;
        
        for (int j = 0; j < m; j++) {
            int val = runs[zIndices[j]];
            if (val > v1) {
                v3 = v2; top3 = top2;
                v2 = v1; top2 = top1;
                v1 = val; top1 = j;
            } else if (val > v2) {
                v3 = v2; top3 = top2;
                v2 = val; top2 = j;
            } else if (val > v3) {
                v3 = val; top3 = j;
            }
        }
        
        // 4. Evaluate all valid trades
        int maxNetGain = 0;
        for (int j = 0; j < m - 1; j++) {
            int zLeft = runs[zIndices[j]];
            int zRight = runs[zIndices[j + 1]];
            int oMid = runs[zIndices[j] + 1];
            
            // Find the largest 0-block outside of our merged cluster
            int mRest = 0;
            if (top1 != j && top1 != j + 1) mRest = v1;
            else if (top2 != j && top2 != j + 1) mRest = v2;
            else if (top3 != j && top3 != j + 1) mRest = v3;
            
            int merged = zLeft + oMid + zRight;
            int maxAvailableZero = Math.max(merged, mRest);
            
            int netGain = maxAvailableZero - oMid;
            if (netGain > maxNetGain) {
                maxNetGain = netGain;
            }
        }
        
        return initialOnes + maxNetGain;
    }
}