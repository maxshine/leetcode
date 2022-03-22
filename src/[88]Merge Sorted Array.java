class Solution {
    public void mergeMine(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = nums1.length-1;

        while (i>=0 || j>=0) {
            int tmp = 0;
            if (j<0 || i>=0 && nums1[i]>=nums2[j]) {
                tmp = nums1[i--];
            } else if (i<0 || j>=0 && nums1[i]<nums2[j]) {
                tmp = nums2[j--];
            }
            nums1[k--] = tmp;
        }
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Set p1 and p2 to point to the end of their respective arrays.
        int p1 = m - 1;
        int p2 = n - 1;

        // And move p backwards through the array, each time writing
        // the smallest value pointed at by p1 or p2.
        for (int p = m + n - 1; p >= 0; p--) {
            if (p2 < 0) {
                break;
            }
            if (p1 >= 0 && nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1--];
            } else {
                nums1[p] = nums2[p2--];
            }
        }
    }
}