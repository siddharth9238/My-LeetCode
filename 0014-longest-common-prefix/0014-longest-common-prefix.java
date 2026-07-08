class Solution {
    public String longestCommonPrefix(String[] strs) {
        // The first string acts as our baseline reference
        String first = strs[0];
        
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            
            // Vertically scan the rest of the strings at the current index 'i'
            for (int j = 1; j < strs.length; j++) {
                // If we reach the end of a shorter string, or find a mismatch, we are done
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return first.substring(0, i);
                }
            }
        }
        
        // If we make it through the entire first string, the whole first string is the prefix
        return first;
    }
}