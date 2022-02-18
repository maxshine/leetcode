import java.lang.StringBuilder;

// @solution-sync:begin
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        // init data
        int newStart = newInterval[0], newEnd = newInterval[1];
        int idx = 0, n = intervals.length;
        LinkedList<int[]> output = new LinkedList<int[]>();

        // add all intervals starting before newInterval
        while (idx < n && newStart > intervals[idx][0])
            output.add(intervals[idx++]);

        // add newInterval
        int[] interval = new int[2];
        // if there is no overlap, just add the interval
        if (output.isEmpty() || output.getLast()[1] < newStart)
            output.add(newInterval);
            // if there is an overlap, merge with the last interval
        else {
            interval = output.removeLast();
            interval[1] = Math.max(interval[1], newEnd);
            output.add(interval);
        }

        // add next intervals, merge with newInterval if needed
        while (idx < n) {
            interval = intervals[idx++];
            int start = interval[0], end = interval[1];
            // if there is no overlap, just add an interval
            if (output.getLast()[1] < start) output.add(interval);
                // if there is an overlap, merge with the last interval
            else {
                interval = output.removeLast();
                interval[1] = Math.max(interval[1], end);
                output.add(interval);
            }
        }
        return output.toArray(new int[output.size()][2]);
    }
    public int[][] insertMine(int[][] intervals, int[] newInterval) {
        int newStart = newInterval[0];
        int newEnd = newInterval[1];
        int i = 0;
        List<int[]> ansList = new ArrayList<int[]>();

        while (i<intervals.length && intervals[i][1]<newStart) {
            ansList.add(intervals[i++]);
        }
        if (i<intervals.length) {
            newStart = Math.min(intervals[i][0], newStart);
            while (i<intervals.length && intervals[i][0]<=newEnd) {
                i++;
            }
            newEnd = i>0?Math.max(intervals[i-1][1], newEnd):newEnd;
        }
        ansList.add(new int[]{newStart, newEnd});

        while(i<intervals.length) {
            ansList.add(intervals[i++]);
        }

        int[][] ans = new int[ansList.size()][2];
        for (int j=0; j<ans.length; j++) {
            ans[j] = ansList.get(j);
        }
        return ans;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
//        int[][] intervals = new int[][]{
//                new int[]{1, 2},
//                new int[]{3, 5},
//                new int[]{6, 7},
//                new int[]{8, 10},
//                new int[]{12, 16},
//        };
//        int[] newInterval = new int[]{4, 8};
        int[][] intervals = new int[][]{
                new int[]{1, 5}
        };
        int[] newInterval = new int[]{0, 0};

        int[][] result = new Solution().insert(intervals, newInterval);
        System.out.println(toString(result));
    }

    private static String toString(int[] arr) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0)
                buf.append(",");
            buf.append(arr[i]);
        }
        buf.append("]");
        return buf.toString();
    }

    private static String toString(int[][] arr) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < arr.length; i++) {
            if (i != 0)
                buf.append(",");
            buf.append(toString(arr[i]));
        }
        buf.append("]");
        return buf.toString();
    }

}
