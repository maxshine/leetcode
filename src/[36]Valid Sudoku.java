class Solution {
    public boolean isValidSudokuArray(char[][] board) {
        int N = 9;
        int rowsSet[][] = new int[N][N];
        int colsSet[][] = new int[N][N];
        int boxesSet[][] = new int[N][N];
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (board[i][j] == '.')  {
                    continue;
                }
                int num = board[i][j]-'0';
                if (rowsSet[i][num-1] != 0) {
                    return false;
                }
                rowsSet[i][num-1]=1;
                if (colsSet[j][num-1] != 0) {
                    return false;
                }
                colsSet[j][num-1]=1;

                if (boxesSet[i/3*3+j/3][num-1] != 0) {
                    return false;
                }
                boxesSet[i/3*3+j/3][num-1]=1;
            }
        }
        return true;
    }

    public boolean isValidSudoku(char[][] board) {
        int N = 9;

        // Use a binary number to record previous occurrence
        int[] rows = new int[N];
        int[] cols = new int[N];
        int[] boxes = new int[N];

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                // Check if the position is filled with number
                if (board[r][c] == '.') {
                    continue;
                }
                int val = board[r][c] - '0';
                int pos = 1 << (val - 1);

                // Check the row
                if ((rows[r] & pos) > 0) {
                    return false;
                }
                rows[r] |= pos;

                // Check the column
                if ((cols[c] & pos) > 0) {
                    return false;
                }
                cols[c] |= pos;

                // Check the box
                int idx = (r / 3) * 3 + c / 3;
                if ((boxes[idx] & pos) > 0) {
                    return false;
                }
                boxes[idx] |= pos;
            }
        }
        return true;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
//        char[][] board = new char[][]{
//                new char[]{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
//                new char[]{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
//                new char[]{'.', '9', '8', '.', '.', '.', '.', '6', '.'},
//                new char[]{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
//                new char[]{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
//                new char[]{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
//                new char[]{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
//                new char[]{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
//                new char[]{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
//        };
        char[][] board = new char[][]{
                new char[]{'.','8','7','6','5','4','3','2','1'},
                new char[]{'2','.','.','.','.','.','.','.','.'},
                new char[]{'3','.','.','.','.','.','.','.','.'},
                new char[]{'4','.','.','.','.','.','.','.','.'},
                new char[]{'5','.','.','.','.','.','.','.','.'},
                new char[]{'6','.','.','.','.','.','.','.','.'},
                new char[]{'7','.','.','.','.','.','.','.','.'},
                new char[]{'8','.','.','.','.','.','.','.','.'},
                new char[]{'9','.','.','.','.','.','.','.','.'}
        };

        boolean result = new Solution().isValidSudoku(board);
        System.out.println(result);
    }

}
