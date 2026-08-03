class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        
        int dp1 = 0, dp2 = 0, dp3 = 0; 
        
        for (int i = n - 1; i >= 0; i--) {
            int maxScore = Integer.MIN_VALUE;
            int sum = 0;

            sum += stoneValue[i];
            maxScore = Math.max(maxScore, sum - dp1);
            
            if (i + 1 < n) {
                sum += stoneValue[i + 1];
                maxScore = Math.max(maxScore, sum - dp2);
            }
            
            // Option 3: Take 3 stones
            if (i + 2 < n) {
                sum += stoneValue[i + 2];
                maxScore = Math.max(maxScore, sum - dp3);
            }
            
            
            dp3 = dp2;
            dp2 = dp1;
            dp1 = maxScore; 
        }
        
        
        if (dp1 > 0) {
            return "Alice";
        } else if (dp1 < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}