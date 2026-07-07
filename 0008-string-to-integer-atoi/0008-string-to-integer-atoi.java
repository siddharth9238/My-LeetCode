class Solution {
    public int myAtoi(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        
        char[] c = s.toCharArray();
        int i = 0;
        
        while (i < n && c[i] == ' ') {
            i++;
        }
        
        if (i == n) {
            return 0;
        }
        
        int sign = 1;
        if (c[i] == '-') {
            sign = -1;
            i++;
        } else if (c[i] == '+') {
            i++;
        }
        
        int res = 0;
        int limit = Integer.MAX_VALUE / 10;
        
        while (i < n && c[i] >= '0' && c[i] <= '9') {
            int digit = c[i] - '0';
            
            if (res > limit || (res == limit && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            
            res = res * 10 + digit;
            i++;
        }
        
        return res * sign;
    }
}