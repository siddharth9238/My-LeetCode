import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        if (num == null || num.length() == 0) {
            return result;
        }
        
        char[] digits = num.toCharArray();
        char[] path = new char[num.length() * 2];
        
        backtrack(result, digits, path, 0, 0, 0, 0, target);
        
        return result;
    }

    private void backtrack(List<String> result, char[] digits, char[] path, int pathLen, int index, long eval, long prevOperand, int target) {
        if (index == digits.length) {
            if (eval == target) {
                result.add(new String(path, 0, pathLen));
            }
            return;
        }

        long current = 0;
        int opPos = pathLen;
        
        if (index != 0) {
            pathLen++;
        }

        for (int i = index; i < digits.length; i++) {
            if (i != index && digits[index] == '0') {
                break;
            }

            current = current * 10 + (digits[i] - '0');
            path[pathLen++] = digits[i];

            if (index == 0) {
                backtrack(result, digits, path, pathLen, i + 1, current, current, target);
            } else {
                path[opPos] = '+';
                backtrack(result, digits, path, pathLen, i + 1, eval + current, current, target);

                path[opPos] = '-';
                backtrack(result, digits, path, pathLen, i + 1, eval - current, -current, target);

                path[opPos] = '*';
                backtrack(result, digits, path, pathLen, i + 1, eval - prevOperand + prevOperand * current, prevOperand * current, target);
            }
        }
    }
}