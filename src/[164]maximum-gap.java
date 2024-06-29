import java.util.Arrays;

class Solution {
    public int maximumGapSimple(int[] nums) {
        if (nums.length < 2)            // check if array is empty or small sized
            return 0;

        Arrays.sort(nums);                 // sort the array

        int maxGap = 0;

        for (int i = 0; i < nums.length - 1; i++)
            maxGap = Math.max(nums[i + 1] - nums[i], maxGap);

        return maxGap;
    }

    public int maximumGapRadix(int[] nums) {
        if (nums.length < 2)            // check if array is empty or small sized
            return 0;

        int maxVal = Integer.MIN_VALUE;
        for (int i : nums) {
            maxVal = Math.max(maxVal, i);
        }

        int exp = 1;                                 // 1, 10, 100, 1000 ...
        int radix = 10;                              // base 10 system

        int[] aux = new int[nums.length];

        /* LSD Radix Sort */
        while (maxVal / exp > 0) { // Go through all digits from LSD to MSD
            int[] count = new int[radix];
            Arrays.fill(count, 0);

            for (int i = 0; i < nums.length; i++)    // Counting sort
                count[(nums[i] / exp) % 10]++;

            for (int i = 1; i < count.length; i++)   // you could also use partial_sum()
                count[i] += count[i - 1];

            for (int i = nums.length - 1; i >= 0; i--)
                aux[--count[(nums[i] / exp) % 10]] = nums[i];

            for (int i = 0; i < nums.size(); i++)
                nums[i] = aux[i];

            exp *= 10;
        }

        int maxGap = 0;

        for (int i = 0; i < nums.length - 1; i++)
            maxGap = Math.max(nums[i + 1] - nums[i], maxGap);

        return maxGap;
    }
    class Bucket {
        public boolean used = false;
        public int minval = Integer.MAX_VALUE;       // same as INT_MAX
        public int maxval = Integer.MIN_VALUE;        // same as INT_MIN
    };

    public int maximumGapBucket(int[] nums) {
        if (nums.length < 2)            // check if array is empty or small sized
            return 0;
        int maxi = Integer.MIN_VALUE;
        int mini = Integer.MAX_VALUE;
        for (int i : nums) {
            maxi = Math.max(maxi, i);
            mini = Math.min(maxi, i);
        }

        int bucketSize = Math.max(1, (maxi - mini) / (nums.length - 1));
        int bucketNum = (maxi - mini) / bucketSize + 1;
        Bucket[] buckets = new Bucket[bucketNum];

        for (int num : nums) {
            int bucketIdx = (num - mini) / bucketSize;
            buckets[bucketIdx].used = true;
            buckets[bucketIdx].minval = Math.min(num, buckets[bucketIdx].minval);
            buckets[bucketIdx].maxval = Math.max(num, buckets[bucketIdx].maxval);
        }
        int prevBucketMax = mini, maxGap = 0;
        for (Bucket bucket : buckets) {
            if (!bucket.used)
                continue;
            maxGap = Math.max(maxGap, bucket.minval - prevBucketMax);
            prevBucketMax = bucket.maxval;
        }
        return maxGap;
    }
}