class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        int[][] ans = new int[rows][cols];

        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        ans[0][0] = 1;
        for (int i=1; i<rows; i++) {
            ans[i][0] = (obstacleGrid[i][0] == 0 && ans[i - 1][0] == 1) ? 1 : 0;
        }
        for (int i=1; i<cols; i++) {
            ans[0][i] = (obstacleGrid[0][i]==0 && ans[0][i-1]==1) ? 1 : 0;
        }
        for (int i=1; i<rows; i++) {
            for (int j=1; j<cols; j++) {
                if (obstacleGrid[i][j] == 1) {
                    ans[i][j] = 0;
                } else {
                    ans[i][j] = ans[i-1][j] + ans[i][j-1];
                }
            }
        }
        return ans[rows-1][cols-1];
    }
}