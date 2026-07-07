/**
 Do not return anything, modify nums in-place instead.
 */
function nextPermutation(nums: number[]): void {
    const n = nums.length;
    if (n <= 1) return;

    let i = n - 2;
    
    // 1. Find the first decreasing element from the right (the pivot)
    while (i >= 0 && nums[i] >= nums[i + 1]) {
        i--;
    }

    if (i >= 0) {
        // 2. Find the element just larger than nums[i] from the right
        let j = n - 1;
        while (nums[j] <= nums[i]) {
            j--;
        }
        
        // Inline swap to avoid function call overhead
        const temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 3. Reverse the suffix from i + 1 to the end
    let left = i + 1;
    let right = n - 1;
    
    while (left < right) {
        // Inline swap
        const temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
        
        left++;
        right--;
    }
}