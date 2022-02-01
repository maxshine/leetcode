class Solution {
    int n = 3;
    int N = n * n;
    int[][] rows = new int[N][N+1];
    int[][] cols = new int[N][N+1];
    int[][] boxes = new int[N][N+1];
    
    char[][] board;
    boolean sudokuSolved = false;
    public void placeNumber(int d, int row, int col) {
        int idx = (row/n*n+col/n);
        rows[row][d]=1;
        cols[col][d]=1;
        boxes[idx][d]=1;
        board[row][col] = Character.forDigit(d, 10);
    }

    public void removeNumber(int d, int row, int col) {
        int idx = (row/n*n+col/n);
        rows[row][d]=0;
        cols[col][d]=0;
        boxes[idx][d]=0;
        board[row][col] = '.';
    }
    
    public boolean couldPlace(int d, int row, int col) {
        int idx = (row/n*n+col/n);
        return rows[row][d] + cols[col][d] + boxes[idx][d] == 0;
    }
    
    public void placeNextNumber(int row, int col) {
        if (row == N-1 && col == N-1) {
            sudokuSolved = true;
        } else {
            if (col == N-1) {
                backtrace(row+1, 0);
            } else {
                backtrace(row, col+1);
            }
        }
    }
    
    public void backtrace(int row, int col) {
        if (board[row][col] == '.') {
            for (int d=1; d<=N; d++) {
                if (couldPlace(d, row, col)) {
                    placeNumber(d, row, col);
                    placeNextNumber(row, col);
                    if (!sudokuSolved) {
                        removeNumber(d, row, col);
                    }
                }
            }
        } else {
            placeNextNumber(row, col);
        }
    }
    
    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (board[i][j] != '.') {
                    int d = Character.getNumericValue(board[i][j]);
                    placeNumber(d, i, j);
                }
            }
        }
        backtrace(0, 0);
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        char[][] board = new char[][]{
                new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        new Solution().solveSudoku(board);
    }

}
