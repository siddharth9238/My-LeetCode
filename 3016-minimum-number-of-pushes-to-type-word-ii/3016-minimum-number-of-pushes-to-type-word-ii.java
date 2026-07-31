import java.util.Arrays;

class Solution {
    public int minimumPushes(String word) {
        int[] freq = new int[26];
        
        // Count frequencies of each character
        for (char c : word.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Sort frequencies in ascending order
        Arrays.sort(freq);
        
        int totalPushes = 0;
        int distinctCount = 0;
        
        // Process characters from highest frequency to lowest
        for (int i = 25; i >= 0; i--) {
            if (freq[i] == 0) {
                break; // Stop if no more characters are present in word
            }
            
            int costMultiplier = (distinctCount / 8) + 1;
            totalPushes += freq[i] * costMultiplier;
            distinctCount++;
        }
        
        return totalPushes;
    }
}