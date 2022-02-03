import java.util.*;
import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // container to hold the final combinations
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();

        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int candidate : candidates) {
            if (counter.containsKey(candidate))
                counter.put(candidate, counter.get(candidate) + 1);
            else
                counter.put(candidate, 1);
        }

        // convert the counter table to a list of (num, count) tuples
        List<int[]> counterList = new ArrayList<>();
        counter.forEach((key, value) -> {
            counterList.add(new int[]{key, value});
        });

        backtrack(comb, target, 0, counterList, results);
        return results;
    }

    private void backtrack(LinkedList<Integer> comb,
                           int remain, int curr,
                           List<int[]> counter,
                           List<List<Integer>> results) {

        if (remain <= 0) {
            if (remain == 0) {
                // make a deep copy of the current combination.
                results.add(new ArrayList<Integer>(comb));
            }
            return;
        }

        for (int nextCurr = curr; nextCurr < counter.size(); ++nextCurr) {
            int[] entry = counter.get(nextCurr);
            Integer candidate = entry[0], freq = entry[1];

            if (freq <= 0)
                continue;

            // add a new element to the current combination
            comb.addLast(candidate);
            counter.set(nextCurr, new int[]{candidate, freq - 1});

            // continue the exploration with the updated combination
            backtrack(comb, remain - candidate, nextCurr, counter, results);

            // backtrack the changes, so that we can try another candidate
            counter.set(nextCurr, new int[]{candidate, freq});
            comb.removeLast();
        }
    }
    
    public List<List<Integer>> combinationSum2Index(int[] candidates, int target) {
        List<List<Integer>> results = new ArrayList<>();
        LinkedList<Integer> comb = new LinkedList<>();

        Arrays.sort(candidates);

        backtrack2(candidates, comb, target, 0, results);
        return results;
    }

    private void backtrack2(int[] candidates, LinkedList<Integer> comb,
                           Integer remain, Integer curr,
                           List<List<Integer>> results) {
        if (remain == 0) {
            // copy the current combination to the final list.
            results.add(new ArrayList<Integer>(comb));
            return;
        }

        for (int nextCurr = curr; nextCurr < candidates.length; ++nextCurr) {
            if (nextCurr > curr && candidates[nextCurr] == candidates[nextCurr - 1])
                continue;

            Integer pick = candidates[nextCurr];
            // optimization: early stopping
            if (remain - pick < 0)
                break;

            comb.addLast(pick);
            backtrack2(candidates, comb, remain - pick, nextCurr + 1, results);
            comb.removeLast();
        }
    }
    
    // mine
    public List<List<Integer>> combinationSum21(int[] candidates, int target) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        Arrays.sort(candidates);
        if (target >= candidates[0]) {
            for (int i=0; i<candidates.length; i++) {
                for (List<Integer> sub : backtrace1(candidates, i+1, target-candidates[i])) {
                    sub.add(candidates[i]);
                    Collections.sort(sub);
                    ans.add(sub);
                }
                while(i<=candidates.length-2 && candidates[i+1] == candidates[i]) {
                    i++;
                }
            }
        }
        return ans;
    }

    public List<List<Integer>>  backtrace1(int[] candidates, int start, int target) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (target == 0) {
            ans.add(new LinkedList<>());
        } else if (start <candidates.length && target >= candidates[start]) {
            for (int i=start; i<candidates.length; i++) {
                for (List<Integer> sub : backtrace1(candidates, i+1, target-candidates[i])) {
                    sub.add(candidates[i]);
                    ans.add(sub);
                }
                while(i<=candidates.length-2 && candidates[i+1] == candidates[i]) {
                    i++;
                }
            }
        }
        return ans;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] candidates = new int[]{10, 1, 2, 7, 6, 1, 5};
        int target = 8;

        List<List<Integer>> result = new Solution().combinationSum2(candidates, target);
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
