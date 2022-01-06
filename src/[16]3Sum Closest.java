import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int diff = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i=0; i<nums.length-2 && diff != 0; i++) {
            int lo = i+1;
            int hi = nums.length-1;
            while (lo < hi) {
                int sum = nums[i] + nums[lo] + nums[hi];
                if(Math.abs(sum-target) < Math.abs(diff)) {
                    diff = target - sum;
                }
                if (sum < target) {
                    lo++;
                } else {
                    hi--;
                }
            }
        }
        return target - diff;
    }
    public int threeSumClosest2(int[] num, int target) {
        int result = num[0] + num[1] + num[num.length - 1];
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int sum = num[i] + num[start] + num[end];
                if (sum > target) {
                    end--;
                } else {
                    start++;
                }
                if (Math.abs(sum - target) < Math.abs(result - target)) {
                    result = sum;
                }
            }
        }
        return result;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 2, 1, -4};
        nums = new int[]{0,1,2};
        int target = 1;

        int result = new Solution().threeSumClosest(nums, target);
        System.out.println(result);
    }

}
