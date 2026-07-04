class Solution {
    public int minCut(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        
        char[] c = s.toCharArray();
        int n = c.length;
        int[] cuts = new int[n + 1];
        
        for (int i = 0; i <= n; i++) {
            cuts[i] = i - 1;
        }
        
        for (int i = 0; i < n; i++) {
            int left = i;
            int right = i;
            while (left >= 0 && right < n && c[left] == c[right]) {
                if (cuts[left] + 1 < cuts[right + 1]) {
                    cuts[right + 1] = cuts[left] + 1;
                }
                left--;
                right++;
            }
            
            left = i;
            right = i + 1;
            while (left >= 0 && right < n && c[left] == c[right]) {
                if (cuts[left] + 1 < cuts[right + 1]) {
                    cuts[right + 1] = cuts[left] + 1;
                }
                left--;
                right++;
            }
        }
        
        return cuts[n];
    }
}