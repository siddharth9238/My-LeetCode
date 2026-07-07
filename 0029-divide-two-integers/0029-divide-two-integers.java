class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;

        if (dividend > 0) dividend = -dividend;
        if (divisor > 0) divisor = -divisor;

        int quotient = 0;
        int halfMin = Integer.MIN_VALUE >> 1;

        while (dividend <= divisor) {
            int temp = divisor;
            int multiple = -1;

            while (temp >= halfMin && dividend <= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }

            dividend -= temp;
            quotient += multiple;
        }

        return sign == -1 ? quotient : -quotient;
    }
}