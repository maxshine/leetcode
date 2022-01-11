import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return ksum(nums, target, 0, 4);
    }

    public List<List<Integer>> ksum(int[] nums, int target, int start, int k) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        if (start == nums.length) {
            return result;
        }
        int average = target / k;
        if (nums[start] > average || nums[nums.length-1] < average) {
            return result;
        }
        if (k == 2) {
            return twoSum(nums, target, start);
        }
        for (int i=start; i<nums.length; i++) {
            if (i == start || nums[i - 1] != nums[i]) {
                List<List<Integer>> subsets = ksum(nums, target-nums[i], i+1, k-1);
                for (List<Integer> subset : subsets) {
                    subset.add(0, nums[i]);
                    result.add(subset);
                }
            }
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> result = new LinkedList<List<Integer>>();
        int lo = start, hi = nums.length-1;
        while (lo < hi) {
            int sum = nums[lo] + nums[hi];
            if (sum < target || (lo > start && nums[lo] == nums[lo - 1])) {
                lo++;
            } else if (sum > target || (hi < nums.length-1 && nums[hi] == nums[hi+1])) {
                hi--;
            } else {
                List<Integer> temp = new LinkedList<Integer>();
                temp.add(nums[lo++]);
                temp.add(nums[hi--]);
                result.add(temp);
            }
        }
        return result;
    }

}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        // int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int[] nums = new int[]{2, 2, 2, 2, 2};
        int target = 8;

        List<List<Integer>> result = new Solution().fourSum(nums, target);
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
