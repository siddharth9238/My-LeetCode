class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] max = new int[k];
        
        for (int i = Math.max(0, k - n); i <= Math.min(k, m); i++) {
            int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i), k);
            if (greater(candidate, 0, max, 0)) {
                max = candidate;
            }
        }
        return max;
    }
    
    private int[] maxArray(int[] nums, int k) {
        int[] res = new int[k];
        int n = nums.length;
        int drop = n - k;
        int p = 0;
        
        for (int i = 0; i < n; i++) {
            while (drop > 0 && p > 0 && res[p - 1] < nums[i]) {
                p--;
                drop--;
            }
            if (p < k) {
                res[p++] = nums[i];
            } else {
                drop--;
            }
        }
        return res;
    }
    
    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int i = 0, j = 0;
        
        for (int r = 0; r < k; r++) {
            if (greater(nums1, i, nums2, j)) {
                res[r] = nums1[i++];
            } else {
                res[r] = nums2[j++];
            }
        }
        return res;
    }
    
    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }
        if (j == nums2.length) {
            return true;
        }
        if (i == nums1.length) {
            return false;
        }
        return nums1[i] > nums2[j];
    }
}