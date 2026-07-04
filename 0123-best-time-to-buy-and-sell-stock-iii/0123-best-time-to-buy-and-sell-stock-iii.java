class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MAX_VALUE;
        int sell1 = 0;
        int buy2 = Integer.MAX_VALUE;
        int sell2 = 0;
        
        for (int p : prices) {
            if (p < buy1) {
                buy1 = p;
            }
            if (p - buy1 > sell1) {
                sell1 = p - buy1;
            }
            if (p - sell1 < buy2) {
                buy2 = p - sell1;
            }
            if (p - buy2 > sell2) {
                sell2 = p - buy2;
            }
        }
        
        return sell2;
    }
}