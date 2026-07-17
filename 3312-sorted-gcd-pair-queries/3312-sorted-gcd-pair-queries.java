class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            if (num > maxVal) {
                maxVal = num;
            }
        }
        
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }
        
        long[] exact = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long multipleCount = 0;
            for (int j = i; j <= maxVal; j += i) {
                multipleCount += freq[j];
            }
            
            long pairs = multipleCount * (multipleCount - 1) / 2;
            
            for (int j = i * 2; j <= maxVal; j += i) {
                pairs -= exact[j];
            }
            
            exact[i] = pairs;
        }
        
        long[] prefix = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefix[i] = prefix[i - 1] + exact[i];
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            int left = 1;
            int right = maxVal;
            int res = 1;
            
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (prefix[mid] > target) {
                    res = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = res;
        }
        
        return ans;
    }
}