import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// @solution-sync:begin
class Solution {
    
    // Mine
    public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> ans = new LinkedList<>();
        List<Integer> prev = null;
        for (int i=0; i<numRows; i++) {
            if (i == 0) {
                ans.add(new ArrayList<>() {{add(1);}});
                prev = ans.get(0);
                continue;
            }
            if (i == 1) {
                ans.add(new ArrayList<>() {{add(1);add(1);}});
                prev = ans.get(1);
                continue;
            }
            List<Integer> current = new ArrayList<>();
            int size = i+1;
            for (int j=0; j<size; j++) {
                if (j == 0) {
                    current.add(1);
                } else if (j == size-1) {
                    current.add(1);
                } else {
                    current.add(prev.get(j-1) + prev.get(j));
                }
            }
            ans.add(current);
            prev = current;
        }
        return ans;
    }

    // DP
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        // Base case; first row is always [1].
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for (int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum-1);

            // The first row element is always 1.
            row.add(1);

            // Each triangle element (other than the first and last of each row)
            // is equal to the sum of the elements above-and-to-the-left and
            // above-and-to-the-right.
            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j-1) + prevRow.get(j));
            }

            // The last row element is always 1.
            row.add(1);

            triangle.add(row);
        }

        return triangle;
    }
}