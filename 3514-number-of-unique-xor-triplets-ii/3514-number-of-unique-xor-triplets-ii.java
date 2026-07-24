class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] present = new boolean[2048];
        int[] unique = new int[2048];
        int uCount = 0;
        
        // 1. Deduplicate the input array
        for (int num : nums) {
            if (!present[num]) {
                present[num] = true;
                unique[uCount++] = num;
            }
        }
        
        // 2. Generate all unique pairwise XOR values
        boolean[] pairXor = new boolean[2048];
        for (int i = 0; i < uCount; i++) {
            for (int j = i; j < uCount; j++) {
                pairXor[unique[i] ^ unique[j]] = true;
            }
        }
        
        // 3. Generate all unique triplet XOR values
        boolean[] tripletXor = new boolean[2048];
        int uniqueTriplets = 0;
        
        for (int p = 0; p < 2048; p++) {
            if (pairXor[p]) {
                for (int i = 0; i < uCount; i++) {
                    int val = p ^ unique[i];
                    
                    // Instantly count new discoveries
                    if (!tripletXor[val]) {
                        tripletXor[val] = true;
                        uniqueTriplets++;
                    }
                }
            }
        }
        
        return uniqueTriplets;
    }
}