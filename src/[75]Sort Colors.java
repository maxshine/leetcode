class Solution {
    public void sortColors(int[] nums) {
        int redIdx = -1;
        int blueIdx = nums.length;
        int i=0;
        while(i<blueIdx) {
            if (nums[i] == 0) {
                // red
                redIdx++;
                int temp = nums[redIdx];
                nums[redIdx] = nums[i];
                nums[i] = temp;
                i++;
            } else if (nums[i] == 2) {
                // blue
                blueIdx--;
                int temp = nums[blueIdx];
                nums[blueIdx] = nums[i];
                nums[i] = temp;
            } else {
                i++;
            }
        }
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        new Solution().sortColors(nums);
    }

}
