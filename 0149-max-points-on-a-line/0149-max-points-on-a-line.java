import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n <= 2) {
            return n;
        }
        
        int max = 2;
        
        for (int i = 0; i < n; i++) {
            Map<Long, Integer> map = new HashMap<>();
            
            for (int j = i + 1; j < n; j++) {
                int dx = points[j][0] - points[i][0];
                int dy = points[j][1] - points[i][1];
                
                if (dx == 0) {
                    dy = 1;
                } else if (dy == 0) {
                    dx = 1;
                } else {
                    if (dx < 0) {
                        dx = -dx;
                        dy = -dy;
                    }
                    int g = gcd(Math.abs(dx), Math.abs(dy));
                    dx /= g;
                    dy /= g;
                }
                
                long key = (((long) dx) << 32) | (dy & 0xFFFFFFFFL);
                int count = map.getOrDefault(key, 1) + 1;
                map.put(key, count);
                
                if (count > max) {
                    max = count;
                }
            }
        }
        
        return max;
    }
    
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}