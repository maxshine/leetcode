class Solution {
    public int minPathSum(int[][] grid) {
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if(i == grid.length - 1 && j != grid[0].length - 1)
                    grid[i][j] = grid[i][j] +  grid[i][j + 1];
                else if(j == grid[0].length - 1 && i != grid.length - 1)
                    grid[i][j] = grid[i][j] + grid[i + 1][j];
                else if(j != grid[0].length - 1 && i != grid.length - 1)
                    grid[i][j] = grid[i][j] + Math.min(grid[i + 1][j],grid[i][j + 1]);
            }
        }
        return grid[0][0];
    }
    public int minPathSumDP1D(int[][] grid) {
        int[] dp = new int[grid[0].length];
        for (int i = grid.length - 1; i >= 0; i--) {
            for (int j = grid[0].length - 1; j >= 0; j--) {
                if(i == grid.length - 1 && j != grid[0].length - 1)
                    dp[j] = grid[i][j] +  dp[j + 1];
                else if(j == grid[0].length - 1 && i != grid.length - 1)
                    dp[j] = grid[i][j] + dp[j];
                else if(j != grid[0].length - 1 && i != grid.length - 1)
                    dp[j] = grid[i][j] + Math.min(dp[j], dp[j + 1]);
                else
                    dp[j] = grid[i][j];
            }
        }
        return dp[0];
    }
    public int minPathSumDP2D(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] ans = new int[rows][cols];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                ans[i][j] = Integer.MAX_VALUE;
            }
        }
        ans[rows-1][cols-1] = grid[rows-1][cols-1];
        for (int i=rows-1; i>=0; i--) {
            for (int j=cols-1; j>=0; j--) {
                if (j == cols-1 && i<rows-1) {
                    ans[i][j] = grid[i][j]+ans[i+1][j];
                } else if (i == rows-1 && j<cols-1) {
                    ans[i][j] = grid[i][j]+ans[i][j+1];
                } else if (i<rows-1 && j<cols-1) {
                    ans[i][j] = grid[i][j]+Math.min(ans[i+1][j], ans[i][j+1]);
                }
            }
        }
        return ans[0][0];
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[][] grid = new int[][]{
                new int[]{1, 3, 1},
                new int[]{1, 5, 1},
                new int[]{4, 2, 1}
        };

        int result = new Solution().minPathSum(grid);
        System.out.println(result);
    }

}
