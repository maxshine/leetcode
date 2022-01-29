class Solution {
    public int searchInsert(int[] nums, int target) {
        int start = 0;
        int end = nums.length-1;
        while (start <=end) {
            int mid = start + (end - start) / 2;;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                end = mid-1;
            } else {
                start = mid+1;
            }
        }
        return start;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 7;

        int result = new Solution().searchInsert(nums, target);
        System.out.println(result);
    }

}
