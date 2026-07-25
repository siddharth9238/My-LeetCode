class Solution {
    public int maxProduct(int n) {
        int max1 = 0; // To store the largest digit
        int max2 = 0; // To store the second largest digit
        
        while (n > 0) {
            int digit = n % 10;
            
            // Update the top two maximum digits
            if (digit >= max1) {
                max2 = max1;
                max1 = digit;
            } else if (digit > max2) {
                max2 = digit;
            }
            
            n /= 10; // Remove the last digit
        }
        
        return max1 * max2;
    }
}