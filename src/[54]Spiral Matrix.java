import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    private int[][] directions = new int[][] {{0,1}, {1,0}, {0,-1}, {-1,0}};
    public List<Integer> spiralOrder(int[][] matrix) {
        int VISITED = 101;
        int rows = matrix.length;
        int columns = matrix[0].length;
        // Four directions that we will move: right, down, left, up.
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // Initial direction: moving right.
        int currentDirection = 0;
        // The number of times we change the direction.
        int changeDirection = 0;
        // Current place that we are at is (row, col).
        // row is the row index; col is the column index.
        int row = 0;
        int col = 0;
        // Store the first element and mark it as visited.
        List<Integer> result = new ArrayList<>();
        result.add(matrix[0][0]);
        matrix[0][0] = VISITED;
        while (changeDirection < 2) {
            while (row + directions[currentDirection][0] >= 0 &&
                    row + directions[currentDirection][0] < rows &&
                    col + directions[currentDirection][1] >= 0 &&
                    col + directions[currentDirection][1] < columns &&
                    matrix[row + directions[currentDirection][0]]
                            [col + directions[currentDirection][1]] != VISITED) {
                // Reset this to 0 since we did not break and change the direction.
                changeDirection = 0;
                // Calculate the next place that we will move to.
                row = row + directions[currentDirection][0];
                col = col + directions[currentDirection][1];
                result.add(matrix[row][col]);
                matrix[row][col] = VISITED;
            }
            // Change our direction.
            currentDirection = (currentDirection + 1) % 4;
            // Increment change_direction because we changed our direction.
            changeDirection++;
        }
        return result;
    }
    public List<Integer> spiralOrderBoundary(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int rows = matrix.length;
        int columns = matrix[0].length;
        int up = 0;
        int left = 0;
        int right = columns - 1;
        int down = rows - 1;

        while (result.size() < rows * columns) {
            // Traverse from left to right.
            for (int col = left; col <= right; col++) {
                result.add(matrix[up][col]);
            }
            // Traverse downwards.
            for (int row = up + 1; row <= down; row++) {
                result.add(matrix[row][right]);
            }
            // Make sure we are now on a different row.
            if (up != down) {
                // Traverse from right to left.
                for (int col = right - 1; col >= left; col--) {
                    result.add(matrix[down][col]);
                }
            }
            // Make sure we are now on a different column.
            if (left != right) {
                // Traverse upwards.
                for (int row = down - 1; row > up; row--) {
                    result.add(matrix[row][left]);
                }
            }
            left++;
            right--;
            up++;
            down--;
        }

        return result;
    }

    public List<Integer> spiralOrderMine(int[][] matrix) {
        int dIdx = 0;
        int dSize = directions.length;
        int up = 0;
        int left = 0;
        int rowDirect = directions[dIdx][0];
        int colsDirect = directions[dIdx][1];
        int bottom = matrix.length-1;
        int right = matrix[0].length-1;
        List<Integer> ans = new ArrayList<Integer>();

        while(left<=right&&up<=bottom) {
            if (rowDirect==0 && colsDirect==1) {
                int temp = left;
                for (; left<=right; left++) {
                    ans.add(matrix[up][left]);
                }
                up++;
                left = temp;
            } else if (rowDirect==1 && colsDirect==0) {
                int temp = up;
                for (; up<=bottom; up++) {
                    ans.add(matrix[up][right]);
                }
                right--;
                up = temp;
            } else if (rowDirect==0 && colsDirect==-1) {
                int temp = right;
                for (; right>=left; right--) {
                    ans.add(matrix[bottom][right]);
                }
                bottom--;
                right=temp;
            } else if (rowDirect==-1 && colsDirect==0) {
                int temp = bottom;
                for (; bottom>=up; bottom--) {
                    ans.add(matrix[bottom][left]);
                }
                left++;
                bottom = temp;
            }
            dIdx = (++dIdx % dSize);
            rowDirect = directions[dIdx][0];
            colsDirect = directions[dIdx][1];
        }
        return ans;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                new int[]{1, 2, 3},
                new int[]{4, 5, 6},
                new int[]{7, 8, 9}
        };

        List<Integer> result = new Solution().spiralOrder(matrix);
        System.out.println(listToString(result));
    }

    private static String listToString(List<Integer> list) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0)
                buf.append(",");
            buf.append(list.get(i));
        }
        buf.append("]");
        return buf.toString();
    }

}
