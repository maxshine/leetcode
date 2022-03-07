class Solution {
    public boolean searchMatrixMine(int[][] matrix, int target) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int start = 0;
        int end = rows*columns - 1;
        while (start <= end) {
            int middle = (start+end) / 2;
            int x = middle / columns;
            int y = middle % columns;
            if (target > matrix[x][y]) {
                start = middle + 1;
            } else if (target < matrix[x][y]) {
                end = middle - 1;
            } else {
                return true;
            }
        }
        return false;
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0)
            return false;

        int n = matrix[0].length;
        // binary search
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement)
                return true;
            else {
                if (target < pivotElement)
                    right = pivotIdx - 1;
                else
                    left = pivotIdx + 1;
            }
        }
        return false;
    }
}