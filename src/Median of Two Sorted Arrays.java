class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        int len1 = nums1.length;
        int len2 = nums2.length;

        int imin = 0;
        int imax = len1;
        int half = (len1 + len2 + 1) / 2;

        while (imin <= imax) {
            int i = (imin + imax) / 2;
            int j = half - i;

            if (i > imin && nums1[i-1] > nums2[j]) {
                imax = i - 1;
            } else if (i < imax && nums2[j-1] > nums1[i]) {
                imin = i + 1;
            } else {
                double result;
                int maxleft = 0;
                if (i == 0) {
                    maxleft = nums2[j-1];
                } else if (j == 0) {
                    maxleft = nums1[i-1];
                } else {
                    maxleft = Math.max(nums1[i-1], nums2[j-1]);
                }
                if ((len1 + len2) % 2 == 1) {
                    return (double)maxleft;
                }
                int minright = 0;
                if (i == len1) {
                    minright = nums2[j];
                } else if (j == len2) {
                    minright = nums1[i];
                } else {
                    minright = Math.min(nums1[i], nums2[j]);
                }
                return (double)(maxleft+minright) / 2;
            }
        }
        return 0.0;
    }
}