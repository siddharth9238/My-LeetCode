class Solution {
    public String smallestSubsequence(String s) {
        char[] chars = s.toCharArray();
        int[] lastIndex = new int[26];
        
        for (int i = 0; i < chars.length; i++) {
            lastIndex[chars[i] - 'a'] = i;
        }
        
        boolean[] seen = new boolean[26];
        char[] stack = new char[26];
        int top = -1;
        
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            int idx = c - 'a';
            
            if (seen[idx]) {
                continue;
            }
            
            while (top >= 0 && stack[top] > c && lastIndex[stack[top] - 'a'] > i) {
                seen[stack[top] - 'a'] = false;
                top--;
            }
            
            stack[++top] = c;
            seen[idx] = true;
        }
        
        return new String(stack, 0, top + 1);
    }
}