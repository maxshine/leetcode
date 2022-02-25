class Solution {
    public int uniquePathsDP2D(int m, int n) {
        int[][] ans = new int[m][n];
        for (int i=m-1; i>=0; i--) {
            for (int j=n-1; j>=0; j--) {
                if (i==m-1 && j==n-1) {
                    ans[i][j] = 1;
                } else if (i<m-1 && j==n-1) {
                    ans[i][j] = ans[i+1][j];
                } else if (i==m-1 && j<n-1) {
                    ans[i][j] = ans[i][j+1];
                } else {
                    ans [i][j] = ans[i+1][j] + ans[i][j+1];
                }
            }
        }
        return ans[0][0];
    }
    public int uniquePathsRecursive(int m, int n) {
        if (m==1 || n==1) {
            return 1;
        }
        return uniquePathsRecursive(m-1, n) + uniquePathsRecursive(m, n-1);
    }
}