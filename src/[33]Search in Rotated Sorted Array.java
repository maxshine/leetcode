class Solution {
    public int searchLinear(int[] nums, int target) {
        int pivot = 0;
        if(nums.length >= 2) {
            for (int i=1; i<nums.length; i++) {
                if (nums[i]<nums[i-1]) {
                    pivot = i-1;
                    break;
                }
            }
        }
        int idx = binarySearch(nums, 0, pivot, target);
        if (idx == -1) {
            idx = binarySearch(nums, pivot+1, nums.length-1, target);
        }
        return idx;
    }
    public int searchTwopass(int[] nums, int target) {
        int pivot = findPivot(nums, 0, nums.length-1);
        if (nums[pivot] == target) {
            return pivot;
        }
        if (pivot == 0) {
            return binarySearch(nums, 0, nums.length-1, target);
        } else if (target < nums[0]) {
            return binarySearch(nums, pivot, nums.length-1, target);
        } else {
            return binarySearch(nums, 0, pivot-1, target);
        }
    }

    public int findPivot(int[] nums, int start, int end) {
        if (nums[start]<=nums[end]) {
            return 0;
        }
        while (start <= end) {
            int pivot = (start+end) / 2;
            if (nums[pivot]>nums[pivot+1]) {
                return pivot+1;
            } else {
                if (nums[pivot]<nums[start]) {
                    end = pivot-1;
                } else {
                    start = pivot+1;
                }
            }
        }
        return 0;
    }
    public int binarySearch(int[] nums, int start, int end, int target) {
        int middle = (start+end)/2;
        while (start<=end) {
            if (nums[middle]<target) {
                start=middle+1;
            } else if(nums[middle]>target) {
                end=middle-1;
            } else {
                return middle;
            }
            middle = (start+end)/2;
        }
        return -1;
    }
    public int search(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] >= nums[start]) {
                if (target >= nums[start] && target < nums[mid]) end = mid - 1;
                else start = mid + 1;
            }
            else {
                if (target <= nums[end] && target > nums[mid]) start = mid + 1;
                else end = mid - 1;
            }
        }
        return -1;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int target = 0;
//        int[] nums = new int[]{3,1};
//        int target = 3;

        int result = new Solution().search(nums, target);
        System.out.println(result);
    }

}
