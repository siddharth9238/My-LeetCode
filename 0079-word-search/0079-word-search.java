class Solution {
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        int m = board.length;
        int n = board[0].length;
        
        int[] boardFreq = new int[128];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boardFreq[board[i][j]]++;
            }
        }
        
        int[] wordFreq = new int[128];
        for (char c : w) {
            wordFreq[c]++;
        }
        
        for (int i = 0; i < 128; i++) {
            if (wordFreq[i] > boardFreq[i]) {
                return false;
            }
        }
        
        if (boardFreq[w[0]] > boardFreq[w[w.length - 1]]) {
            for (int i = 0; i < w.length / 2; i++) {
                char temp = w[i];
                w[i] = w[w.length - 1 - i];
                w[w.length - 1 - i] = temp;
            }
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == w[0] && dfs(board, w, i, j, 0)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    private boolean dfs(char[][] board, char[] w, int i, int j, int index) {
        if (index == w.length) {
            return true;
        }
        
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != w[index]) {
            return false;
        }
        
        board[i][j] ^= 256;
        
        boolean found = dfs(board, w, i + 1, j, index + 1) ||
                        dfs(board, w, i - 1, j, index + 1) ||
                        dfs(board, w, i, j + 1, index + 1) ||
                        dfs(board, w, i, j - 1, index + 1);
        
        board[i][j] ^= 256;
        
        return found;
    }
}