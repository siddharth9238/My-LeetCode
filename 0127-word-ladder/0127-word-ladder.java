import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        
        beginSet.add(beginWord);
        endSet.add(endWord);
        
        int step = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextSet = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();
                for (int i = 0; i < chs.length; i++) {
                    char oldChar = chs[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == oldChar) {
                            continue;
                        }
                        
                        chs[i] = c;
                        String target = String.valueOf(chs);
                        
                        if (endSet.contains(target)) {
                            return step + 1;
                        }
                        
                        if (dict.contains(target)) {
                            nextSet.add(target);
                            dict.remove(target);
                        }
                    }
                    chs[i] = oldChar;
                }
            }
            
            beginSet = nextSet;
            step++;
        }

        return 0;
    }
}