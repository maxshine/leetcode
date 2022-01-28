import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    public int[] searchRange(int[] nums, int target) {

        int firstOccurrence = this.findBound(nums, target, true);

        if (firstOccurrence == -1) {
            return new int[]{-1, -1};
        }

        int lastOccurrence = this.findBound(nums, target, false);

        return new int[]{firstOccurrence, lastOccurrence};
    }

    private int findBound(int[] nums, int target, boolean isFirst) {
        int N = nums.length;
        int begin = 0, end = N - 1;

        while (begin <= end) {

            int mid = (begin + end) / 2;

            if (nums[mid] == target) {

                if (isFirst) {

                    // This means we found our lower bound.
                    if (mid == begin || nums[mid - 1] != target) {
                        return mid;
                    }

                    // Search on the left side for the bound.
                    end = mid - 1;

                } else {

                    // This means we found our upper bound.
                    if (mid == end || nums[mid + 1] != target) {
                        return mid;
                    }

                    // Search on the right side for the bound.
                    begin = mid + 1;
                }

            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                begin = mid + 1;
            }
        }

        return -1;
    }

    public int[] searchRange1(int[] nums, int target) {
        int first = findFirstIndex(nums, target);
        if (first != -1 && nums[first] != target) {
            return new int[] {-1, -1};
        }
        int last = findLastIndex(nums, target);
        return new int[]{first, last};
    }

    public int findFirstIndex(int []nums, int target) {
        if (nums.length == 0 || nums[0] > target || nums[nums.length-1] < target) {
            return -1;
        }
        if (nums[0] == target) {
            return 0;
        }
        int start = 0;
        int end = nums.length-1;
        while (start < end) {
            int mid = (start+end+1) / 2;
            if (nums[mid]<target) {
                start = mid;
            } else {
                end = mid-1;
            }
        }
        return start+1;
    }

    public int findLastIndex(int []nums, int target) {
        if (nums.length == 0 || nums[0] > target || nums[nums.length-1] < target) {
            return -1;
        }
        if (nums[nums.length-1] == target) {
            return nums.length-1;
        }
        int start = 0;
        int end = nums.length-1;
        while (start < end) {
            int mid = (start+end) / 2;
            if (nums[mid]>target) {
                end = mid;
            } else {
                start = mid+1;
            }
        }
        return end-1;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 6;
//        int[] nums = new int[]{3, 3};
//        int target = 2;

        int[] result = new Solution().searchRange(nums, target);
        System.out.println(toString(result));
    }

    private static String toString(int[] arr) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0)
                buf.append(",");
            buf.append(arr[i]);
        }
        buf.append("]");
        return buf.toString();
    }

}
