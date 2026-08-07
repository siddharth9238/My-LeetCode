class Solution {
    private static final int[] DIGIT_A = {0, 0, 1, 0, 2, 0, 1, 0, 3, 0};
    private static final int[] DIGIT_B = {0, 0, 0, 1, 0, 0, 1, 0, 0, 2}; 
    private static final int[] DIGIT_C = {0, 0, 0, 0, 0, 1, 0, 0, 0, 0}; 
    private static final int[] DIGIT_D = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}; 
    public String smallestNumber(String num, long t) {

        long tempT = t;
        int reqA = 0, reqB = 0, reqC = 0, reqD = 0;
        while (tempT % 2 == 0) { reqA++; tempT /= 2; }
        while (tempT % 3 == 0) { reqB++; tempT /= 3; }
        while (tempT % 5 == 0) { reqC++; tempT /= 5; }
        while (tempT % 7 == 0) { reqD++; tempT /= 7; }
        
        if (tempT > 1) {
            return "-1"; 
        }

        int n = num.length();
        int z = n;
        for (int i = 0; i < n; i++) {
            if (num.charAt(i) == '0') {
                z = i;
                break;
            }
        }

        int[] prefA = new int[n + 1];
        int[] prefB = new int[n + 1];
        int[] prefC = new int[n + 1];
        int[] prefD = new int[n + 1];

        for (int i = 0; i < z; i++) {
            int dig = num.charAt(i) - '0';
            prefA[i + 1] = prefA[i] + DIGIT_A[dig];
            prefB[i + 1] = prefB[i] + DIGIT_B[dig];
            prefC[i + 1] = prefC[i] + DIGIT_C[dig];
            prefD[i + 1] = prefD[i] + DIGIT_D[dig];
        }

        if (z == n) {
            if (reqA <= prefA[n] && reqB <= prefB[n] && reqC <= prefC[n] && reqD <= prefD[n]) {
                return num;
            }
        }

        int startL = Math.min(n - 1, z);
        for (int L = startL; L >= 0; L--) {
            int startDigit = num.charAt(L) - '0' + 1;
            for (int d = startDigit; d <= 9; d++) {
                int remA = Math.max(0, reqA - prefA[L] - DIGIT_A[d]);
                int remB = Math.max(0, reqB - prefB[L] - DIGIT_B[d]);
                int remC = Math.max(0, reqC - prefC[L] - DIGIT_C[d]);
                int remD = Math.max(0, reqD - prefD[L] - DIGIT_D[d]);

                int R = n - 1 - L;
                if (getMinLen(remA, remB, remC, remD) <= R) {
                    String suf = getMinString(remA, remB, remC, remD);
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, L);
                    sb.append((char) ('0' + d));
                    for (int i = 0; i < R - suf.length(); i++) {
                        sb.append('1');
                    }
                    sb.append(suf);
                    return sb.toString();
                }
            }
        }
       
        int minLenForT = getMinLen(reqA, reqB, reqC, reqD);
        int targetLen = Math.max(n + 1, minLenForT);
        String suf = getMinString(reqA, reqB, reqC, reqD);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < targetLen - suf.length(); i++) {
            sb.append('1');
        }
        sb.append(suf);
        return sb.toString();
    }

    private int getMinLen(int a, int b, int c, int d) {
        int c5 = Math.max(0, c);
        int c7 = Math.max(0, d);
        int minLen = Integer.MAX_VALUE;

        for (int c2 = 0; c2 <= 2; c2++) {
            for (int c3 = 0; c3 <= 1; c3++) {
                for (int c4 = 0; c4 <= 2; c4++) {
                    for (int c6 = 0; c6 <= 1; c6++) {
                        int remA = Math.max(0, a - (c2 + 2 * c4 + c6));
                        int remB = Math.max(0, b - (c3 + c6));
                        int c8 = (remA + 2) / 3;
                        int c9 = (remB + 1) / 2;
                        int len = c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9;
                        if (len < minLen) {
                            minLen = len;
                        }
                    }
                }
            }
        }
        return minLen;
    }

    private String getMinString(int a, int b, int c, int d) {
        int c5 = Math.max(0, c);
        int c7 = Math.max(0, d);
        int minLen = Integer.MAX_VALUE;
        String bestStr = null;

        for (int c2 = 0; c2 <= 2; c2++) {
            for (int c3 = 0; c3 <= 1; c3++) {
                for (int c4 = 0; c4 <= 2; c4++) {
                    for (int c6 = 0; c6 <= 1; c6++) {
                        int remA = Math.max(0, a - (c2 + 2 * c4 + c6));
                        int remB = Math.max(0, b - (c3 + c6));
                        int c8 = (remA + 2) / 3;
                        int c9 = (remB + 1) / 2;

                        int len = c2 + c3 + c4 + c5 + c6 + c7 + c8 + c9;
                        if (len <= minLen) {
                            StringBuilder sb = new StringBuilder();
                            for (int i = 0; i < c2; i++) sb.append('2');
                            for (int i = 0; i < c3; i++) sb.append('3');
                            for (int i = 0; i < c4; i++) sb.append('4');
                            for (int i = 0; i < c5; i++) sb.append('5');
                            for (int i = 0; i < c6; i++) sb.append('6');
                            for (int i = 0; i < c7; i++) sb.append('7');
                            for (int i = 0; i < c8; i++) sb.append('8');
                            for (int i = 0; i < c9; i++) sb.append('9');

                            String str = sb.toString();
                            if (len < minLen || bestStr == null || str.compareTo(bestStr) < 0) {
                                minLen = len;
                                bestStr = str;
                            }
                        }
                    }
                }
            }
        }
        return bestStr == null ? "" : bestStr;
    }
}