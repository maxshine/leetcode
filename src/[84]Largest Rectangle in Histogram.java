import java.util.Stack;
class SegTreeNode {
    int start;
    int end;
    int min;
    SegTreeNode left;
    SegTreeNode right;
    SegTreeNode(int start, int end) {
        this.start = start;
        this.end = end;
        left = right = null;
    }
};

class Solution {
    public int largestRectangleAreaBrutal(int[] heights) {
        int ans = 0;
        for (int i=0; i<heights.length; i++) {
            for (int j=i; j<heights.length; j++) {
                int height = heights[i];
                for (int k=i; k<=j; k++) {
                    height = Math.min(height, heights[k]);
                }
                ans = Math.max(ans, height*(j-i+1));
            }
        }
        return ans;
    }
    public int largestRectangleAreaBrutal2(int[] heights) {
        int ans = 0;
        for (int i=0; i<heights.length; i++) {
            int height = Integer.MAX_VALUE;
            for (int j=i; j<heights.length; j++) {
                height = Math.min(height, heights[j]);
                ans = Math.max(ans, height*(j-i+1));
            }
        }
        return ans;
    }
    public int largestRectangleAreaDivide(int[] heights) {
        return calculateLargestRectangleArea(heights, 0, heights.length-1);
    }

    private int calculateLargestRectangleArea(int[] heights, int start, int end) {
        if (start == end) {
            return heights[start];
        } else if (start > end) {
            return 0;
        }
        int minIdx = start;
        for (int k=start; k<=end; k++) {
            if (heights[k]<heights[minIdx]) {
                minIdx = k;
            }
        }
        return Math.max(heights[minIdx]*(end-start+1), Math.max(
                calculateLargestRectangleArea(heights, start, minIdx-1),
                calculateLargestRectangleArea(heights,minIdx+1, end)
        ));
    }

    int largestRectangleAreaStack(int[] heights) {
        Stack<Integer> stk =  new Stack<Integer> ();
        stk.push(-1);
        int max_area = 0;
        for (int i = 0; i < heights.length; i++) {
            while (stk.lastElement() != -1 && heights[stk.lastElement()] >= heights[i]) {
                int current_height = heights[stk.lastElement()];
                stk.pop();
                int current_width = i - stk.lastElement() - 1;
                max_area = Math.max(max_area, current_height * current_width);
            }
            stk.push(i);
        }
        while (stk.lastElement() != -1) {
            int current_height = heights[stk.lastElement()];
            stk.pop();
            int current_width = heights.length - stk.lastElement() - 1;
            max_area = Math.max(max_area, current_height * current_width);
        }
        return max_area;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] heights = new int[]{2, 1, 5, 6, 2, 3};

        int result = new Solution().largestRectangleArea(heights);
        System.out.println(result);
    }

}
