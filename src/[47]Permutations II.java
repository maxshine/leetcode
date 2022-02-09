import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

// @solution-sync:begin
class Solution {
    public List<List<Integer>> permuteUniqueByCount(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();

        // count the occurrence of each number
        HashMap<Integer, Integer> counter = new HashMap<>();
        for (int num : nums) {
            if (!counter.containsKey(num))
                counter.put(num, 0);
            counter.put(num, counter.get(num) + 1);
        }

        LinkedList<Integer> comb = new LinkedList<>();
        this.backtrackByCount(comb, nums.length, counter, results);
        return results;
    }

    protected void backtrackByCount(
            LinkedList<Integer> comb,
            Integer N,
            HashMap<Integer, Integer> counter,
            List<List<Integer>> results) {

        if (comb.size() == N) {
            // make a deep copy of the resulting permutation,
            // since the permutation would be backtracked later.
            results.add(new ArrayList<Integer>(comb));
            return;
        }

        for (Map.Entry<Integer, Integer> entry : counter.entrySet()) {
            Integer num = entry.getKey();
            Integer count = entry.getValue();
            if (count == 0)
                continue;
            // add this number into the current combination
            comb.addLast(num);
            counter.put(num, count - 1);

            // continue the exploration
            backtrackByCount(comb, N, counter, results);

            // revert the choice for the next exploration
            comb.removeLast();
            counter.put(num, count);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> results = new LinkedList<List<Integer>>();
        List<Integer> numList = new ArrayList<Integer>();
        for (int n : nums) {
            numList.add(n);
        }

        backtrace(nums.length, numList, results, 0);
        return results;

    }

    private void backtrace(int length, List<Integer> numList, List<List<Integer>> results, int start) {
        if (start == length) {
            results.add(new ArrayList<Integer>(numList));
        }

        for (int i=start; i<numList.size(); i++) {
            if (i>start && numList.get(i) == numList.get(start)) {
                continue;
            }
            Collections.swap(numList, start, i);
            backtrace(length, numList, results, start+1);
            Collections.swap(numList, start, i);
        }
    }
}