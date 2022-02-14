import java.util.HashSet;

class Solution {
    public int totalNQueens(int n) {
        return backtrace(new HashSet<Integer>(), new HashSet<Integer>(), new HashSet<Integer>(), 0, n);
    }

    private int backtrace(HashSet<Integer> cols, HashSet<Integer> diagonals, HashSet<Integer> antiDiagonals, int row, int size) {
        if (row == size) {
            return 1;
        }
        int solution = 0;
        for (int col=0; col<size; col++) {
            int diagonal = row - col;
            int antiDiagonal = row + col;
            if (cols.contains(col) || diagonals.contains(diagonal) || antiDiagonals.contains(antiDiagonal)) {
                continue;
            }
            cols.add(col);
            diagonals.add(diagonal);
            antiDiagonals.add(antiDiagonal);
            solution += backtrace(cols, diagonals, antiDiagonals, row+1, size);
            cols.remove(col);
            diagonals.remove(diagonal);
            antiDiagonals.remove(antiDiagonal);
        }
        return solution;
    }
}