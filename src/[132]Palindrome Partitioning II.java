import java.util.ArrayList;
import java.util.List;

class Solution {
    // 1 Backtracing
    public int minCutBacktrace(String s) {
        return findMinimumCutBacktrace(s, 0, s.length() - 1, s.length() - 1);
    }

    int findMinimumCutBacktrace(String s, int start, int end, int minimumCut) {
        // base condition, no cut needed for an empty substring or palindrome substring.
        if (start == end || isPalindrome(s, start, end)) {
            return 0;
        }
        for (int currentEndIndex = start; currentEndIndex <= end; currentEndIndex++) {
            // find result for substring (start, currentEnd) if it is palindrome
            if (isPalindrome(s, start, currentEndIndex)) {
                minimumCut = Math.min(
                        minimumCut,
                        1 + findMinimumCutBacktrace(s, currentEndIndex + 1, end, minimumCut));
            }
        }
        return minimumCut;
    }

    boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }
        }
        return true;
    }

    // expand
    int minCut(String s) {
        List<Integer> cutsDp = new ArrayList<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            cutsDp.add(i);
        }
        for (int mid = 0; mid < s.length(); mid++) {
            // check for odd length palindrome around mid index
            findMinimumCuts(mid, mid, cutsDp, s);
            // check for even length palindrome around mid index
            findMinimumCuts(mid - 1, mid, cutsDp, s);
        }
        return cutsDp.get(cutsDp.size()-1);
    }

    void findMinimumCuts(int startIndex, int endIndex, List<Integer> cutsDp,
                         String s) {
        for (int start = startIndex, end = endIndex;
             start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end);
             start--, end++) {
            int newCut = start == 0 ? 0 : cutsDp.get(start - 1) + 1;
            cutsDp.set(end, Math.min(cutsDp.get(end), newCut));
        }
    }
}