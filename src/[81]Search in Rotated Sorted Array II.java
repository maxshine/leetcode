class Solution {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) {
            return false;
        }
        int start = 0;
        int end = nums.length-1;
        while (start <= end) {
            int mid = start + (end-start)/2;
            if (nums[mid] == target) {
                return true;
            }
            if (nums[mid] == nums[start]) {
                start++;
                continue;
            }
            boolean pivotInFirst = nums[start]<=nums[mid];
            boolean targetInFirst = nums[start]<=target;
            if (pivotInFirst ^ targetInFirst) {
                if (targetInFirst) {
                    end = mid-1;
                } else {
                    start = mid+1;
                }
            } else {
                if (target > nums[mid]) {
                    start = mid+1;
                } else {
                    end = mid-1;
                }
            }
        }
        return false;
    }
}