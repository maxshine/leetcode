import java.util.*;

// @solution-sync:begin
class Solution {
    public List<List<String>> groupAnagramsByHash(String[] strs) {
        if (strs.length == 0) { return new ArrayList<>();}
        Map<String, List> ans = new HashMap<String, List>();
        for (String s : strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String key = String.valueOf(ca);
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<String>());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) { return new ArrayList<>();}
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) {
                count[c-'a']++;
            }
            StringBuilder sb = new StringBuilder("");
            for (int i : count) {
                sb.append('#').append(i);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList<String>());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }
}