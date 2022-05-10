import java.util.*;

// @solution-sync:begin
class Solution {
    // 1
    public int minimumTotalInPlace(List<List<Integer>> triangle) {
        for (int row = 1; row < triangle.size(); row++) {
            for (int col = 0; col <= row; col++) {
                int smallestAbove = Integer.MAX_VALUE;
                if (col > 0) {
                    smallestAbove = triangle.get(row - 1).get(col - 1);
                }
                if (col < row) {
                    smallestAbove = Math.min(smallestAbove, triangle.get(row - 1).get(col));
                }
                int path = smallestAbove + triangle.get(row).get(col);
                triangle.get(row).set(col, path);
            }
        }
        return Collections.min(triangle.get(triangle.size() - 1));
    }
    // 2
    public int minimumTotalAuxPlace(List<List<Integer>> triangle) {
        List<Integer> prevRow = triangle.get(0);
        for (int row = 1; row < triangle.size(); row++) {
            List<Integer> currRow = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                int smallestAbove = Integer.MAX_VALUE;
                if (col > 0) {
                    smallestAbove = prevRow.get(col - 1);
                }
                if (col < row) {
                    smallestAbove = Math.min(smallestAbove, prevRow.get(col));
                }
                currRow.add(smallestAbove + triangle.get(row).get(col));
            }
            prevRow = currRow;
        }
        return Collections.min(prevRow);
    }
    
    //3
    public int minimumTotalUpsideDown(List<List<Integer>> triangle) {
        for (int row = triangle.size() - 2; row >= 0; row--) {
            for (int col = 0; col <= row; col++) {
                int bestBelow = Math.min(
                        triangle.get(row + 1).get(col),
                        triangle.get(row + 1).get(col + 1));
                triangle.get(row).set(col, bestBelow + triangle.get(row).get(col));
            }
        }
        return triangle.get(0).get(0);
    }

    public int minimumTotalUpsideDownAuxPlace(List<List<Integer>> triangle) {
        List<Integer> belowRow = triangle.get(triangle.size() - 1);
        for (int row = triangle.size() - 2; row >= 0; row--) {
            List<Integer> currRow = new ArrayList<>();
            for (int col = 0; col <= row; col++) {
                int bestBelow = Math.min(belowRow.get(col), belowRow.get(col + 1));
                currRow.add(bestBelow + triangle.get(row).get(col));
            }
            belowRow = currRow;
        }
        return belowRow.get(0);
    }
    
    // 5
    private Map<String, Integer> memoTable;
    private List<List<Integer>> triangle;

    private int minPath(int row, int col) {
        String params = row + ":" + col;
        if (memoTable.containsKey(params)) {
            return memoTable.get(params);
        }
        int path = triangle.get(row).get(col);
        if (row < triangle.size() - 1) {
            path += Math.min(minPath(row + 1, col), minPath(row + 1, col + 1));
        }
        memoTable.put(params, path);
        return path;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        this.triangle = triangle;
        memoTable = new HashMap<>();
        return minPath(0, 0);
    }
}