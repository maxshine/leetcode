import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

// @solution-sync:begin
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> ans = new LinkedList<Integer>();
        int sequenceLength = 1 << n;
        for (int i = 0; i < sequenceLength; i++) {
            int num = i ^ i >> 1;
            ans.add(num);
        }
        return ans;
    }
    
    public List<Integer> grayCodeIteration(int n) {
        List<Integer> ans = new LinkedList<Integer>();
        ans.add(0);
        for (int i = 1; i <= n; i++) {
            int previousSequenceLength = ans.size();
            int mask = 1 << (i - 1);
            for (int j = previousSequenceLength - 1; j >= 0; j--) {
                ans.add(mask + ans.get(j));
            }
        }
        return ans;
    }

    public List<Integer> grayCodeRecursive2(int n) {
        List<Integer> ans = new LinkedList<Integer>();
        backtraceRecursive2(ans, n);
        return ans;
    }
    int nextNum = 0;

    void backtraceRecursive2(List<Integer> result, int n) {
        if (n == 0) {
            result.add(nextNum);
            return;
        }
        backtraceRecursive2(result, n - 1);
        // Flip the bit at (n - 1)th position from right
        nextNum = nextNum ^ (1 << (n - 1));
        backtraceRecursive2(result, n - 1);
    }

    public List<Integer> grayCodeRecursive(int n) {
        List<Integer> ans = new LinkedList<Integer>();
        backtraceRecursive(ans, n);
        return ans;
    }

    void backtraceRecursive(List<Integer> result, int n) {
        if (n == 0) {
            result.add(0);
            return;
        }
        // we derive the n bits sequence from the (n - 1) bits sequence.
        backtraceRecursive(result, n - 1);
        int currentSequenceLength = result.size();
        // Set the bit at position n - 1 (0 indexed) and assign it to mask.
        int mask = 1 << (n - 1);
        for (int i = currentSequenceLength - 1; i >= 0; i--) {
            // mask is used to set the (n - 1)th bit from the LSB of all the numbers present in the current sequence.
            result.add(result.get(i) | mask);
        }
        return;
    }

    public List<Integer> grayCodeBacktrace(int n) {
        List<Integer> ans = new LinkedList<Integer>();
        HashSet<Integer> isPresent = new HashSet<Integer>();
        ans.add(0);
        isPresent.add(0);
        backtrace(ans, isPresent, n);
        return ans;
    }

    private boolean backtrace(List<Integer> ans, HashSet<Integer> isPresent, int bits) {
        if (ans.size() == 1<<bits) {
            return true;
        }
        int current = ans.get(ans.size()-1);
        for (int i=0; i<bits; i++) {
            int temp = current ^ 1<<i;
            if (isPresent.contains(temp)) {
                continue;
            }
            isPresent.add(temp);
            ans.add(temp);
            if (backtrace(ans, isPresent, bits)) {
                return true;
            }
            isPresent.remove(temp);
            ans.remove(ans.size()-1);
        }
        return false;
    }
}