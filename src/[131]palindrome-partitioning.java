import java.util.LinkedList;
import java.util.List;

// @solution-sync:begin
class Solution {
    
    boolean dp[][];
    // 1
    public List<List<String>> partition(String s) {
        dp = new boolean[s.length()][s.length()];
        List<List<String>> ans = new LinkedList<>();
        List<String> curr = new LinkedList<>();
        dfsDP(s, 0, s.length()-1, curr, ans);
        return ans;
    }

    private void dfs(String s, int start, int end, List<String> curr, List<List<String>> ans) {
        if (start >= s.length()) {
            ans.add(new LinkedList<>(curr));
        }
        for (int currentEnd = start; currentEnd<=end; currentEnd++) {
            if (isPalindrome(s, start, currentEnd)) {
                curr.add(s.substring(start, currentEnd+1));
                dfs(s, currentEnd+1, end, curr, ans);
                curr.remove(curr.size()-1);
            }
        }
    }

    private void dfsDP(String s, int start, int end, List<String> curr, List<List<String>> ans) {
        if (start >= s.length()) {
            ans.add(new LinkedList<>(curr));
        }
        for (int currentEnd = start; currentEnd<=end; currentEnd++) {
            if (s.charAt(start) == s.charAt(currentEnd) && (currentEnd - start <= 2 || dp[start+1][currentEnd-1])) {
                dp[start][currentEnd] = true;
                curr.add(s.substring(start, currentEnd+1));
                dfs(s, currentEnd+1, end, curr, ans);
                curr.remove(curr.size()-1);
            }
        }
    }

    public boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}