import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {

    class Interval {
        int start;
        int end;
        public Interval(int[] interval) {
            this.start = interval[0];
            this.end = interval[1];
        }
        public int[] toArray() {
            return new int[] {start, end};
        }
    }
    public int[][] merge(int[][] intervals) {
        Comparator<Interval> comparator = new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start<o2.start) {
                    return -1;
                } else if (o1.start==o2.start) {
                    return 0;
                }
                return 1;
            }
        };
        Interval[] ar = new Interval[intervals.length];
        for (int i=0; i<intervals.length; i++) {
            ar[i] = new Interval(intervals[i]);
        }
        Arrays.sort(ar, comparator);
        List<Interval> ansList = new ArrayList<Interval>();
        for (int i=0; i<ar.length; i++) {
            if (ansList.isEmpty() || ar[i].start > ansList.get(ansList.size()-1).end) {
                ansList.add(ar[i]);
            } else if (ar[i].end > ansList.get(ansList.size()-1).end) {
                ansList.get(ansList.size()-1).end = ar[i].end;
            }
        }
        int[][] ans = new int[ansList.size()][];
        for (int i=0; i<ansList.size(); i++) {
            ans[i] = ansList.get(i).toArray();
        }
        return ans;
    }
}