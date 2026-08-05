import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            graph.get(inv[0]).add(inv[1]);
        }

        
        boolean[] isSuspicious = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        
        isSuspicious[k] = true;
        queue.offer(k);
        
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (int neighbor : graph.get(curr)) {
                if (!isSuspicious[neighbor]) {
                    isSuspicious[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!isSuspicious[u] && isSuspicious[v]) {
                
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        
        List<Integer> remaining = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!isSuspicious[i]) {
                remaining.add(i);
            }
        }
        
        return remaining;
    }
}