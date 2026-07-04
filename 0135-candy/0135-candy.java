class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) {
            return 0;
        }
        
        int totalCandies = 1;
        int up = 0;
        int down = 0;
        int peak = 0;
        
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                up++;
                down = 0;
                peak = up;
                totalCandies += 1 + up;
            } else if (ratings[i] == ratings[i - 1]) {
                up = 0;
                down = 0;
                peak = 0;
                totalCandies += 1;
            } else {
                up = 0;
                down++;
                totalCandies += 1 + down + (peak >= down ? -1 : 0);
            }
        }
        
        return totalCandies;
    }
}