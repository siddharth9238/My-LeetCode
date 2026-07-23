class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        
        // Fast-fail exact edge cases mathematically unable to fill out the binary power space
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        
        // For n >= 3, the number of unique permutations fills the exact next power of 2
        int p = 1;
        while (p <= n) {
            p <<= 1;
        }
        
        return p;
    }
}