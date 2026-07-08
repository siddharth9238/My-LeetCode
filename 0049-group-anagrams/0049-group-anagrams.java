import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        
        Map<String, List<String>> map = new HashMap<>();
        
        for (String s : strs) {
            // Allocate a strict 26-slot primitive array for the alphabet
            char[] count = new char[26];
            
            // Tally character frequencies directly via ASCII pointer offsets
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
            }
            
            // Cast the frequency array directly to a String to act as our hash key
            String key = new String(count);
            
            // Optimized map insertion (bypassing computeIfAbsent for slight performance gain)
            List<String> list = map.get(key);
            if (list == null) {
                list = new ArrayList<>();
                map.put(key, list);
            }
            list.add(s);
        }
        
        // Wrap the map values in a new ArrayList to satisfy the return type
        return new ArrayList<>(map.values());
    }
}