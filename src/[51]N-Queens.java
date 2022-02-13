import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    private int size;
    private List<List<String>> ans = new ArrayList<List<String>>();
    public List<List<String>> solveNQueens(int n) {
        size = n;
        char[][] board = new char[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                board[i][j] = '.';
            }
        }
        backtrace(board, new HashSet<Integer>(), new HashSet<Integer>(), new HashSet<Integer>(), 0);
        return ans;
    }
    

    private void backtrace(char[][] board, HashSet<Integer> cols, HashSet<Integer> diagonals, HashSet<Integer> antiDiagonals, int row) {
        if (row == size) {
            ans.add(createBoard(board));
            return;
        }
        for (int i=0; i<size; i++) {
            int col = i;
            int diagonal = row-col;
            int antiDiagonal = row+col;
            if (cols.contains(col) || diagonals.contains(diagonal) || antiDiagonals.contains(antiDiagonal)) {
                continue;
            }
            board[row][col] = 'Q';
            cols.add(col);
            diagonals.add(diagonal);
            antiDiagonals.add(antiDiagonal);
            backtrace(board, cols, diagonals, antiDiagonals, row+1);
            board[row][col] = '.';
            cols.remove(col);
            diagonals.remove(diagonal);
            antiDiagonals.remove(antiDiagonal);
        }
    }

    private List<String> createBoard(char[][] board) {
        List<String> solution = new ArrayList<String>();
        for (int i=0; i<size; i++) {
            solution.add(new String(board[i]));
        }
        return solution;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int n = 4;

        List<List<String>> result = new Solution().solveNQueens(n);
        System.out.println(toString(result));
    }

    private static String listToString(List<String> list) {
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

    private static String toString(List<List<String>> list) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0)
                buf.append(",");
            buf.append(listToString(list.get(i)));
        }
        buf.append("]");
        return buf.toString();
    }

}
