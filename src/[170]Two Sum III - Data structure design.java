import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class TwoSum {
    private ArrayList<Integer> nums;
    private boolean is_sorted;

    /** Initialize your data structure here. */
    public TwoSum() {
        this.nums = new ArrayList<Integer>();
        this.is_sorted = false;
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        this.nums.add(number);
        this.is_sorted = false;
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        if (!this.is_sorted) {
            Collections.sort(this.nums);
            this.is_sorted = true;
        }
        int low = 0, high = this.nums.size() - 1;
        while (low < high) {
            int twosum = this.nums.get(low) + this.nums.get(high);
            if (twosum < value)
                low += 1;
            else if (twosum > value)
                high -= 1;
            else
                return true;
        }
        return false;
    }
}

class TwoSumHashMap {
    private HashMap<Integer, Integer> num_counts;

    /** Initialize your data structure here. */
    public TwoSumHashMap() {
        this.num_counts = new HashMap<Integer, Integer>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        if (this.num_counts.containsKey(number))
            this.num_counts.replace(number, this.num_counts.get(number) + 1);
        else
            this.num_counts.put(number, 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (Map.Entry<Integer, Integer> entry : this.num_counts.entrySet()) {
            int complement = value - entry.getKey();
            if (complement != entry.getKey()) {
                if (this.num_counts.containsKey(complement))
                    return true;
            } else {
                if (entry.getValue() > 1)
                    return true;
            }
        }
        return false;
    }
}

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */