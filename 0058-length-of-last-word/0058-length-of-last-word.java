class Solution {
    public int lengthOfLastWord(String s) {
        int length = 0;
        
        // Traverse the string from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) != ' ') {
                // If it's a character, increase the word length
                length++;
            } else if (length > 0) {
                // If we hit a space AND we've already started counting a word, we're done
                break;
            }
        }
        
        return length;
    }
}