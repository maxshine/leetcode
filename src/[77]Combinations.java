import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// @solution-sync:begin
class Solution {
    public List<List<Integer>> combineMine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<Integer>();
        int[] nums = new int[n];
        for (int i=0; i<n; i++) {
            nums[i] = i+1;
        }
        int start = 0;
        backtraceMine(ans, current, nums, k, start);
        return ans;
    }

    private void backtraceMine(List<List<Integer>> ans, List<Integer> current, int[] nums, int k, int start) {
        if (current.size() == k) {
            ans.add(new ArrayList<>(current));
            return;
        }
        int curr_length = current.size();
        for (int i=start; i<nums.length-(k-curr_length-1); i++) {
            current.add(nums[i]);
            backtraceMine(ans, current, nums, k, i+1);
            current.remove(current.size()-1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        // init first combination
        LinkedList<Integer> nums = new LinkedList<Integer>();
        for(int i = 1; i < k + 1; ++i)
            nums.add(i);
        nums.add(n + 1);

        List<List<Integer>> output = new ArrayList<List<Integer>>();
        int j = 0;
        while (j < k) {
            // add current combination
            output.add(new LinkedList(nums.subList(0, k)));
            // increase first nums[j] by one
            // if nums[j] + 1 != nums[j + 1]
            j = 0;
            while ((j < k) && (nums.get(j + 1) == nums.get(j) + 1))
                nums.set(j, j++ + 1);
            nums.set(j, nums.get(j) + 1);
        }
        return output;
    }
}