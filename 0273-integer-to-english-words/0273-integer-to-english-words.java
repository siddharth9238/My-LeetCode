class Solution {
    private static final String[] LESS_THAN_20 = {
        "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", 
        "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", 
        "Seventeen", "Eighteen", "Nineteen"
    };
    
    private static final String[] TENS = {
        "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
    };

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        
        StringBuilder sb = new StringBuilder();
        
        if (num >= 1000000000) {
            helper(num / 1000000000, sb);
            sb.append("Billion ");
            num %= 1000000000;
        }
        if (num >= 1000000) {
            helper(num / 1000000, sb);
            sb.append("Million ");
            num %= 1000000;
        }
        if (num >= 1000) {
            helper(num / 1000, sb);
            sb.append("Thousand ");
            num %= 1000;
        }
        if (num > 0) {
            helper(num, sb);
        }
        
        return sb.toString().trim();
    }
    
    private void helper(int num, StringBuilder sb) {
        if (num >= 100) {
            sb.append(LESS_THAN_20[num / 100]).append(" Hundred ");
            num %= 100;
        }
        if (num >= 20) {
            sb.append(TENS[num / 10]).append(" ");
            num %= 10;
        }
        if (num > 0) {
            sb.append(LESS_THAN_20[num]).append(" ");
        }
    }
}