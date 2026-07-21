import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        
        // Fast-Fail Constraint Check
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }
        
        char[] chars = s.toCharArray();
        // A valid IP always requires exactly 3 dots added to the original characters
        char[] path = new char[chars.length + 3];
        
        backtrack(result, chars, path, 0, 0, 0);
        
        return result;
    }
    
    private void backtrack(List<String> result, char[] chars, char[] path, int charIdx, int pathIdx, int segment) {
        // Base Case: We have placed exactly 4 segments
        if (segment == 4) {
            if (charIdx == chars.length) {
                result.add(new String(path)); // Only instantiate a String on a valid leaf node
            }
            return;
        }
        
        int val = 0;
        for (int i = charIdx; i < charIdx + 3 && i < chars.length; i++) {
            // Prune invalid leading zeros
            if (i != charIdx && chars[charIdx] == '0') {
                break;
            }
            
            // Mathematically evaluate the segment size
            val = val * 10 + (chars[i] - '0');
            if (val > 255) {
                break;
            }
            
            // Insert character directly into our static buffer
            path[pathIdx + (i - charIdx)] = chars[i];
            
            // Insert the dot separator (unless it is the 4th segment)
            if (segment < 3) {
                path[pathIdx + (i - charIdx) + 1] = '.';
            }
            
            // Advance the path index appropriately depending on if a dot was added
            int nextPathIdx = pathIdx + (i - charIdx) + 1 + (segment < 3 ? 1 : 0);
            
            backtrack(result, chars, path, i + 1, nextPathIdx, segment + 1);
        }
    }
}