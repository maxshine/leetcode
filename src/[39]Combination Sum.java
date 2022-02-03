import java.util.*;
import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    protected void backtrack(
            int remain,
            LinkedList<Integer> comb,
            int start, int[] candidates,
            List<List<Integer>> results) {

        if (remain == 0) {
            // make a deep copy of the current combination
            results.add(new ArrayList<Integer>(comb));
            return;
        } else if (remain < 0) {
            // exceed the scope, stop exploration.
            return;
        }

        for (int i = start; i < candidates.length; ++i) {
            // add the number into the combination
            comb.add(candidates[i]);
            this.backtrack(remain - candidates[i], comb, i, candidates, results);
            // backtrack, remove the number from the combination
            comb.removeLast();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        LinkedList<Integer> comb = new LinkedList<Integer>();

        this.backtrack(target, comb, 0, candidates, results);
        return results;
    }
    
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        Arrays.sort(candidates);
        if (target >= candidates[0]) {
            for (int i=0; i<candidates.length; i++) {
                for (List<Integer> sub : backtrace1(candidates, i, target-candidates[i])) {
                    sub.add(candidates[i]);
                    Collections.sort(sub);
                    ans.add(sub);
                }
            }
        }
        return ans;
    }

    public List<List<Integer>>  backtrace1(int[] candidates, int start, int target) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (target == 0) {
            ans.add(new LinkedList<>());
        } else if (target >= candidates[start]) {
            for (int i=start; i<candidates.length; i++) {
                for (List<Integer> sub : backtrace1(candidates, i, target-candidates[i])) {
                    sub.add(candidates[i]);
                    ans.add(sub);
                }
            }
        }
        return ans;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;

        List<List<Integer>> result = new Solution().combinationSum(candidates, target);
        System.out.println(toString(result));
    }

    private static String listToString(List<Integer> list) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0)
                buf.append(",");
            buf.append(list.get(i));
        }
        buf.append("]");
        return buf.toString();
    }

    private static String toString(List<List<Integer>> list) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0)
                buf.append(",");
            buf.append(listToString(list.get(i)));
        }
        buf.append("]");
        return buf.toString();
    }

}
