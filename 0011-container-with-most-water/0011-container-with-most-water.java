class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        
        while (left < right) {
            int hLeft = height[left];
            int hRight = height[right];
            
            // Inline min calculation to avoid Math.min stack overhead
            int minHeight = hLeft < hRight ? hLeft : hRight;
            int currentArea = minHeight * (right - left);
            
            if (currentArea > maxArea) {
                maxArea = currentArea;
            }
            
            // Fast-forward pointers to skip useless calculations
            if (hLeft < hRight) {
                while (left < right && height[left] <= hLeft) {
                    left++;
                }
            } else {
                while (left < right && height[right] <= hRight) {
                    right--;
                }
            }
        }
        
        return maxArea;
    }
}