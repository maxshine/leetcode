import java.util.*;

// @solution-sync:begin
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int i=0; i< nums.length; i++) {
            if(nums[i] > 0) {
                break;
            }
            if (i == 0 || nums[i - 1] != nums[i]) {
                twoSum(nums, i, result);
            }
        }
        return result;
    }

    void twoSum2(int[] nums, int i, List<List<Integer>> result) {
        HashSet<Integer> seen = new HashSet<Integer>();
        for (int j = i + 1; j < nums.length; ++j) {
            int complement = -nums[i] - nums[j];
            if (seen.contains(complement)) {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(nums[i]);
                temp.add(complement);
                temp.add(nums[j]);
                result.add(temp);
                while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                    ++j;
                }
            }
            seen.add(nums[j]);
        }
    }

    private void twoSum(int[] nums, int i, List<List<Integer>> result) {
        int lo = i+1;
        int hi = nums.length-1;
        while (lo < hi) {
            int sum = nums[i] + nums[lo] + nums[hi];
            if (sum > 0) {
                hi--;
            } else if (sum < 0) {
                lo++;
            } else {
                List<Integer> temp = new ArrayList<Integer>();
                temp.add(nums[i]);
                temp.add(nums[lo++]);
                temp.add(nums[hi--]);
                result.add(temp);
                while(lo < hi && nums[lo] == nums[lo - 1]) {
                    lo++;
                }
            }
        }
    }
}