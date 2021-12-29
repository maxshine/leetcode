import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int result = 0;
        int left = -1;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i=0; i<s.length(); i++) {
            Character c = s.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, i);
            } else {
                left =  Math.max(left, map.get(c));
                map.put(c, i);
            }
            result = Math.max(i-left, result);
        }
        return result;
    }
}