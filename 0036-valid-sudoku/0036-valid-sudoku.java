class Solution {
    public boolean isValidSudoku(char[][] board) {
        // A single array to hold rows (0-8), cols (9-17), and boxes (18-26)
        int[] seen = new int[27];
        
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                
                if (c != '.') {
                    // Convert character to a bitmask (e.g., '3' becomes 1 << 3)
                    int mask = 1 << (c - '0');
                    
                    int colIdx = 9 + j;
                    int boxIdx = 18 + (i / 3) * 3 + (j / 3);
                    
                    // Bitwise AND to check if the digit's bit is already flipped
                    if ((seen[i] & mask) != 0 || 
                        (seen[colIdx] & mask) != 0 || 
                        (seen[boxIdx] & mask) != 0) {
                        return false; 
                    }
                    
                    // Bitwise OR to flip the bit and mark the digit as seen
                    seen[i] |= mask;
                    seen[colIdx] |= mask;
                    seen[boxIdx] |= mask;
                }
            }
        }
        
        return true;
    }
}