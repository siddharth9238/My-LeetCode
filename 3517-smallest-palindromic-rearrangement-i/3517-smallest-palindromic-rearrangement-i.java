class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder leftHalf = new StringBuilder();
        char middleChar = 0;
        
        // Build the lexicographically smallest left half
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            
            if (count[i] % 2 != 0) {
                middleChar = c; // Save the odd character for the center
            }
            
            // Append half of the occurrences
            for (int j = 0; j < count[i] / 2; j++) {
                leftHalf.append(c);
            }
        }
        
        // Build the full palindrome: leftHalf + middleChar + reverse(leftHalf)
        StringBuilder result = new StringBuilder(leftHalf);
        if (middleChar != 0) {
            result.append(middleChar);
        }
        result.append(new StringBuilder(leftHalf).reverse());
        
        return result.toString();
    }
}