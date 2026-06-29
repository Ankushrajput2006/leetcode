class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int half = (m + n + 1) / 2;

        int left = 0;
        int right = m;

        while (left <= right) {
            int i = (left + right) / 2;      // partition index for nums1
            int j = half - i;               // partition index for nums2

            int nums1LeftMax  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
            int nums1RightMin = (i == m) ? Integer.MAX_VALUE : nums1[i];

            int nums2LeftMax  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
            int nums2RightMin = (j == n) ? Integer.MAX_VALUE : nums2[j];

            if (nums1LeftMax <= nums2RightMin && nums2LeftMax <= nums1RightMin) {
                // Correct partition found
                if ((m + n) % 2 == 1) {
                    return (double) Math.max(nums1LeftMax, nums2LeftMax);
                } else {
                    return ((double) Math.max(nums1LeftMax, nums2LeftMax)
                            + Math.min(nums1RightMin, nums2RightMin)) / 2.0;
                }
            } else if (nums1LeftMax > nums2RightMin) {
                // Move partition i left
                right = i - 1;
            } else {
                // nums2LeftMax > nums1RightMin -> move partition i right
                left = i + 1;
            }
        }

        // Should never reach here if inputs are valid (sorted arrays)
        throw new IllegalArgumentException("Input arrays are not valid sorted arrays.");

    }
}