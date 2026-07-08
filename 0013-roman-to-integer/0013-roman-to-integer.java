class Solution {
    public int romanToInt(String s) {
        int sum = 0;
        int prevValue = 0;
        
        // Traverse backwards to easily handle subtraction cases
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = 0;
            
            // Primitive switch for 0ms lookup (bypassing HashMap)
            switch (s.charAt(i)) {
                case 'I': currentValue = 1; break;
                case 'V': currentValue = 5; break;
                case 'X': currentValue = 10; break;
                case 'L': currentValue = 50; break;
                case 'C': currentValue = 100; break;
                case 'D': currentValue = 500; break;
                case 'M': currentValue = 1000; break;
            }
            
            // If current numeral is smaller than the previous one, it's a subtraction case
            if (currentValue < prevValue) {
                sum -= currentValue;
            } else {
                sum += currentValue;
            }
            
            prevValue = currentValue;
        }
        
        return sum;
    }
}