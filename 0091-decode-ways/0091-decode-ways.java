class Solution {
    public int numDecodings(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] == '0') {
            return 0;
        }
        
        int n = chars.length;
        int prev2 = 1;
        int prev1 = 1;
        
        for (int i = 1; i < n; i++) {
            int current = 0;
            
            if (chars[i] != '0') {
                current = prev1;
            }
            
            if (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] <= '6')) {
                current += prev2;
            }
            
            if (current == 0) {
                return 0;
            }
            
            prev2 = prev1;
            prev1 = current;
        }
        
        return prev1;
    }
}