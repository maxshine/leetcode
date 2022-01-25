class Solution {
    public void nextPermutation(int[] nums) {
        int k = nums.length-2;
        while (k>=0 && nums[k]>=nums[k+1]) {
            k--;
        }
        if (k>=0) {
            int j = nums.length-1;
            while (nums[j]<=nums[k]) {
                j--;
            }
            swap(nums, j, k);
        }
        reverse(nums, k+1, nums.length-1);
    }
    
    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public void reverse(int[] nums, int i, int j) {
        while(i<j) {
            swap(nums, i++, j--);
        }
    }
}