class Solution {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;
        int left_max = 0, right_max = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= left_max) {
                    left_max = height[left];
                } else {
                    ans += (left_max - height[left]);
                }
                ++left;
            }
            else {
                if (height[right] >= right_max) {
                    right_max = height[right];
                } else {
                    ans += (right_max - height[right]);
                }
                --right;
            }
        }
        return ans;
    }

    public int trapDP(int[] height) {
        if(height.length == 0)
            return 0;
        int ans = 0;
        int size = height.length;
        int[] left_max = new int[height.length];
        int[] right_max = new int[height.length];
        left_max[0] = height[0];
        for (int i = 1; i < size; i++) {
            left_max[i] = Math.max(height[i], left_max[i - 1]);
        }
        right_max[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            right_max[i] = Math.max(height[i], right_max[i + 1]);
        }
        for (int i = 1; i < size - 1; i++) {
            ans += Math.min(left_max[i], right_max[i]) - height[i];
        }
        return ans;
    }

    public int trapMine(int[] height) {
        int leftMax = 0;
        int rightMax = 0;
        int ans = 0;
        int[] leftMaxArray = new int[height.length];
        int[] rightMaxArray = new int[height.length];
        for (int i=0; i<height.length; i++) {
            if (height[i]>leftMax) {
                leftMax = height[i];
            }
            leftMaxArray[i] = leftMax;
        }

        for (int i=height.length-1; i>=0; i--) {
            if (height[i]>rightMax) {
                rightMax = height[i];
            }
            rightMaxArray[i] = rightMax;
        }
        for (int i=0; i<height.length; i++) {
            int temp = Math.min(leftMaxArray[i], rightMaxArray[i]);
            ans += temp - height[i];
        }
        return ans;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};

        int result = new Solution().trap(height);
        System.out.println(result);
    }

}
