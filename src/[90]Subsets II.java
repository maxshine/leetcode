import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;
import java.lang.StringBuilder;

// @solution-sync:begin
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<List<Integer>>();
        List<Integer> currentSubset = new ArrayList<Integer>();
        subsetsWithDupHelper(subsets, currentSubset, nums, 0);
        return subsets;
    }

    void subsetsWithDupHelper(List<List<Integer>> subsets, List<Integer> currentSubset, int[] nums, int index) {
        // Add the subset formed so far to the subsets list.
        
        subsets.add(new ArrayList<>(currentSubset));

        for (int i = index; i < nums.length; i++) {
            // If the current element is a duplicate, ignore.
            if (i != index && nums[i] == nums[i - 1]) {
                continue;
            }
            currentSubset.add(nums[i]);
            subsetsWithDupHelper(subsets, currentSubset, nums, i + 1);
            currentSubset.remove(currentSubset.size()-1);
        }
    }
    
    public List<List<Integer>> subsetsWithDupIterate(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<List<Integer>>();
        subsets.add(new ArrayList<Integer>());

        int subsetSize = 0;

        for (int i = 0; i < nums.length; i++) {
            int startingIndex = (i >= 1 && nums[i] == nums[i - 1]) ? subsetSize : 0;
            // subsetSize refers to the size of the subset in the previous step. This value also indicates the starting index of the subsets generated in this step.
            subsetSize = subsets.size();
            List<List<Integer>> newSubsets = new ArrayList<List<Integer>>();
            int num = nums[i];
            for (int j = startingIndex; j < subsetSize; j++) {
                List<Integer> currentSubset = subsets.get(j);
                newSubsets.add(new ArrayList<>(currentSubset){{add(num);}});
            }
            for (List<Integer> l : newSubsets) {
                subsets.add(l);
            }
        }
        return subsets;
    }

    public List<List<Integer>> subsetsWithDupBitmask(int[] nums) {
        int n = nums.length;

        // Sort the generated subset. This will help to identify duplicates.
        Arrays.sort(nums);
        List<List<Integer>> subsets = new ArrayList<List<Integer>>();

        int maxNumberOfSubsets = (int)Math.pow(2, n);
        // To store the previously seen sets.
        HashSet<String> seen = new HashSet<>();

        for (int subsetIndex = 0; subsetIndex < maxNumberOfSubsets; subsetIndex++) {
            // Append subset corresponding to that bitmask.
            List<Integer> currentSubset = new ArrayList<>();
            StringBuilder hashcode = new StringBuilder();
            for (int j = 0; j < n; j++) {
                // Generate the bitmask
                int mask = 1 << j;
                int isSet = mask & subsetIndex;
                if (isSet != 0) {
                    currentSubset.add(nums[j]);
                    // Generate the hashcode by creating a comma separated string of numbers in the currentSubset.
                    hashcode.append(nums[j] + ",");
                }
            }

            if (!seen.contains(hashcode)) {
                subsets.add(currentSubset);
                seen.add(hashcode.toString());
            }
        }
        return subsets;
    }

    public List<List<Integer>> subsetsWithDupMine(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i=0; i<=nums.length; i++) {
            List<Integer> current = new ArrayList<Integer>();
            backtraceMine(ans, current, nums, i, 0);
        }
        return ans;
    }

    private void backtraceMine(List<List<Integer>> ans, List<Integer> current, int[] nums, int i, int start) {
        int currLength = current.size();
        if (currLength == i) {
            ans.add(new LinkedList<Integer>(current));
            return;
        }
        for (int j=start; j<nums.length-(i-currLength-1); j++) {
            current.add(nums[j]);
            backtraceMine(ans, current, nums, i, j+1);
            current.remove(current.size()-1);
            while (j<nums.length-1 && nums[j] == nums[j+1]) {
                j++;
            }
        }
    }
}