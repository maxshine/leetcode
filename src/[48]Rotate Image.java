class Solution {
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }
    
    public void transpose(int[][] matrix) {
        for (int i=0; i<matrix.length; i++) {
            for (int j=i+1; j<matrix[i].length; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
    }
    
    public void reflect(int[][] matrix) {
        for (int i=0; i<matrix.length; i++) {
            int left = 0;
            int right = matrix[i].length-1;
            while (left<right) {
                int tmp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = tmp;
                left++;
                right--;
            }
        }
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
        new Solution().rotate(matrix);
    }

}
