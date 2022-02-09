import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new LinkedList<List<Integer>>();
        List<Integer> numList = new ArrayList<Integer>();
        for (int n : nums) {
            numList.add(n);
        }
        
        backtrace(nums.length, numList, results, 0);
        return results;

    }

    private void backtrace(int length, List<Integer> numList, List<List<Integer>> results, int start) {
        if (start == length) {
            results.add(new ArrayList<Integer>(numList));
        }
        
        for (int i=start; i<numList.size(); i++) {
            Collections.swap(numList, start, i);
            backtrace(length, numList, results, start+1);
            Collections.swap(numList, start, i);
        }
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};

        List<List<Integer>> result = new Solution().permute(nums);
        System.out.println(toString(result));
    }

    private static String listToString(List<Integer> list) {
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

    private static String toString(List<List<Integer>> list) {
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
