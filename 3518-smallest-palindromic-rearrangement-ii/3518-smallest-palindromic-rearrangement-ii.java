import java.math.BigInteger;

class Solution {
    private static final long LIMIT = 1_000_001L;

    public String smallestPalindrome(String s, int k) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        int[] half = new int[26];
        int len = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            len += half[i];
            if ((freq[i] & 1) == 1) {
                mid = (char) ('a' + i);
            }
        }

        // If the total distinct palindromes is less than k, return empty string
        if (countWays(half, len) < k) {
            return "";
        }

        StringBuilder left = new StringBuilder();

        // Construct the left half position by position
        for (int pos = 0; pos < len; pos++) {
            for (int ch = 0; ch < 26; ch++) {
                if (half[ch] == 0) continue;

                half[ch]--;
                long ways = countWays(half, len - pos - 1);

                if (ways >= k) {
                    left.append((char) ('a' + ch));
                    break;
                }

                k -= ways;
                half[ch]++; // Backtrack and try next character
            }
        }

        // Reconstruct full palindrome: left + mid + reverse(left)
        StringBuilder ans = new StringBuilder();
        ans.append(left);
        if (mid != 0) {
            ans.append(mid);
        }
        ans.append(new StringBuilder(left).reverse());

        return ans.toString();
    }

    private long countWays(int[] count, int total) {
        BigInteger ways = BigInteger.ONE;
        int rem = total;

        for (int c : count) {
            if (c == 0) continue;
            ways = ways.multiply(nCrBig(rem, c));
            rem -= c;

            if (ways.compareTo(BigInteger.valueOf(LIMIT)) >= 0) {
                return LIMIT;
            }
        }

        return ways.longValue();
    }

    private BigInteger nCrBig(int n, int r) {
        if (r < 0 || r > n) return BigInteger.ZERO;
        if (r == 0 || r == n) return BigInteger.ONE;
        if (r > n / 2) r = n - r;

        BigInteger res = BigInteger.ONE;
        for (int i = 1; i <= r; i++) {
            res = res.multiply(BigInteger.valueOf(n - i + 1)).divide(BigInteger.valueOf(i));
            if (res.compareTo(BigInteger.valueOf(LIMIT)) >= 0) {
                return BigInteger.valueOf(LIMIT);
            }
        }
        return res;
    }
}