class Solution {
    int[][][] memo;
    char[] c1;
    char[] c2;

    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        memo = new int[n][n][n + 1];
        c1 = s1.toCharArray();
        c2 = s2.toCharArray();
        
        return solve(0, 0, n);
    }

    private boolean solve(int i, int j, int len) {
        if (memo[i][j][len] != 0) {
            return memo[i][j][len] == 1;
        }

        boolean isEqual = true;
        for (int k = 0; k < len; k++) {
            if (c1[i + k] != c2[j + k]) {
                isEqual = false;
                break;
            }
        }
        if (isEqual) {
            memo[i][j][len] = 1;
            return true;
        }

        int[] count = new int[26];
        for (int k = 0; k < len; k++) {
            count[c1[i + k] - 'a']++;
            count[c2[j + k] - 'a']--;
        }
        for (int k = 0; k < 26; k++) {
            if (count[k] != 0) {
                memo[i][j][len] = -1;
                return false;
            }
        }

        for (int k = 1; k < len; k++) {
            if (solve(i, j, k) && solve(i + k, j + k, len - k)) {
                memo[i][j][len] = 1;
                return true;
            }
            
            if (solve(i, j + len - k, k) && solve(i + k, j, len - k)) {
                memo[i][j][len] = 1;
                return true;
            }
        }

        memo[i][j][len] = -1;
        return false;
    }
}