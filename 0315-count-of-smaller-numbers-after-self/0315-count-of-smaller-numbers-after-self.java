import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Integer[] res = new Integer[n];
        int[] bit = new int[20002];
        int offset = 10001;
        
        for (int i = n - 1; i >= 0; i--) {
            int val = nums[i] + offset;
            
            int count = 0;
            for (int j = val - 1; j > 0; j -= j & -j) {
                count += bit[j];
            }
            res[i] = count;
            
            for (int j = val; j <= 20001; j += j & -j) {
                bit[j]++;
            }
        }
        
        return Arrays.asList(res);
    }
}