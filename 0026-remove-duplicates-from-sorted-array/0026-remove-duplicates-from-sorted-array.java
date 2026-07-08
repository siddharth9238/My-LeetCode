class Solution {
    public int removeDuplicates(int[] nums) {
        // The array is guaranteed to have at least 1 element based on constraints,
        // so the first element is always unique.
        int insertIndex = 1;
        
        for (int i = 1; i < nums.length; i++) {
            // If the current element is different from the previous one, it's unique
            if (nums[i] != nums[i - 1]) {
                nums[insertIndex] = nums[i];
                insertIndex++;
            }
        }
        
        // insertIndex now represents the exact length of the unique elements prefix
        return insertIndex;
    }
}