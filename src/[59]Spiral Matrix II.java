import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] ans = new int[n][n];
        int[][] visited = new int[n][n];
        int VISITED = -1;
        int[][] directions = new int[][] {{0,1},{1,0},{0,-1},{-1,0}};
        int currentDirection = 0;
        int directChanged = 0;
        int row = 0;
        int col = 0;
        int num = 1;
        ans[row][col]=num++;
        visited[row][col]=VISITED;
        while(directChanged < 2) {
            while (row+directions[currentDirection][0]>=0
            && row+directions[currentDirection][0]<n
            && col+directions[currentDirection][1]>=0
            && col+directions[currentDirection][1]<n
            && visited[row+directions[currentDirection][0]][col+directions[currentDirection][1]] != VISITED) {
                directChanged = 0;
                ans[row+directions[currentDirection][0]][col+directions[currentDirection][1]] = num++;
                visited[row+directions[currentDirection][0]][col+directions[currentDirection][1]] = VISITED;
                row = row + directions[currentDirection][0];
                col = col + directions[currentDirection][1];
            }
            currentDirection = (currentDirection+1) % 4;
            directChanged++;
        }
        return ans;
    }
    public int[][] generateMatrixMineBoundary(int n) {
        int[][] ans = new int[n][n];
        int left = 0;
        int right = n-1;
        int up = 0;
        int bottom = n-1;
        int limit = n*n;
        int num = 1;
        while (num<=limit) {
            for (int j=left; j<=right; j++) {
                ans[up][j] = num++;
            }
            for (int j=up+1; j<=bottom; j++) {
                ans[j][right] = num++;
            }
            if (up!=bottom) {
                for (int j=right-1; j>=left; j--) {
                    ans[bottom][j] = num++;
                }
            }
            if (left!=right) {
                for (int j=bottom-1; j>=up+1; j--) {
                    ans[j][left] = num++;
                }
            }
            left++;
            right--;
            up++;
            bottom--;
        }
        return ans;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int n = 3;

        int[][] result = new Solution().generateMatrix(n);
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

    private static String toString(int[][] arr) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0)
                buf.append(",");
            buf.append(toString(arr[i]));
        }
        buf.append("]");
        return buf.toString();
    }

}
