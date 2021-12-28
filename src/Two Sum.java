import java.util.HashMap;
import java.util.Map;

class Solution {
    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    public int[] twoSum(int[] nums, int target) {
        int[] result = {-1, -1};
        for (int i=0; i<nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                result[0] = map.get(target - nums[i]);
                result[1] = i;
            }
        }
        return result;
    }
}