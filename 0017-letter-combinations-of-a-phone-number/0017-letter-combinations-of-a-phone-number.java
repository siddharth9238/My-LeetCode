import java.util.ArrayList;
import java.util.List;

class Solution {
    // Statically allocated 2D primitive array for instantaneous lookup
    private static final char[][] MAP = {
        {}, // 0
        {}, // 1
        {'a', 'b', 'c'}, // 2
        {'d', 'e', 'f'}, // 3
        {'g', 'h', 'i'}, // 4
        {'j', 'k', 'l'}, // 5
        {'m', 'n', 'o'}, // 6
        {'p', 'q', 'r', 's'}, // 7
        {'t', 'u', 'v'}, // 8
        {'w', 'x', 'y', 'z'}  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        int len = digits.length();
        
        if (len == 0) {
            return res;
        }
        
        char[] dig = digits.toCharArray();
        char[] path = new char[len];
        
        dfs(dig, 0, path, res);
        
        return res;
    }

    private void dfs(char[] dig, int index, char[] path, List<String> res) {
        if (index == dig.length) {
            res.add(new String(path));
            return;
        }
        
        // Fast-path ASCII math to find the corresponding array row
        char[] letters = MAP[dig[index] - '0'];
        
        for (int i = 0; i < letters.length; i++) {
            path[index] = letters[i]; // Overwrite in place, no concatenation
            dfs(dig, index + 1, path, res);
        }
    }
}