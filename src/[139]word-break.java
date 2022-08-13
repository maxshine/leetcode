import java.util.*;

// @solution-sync:begin
class Solution {
    // Mine
    public boolean wordBreakMine(String s, List<String> wordDict) {
        HashSet<String> wordSet = new HashSet<>();
        int minWordLen = Integer.MAX_VALUE;
        int maxWordLen = Integer.MIN_VALUE;
        for (String word : wordDict) {
            wordSet.add(word);
            minWordLen = Math.min(word.length(), minWordLen);
            maxWordLen = Math.max(word.length(), maxWordLen);
        }
        return helperMine(s, wordSet, 0, minWordLen, maxWordLen);
    }
    private boolean helperMine(String s, HashSet<String> wordSet, int start, int minWordLen, int maxWordLen) {
        if (start >= s.length()) {
            return true;
        }
        for (int end=minWordLen; start+end<=s.length() && end <=maxWordLen; end++) {
            if (wordSet.contains(s.substring(start, start+end)) &&
                    helperMine(s, wordSet, start+end, minWordLen, maxWordLen)) {
                return true;
            }
        }
        return false;
    }
    
    // 3  BFS
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[s.length()];
        queue.add(0);
        while (!queue.isEmpty()) {
            int start = queue.remove();
            if (visited[start]) {
                continue;
            }
            for (int end = start + 1; end <= s.length(); end++) {
                if (wordDictSet.contains(s.substring(start, end))) {
                    queue.add(end);
                    if (end == s.length()) {
                        return true;
                    }
                }
            }
            visited[start] = true;
        }
        return false;
    }

    // 4 dp
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = Arrays.asList("leet", "code");

        boolean result = new Solution().wordBreak(s, wordDict);
        System.out.println(result);
    }

}
