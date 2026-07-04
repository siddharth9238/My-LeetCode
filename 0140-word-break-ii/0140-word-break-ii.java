import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private List<String> result = new ArrayList<>();
    private Set<String> dict;
    private boolean[] possible;

    public List<String> wordBreak(String s, List<String> wordDict) {
        dict = new HashSet<>(wordDict);
        possible = new boolean[s.length() + 1];
        
        for (int i = 0; i <= s.length(); i++) {
            possible[i] = true;
        }
        
        backtrack(s, 0, new StringBuilder());
        return result;
    }

    private boolean backtrack(String s, int start, StringBuilder currentPath) {
        if (start == s.length()) {
            result.add(currentPath.toString());
            return true;
        }

        if (!possible[start]) {
            return false;
        }

        boolean found = false;
        int originalLen = currentPath.length();

        for (int end = start + 1; end <= s.length(); end++) {
            String word = s.substring(start, end);
            
            if (dict.contains(word)) {
                if (originalLen > 0) {
                    currentPath.append(" ");
                }
                currentPath.append(word);
                
                if (backtrack(s, end, currentPath)) {
                    found = true;
                }
                
                currentPath.setLength(originalLen);
            }
        }

        possible[start] = found;
        return found;
    }
}