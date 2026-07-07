function searchRange(nums: number[], target: number): number[] {
    let left = 0;
    let right = nums.length - 1;
    let start = -1;

    // 1. Search for the absolute left boundary
    while (left <= right) {
        const mid = (left + right) >> 1; // Native 32-bit integer division
        
        if (nums[mid] === target) {
            start = mid;
            right = mid - 1; // Keep crushing leftwards
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    // Fast-fail if the target doesn't exist at all
    if (start === -1) {
        return [-1, -1];
    }

    // 2. Search for the absolute right boundary
    // Optimization: We only need to search from 'start' to the end of the array
    left = start;
    right = nums.length - 1;
    let end = start;

    while (left <= right) {
        const mid = (left + right) >> 1;
        
        if (nums[mid] === target) {
            end = mid;
            left = mid + 1; // Keep pushing rightwards
        } else if (nums[mid] < target) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }

    return [start, end];
}