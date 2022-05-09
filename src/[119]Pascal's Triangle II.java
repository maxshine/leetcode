import java.util.*;

// @solution-sync:begin
class Solution {
    // Mine
    public List<Integer> getRowMine(int rowIndex) {
        List<Integer> prev = new LinkedList<>() {{ add(1); }};
        if (rowIndex == 0) {
            return prev;
        }
        List<Integer> current = new LinkedList<>();;
        for (int i=1; i<=rowIndex; i++) {
            current = new LinkedList<>();
            current.add(1);
            for (int j=1; j<i; j++) {
                current.add(prev.get(j-1)+prev.get(j));
            }
            current.add(1);
            prev = current;
        }
        return current;
    }

    // 2
    public List<Integer> getRowSingleList(int rowIndex) {
        List<Integer> ans = new ArrayList<>(rowIndex+1);
        for (int i=0; i<rowIndex+1; i++) {
            ans.add(1);
        }

        for (int i = 1; i < rowIndex; i++)
            for (int j = i; j > 0; j--)
                ans.set(j, ans.get(j)+ans.get(j-1));  // ans[j] = ans[j-1] + ans[j]

        return ans;
    }

    //3
    public List<Integer> getRow(int rowIndex) {
        Integer[] ans = new Integer[rowIndex+1];
        for (int i=0; i<rowIndex+1; i++) {
            ans[i] = 1;
        }

        for (int i = 1; i < rowIndex; i++)
            for (int j = i; j > 0; j--)
                ans[j] = ans[j-1] + ans[j];  // ans[j] = ans[j-1] + ans[j]

        return Arrays.asList(ans);
    }
}