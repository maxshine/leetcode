class Solution {
    public int removeDuplicatesMine(int[] nums) {
        int cnt = 0;
        int k = 0;
        for (int i=1; i<nums.length; i++) {
            if (nums[k] != nums[i]) {
                nums[++k] = nums[i];
                cnt = 0;
            } else if (nums[k] == nums[i] && cnt == 0) {
                nums[++k] = nums[i];
                cnt += 1;
            }
        }
        return k+1;
    }

    public int[] remElement(int[] arr, int index) {

        //
        // Overwrite the element at the given index by 
        // moving all the elements to the right of the
        // index, one position to the left.
        //
        for (int i = index + 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }

        return arr;
    }

    public int removeDuplicatesDrop(int[] nums) {

        // Initialize the counter and the array index.
        int i = 1, count = 1, length = nums.length;

        //
        // Start from the second element of the array and process
        // elements one by one.
        //
        while (i < length) {

            //
            // If the current element is a duplicate, 
            // increment the count.
            //
            if (nums[i] == nums[i - 1]) {

                count++;

                //    
                // If the count is more than 2, this is an unwanted duplicate element
                // and hence we remove it from the array.
                //    
                if (count > 2) {

                    this.remElement(nums, i);

                    //
                    // Note that we have to decrement the array index value to
                    // keep it consistent with the size of the array.
                    //    
                    i--;

                    //
                    // Since we have a fixed size array and we can't actually
                    // remove an element, we reduce the length of the array as
                    // well.
                    //
                    length--;
                }
            } else {

                //
                // Reset the count since we encountered a different element
                // than the previous one.
                //
                count = 1;
            }

            // Move on to the next element in the array
            i++;
        }

        return length;
    }
    public int removeDuplicates(int[] nums) {

        //
        // Initialize the counter and the second pointer.
        //
        int j = 1, count = 1;

        //
        // Start from the second element of the array and process
        // elements one by one.
        //
        for (int i = 1; i < nums.length; i++) {

            //
            // If the current element is a duplicate, increment the count.
            //
            if (nums[i] == nums[i - 1]) {

                count++;

            } else {

                //
                // Reset the count since we encountered a different element
                // than the previous one.
                //
                count = 1;
            }

            //
            // For a count <= 2, we copy the element over thus
            // overwriting the element at index "j" in the array
            //
            if (count <= 2) {
                nums[j++] = nums[i];
            }
        }
        return j;
    }
}