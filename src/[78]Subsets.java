import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

// @solution-sync:begin
class Solution {
    public List<List<Integer>> subsetsMine(int[] nums) {
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i=0; i<=nums.length; i++) {
            List<Integer> current = new ArrayList<Integer>();
            backtraceMine(ans, current, nums, i, 0);
        }
        return ans;
    }

    private void backtraceMine(List<List<Integer>> ans, List<Integer> current, int[] nums, int i, int start) {
        int currLength = current.size();
        if (currLength == i) {
            ans.add(new LinkedList<Integer>(current));
            return;
        }
        for (int j=start; j<nums.length-(i-currLength-1); j++) {
            current.add(nums[j]);
            backtraceMine(ans, current, nums, i, j+1);
            current.remove(current.size()-1);
        }
    }

    public List<List<Integer>> subsetsBitwise(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        int n = nums.length;

        for (int i = (int)Math.pow(2, n); i < (int)Math.pow(2, n + 1); ++i) {
            // generate bitmask, from 0..00 to 1..11
            String bitmask = Integer.toBinaryString(i).substring(1);

            // append subset corresponding to that bitmask
            List<Integer> curr = new ArrayList();
            for (int j = 0; j < n; ++j) {
                if (bitmask.charAt(j) == '1') curr.add(nums[j]);
            }
            output.add(curr);
        }
        return output;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> output = new ArrayList();
        output.add(new ArrayList<Integer>());

        for (int num : nums) {
            List<List<Integer>> newSubsets = new ArrayList();
            for (List<Integer> curr : output) {
                newSubsets.add(new ArrayList<Integer>(curr){{add(num);}});
            }
            for (List<Integer> curr : newSubsets) {
                output.add(curr);
            }
        }
        return output;
    }
}