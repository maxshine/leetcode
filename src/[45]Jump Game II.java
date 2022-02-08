class Solution {
    public int jumpDP(int[] nums) {
        int[] bp = new int[nums.length];
        for (int i=0; i<bp.length; i++) {
            bp[i] = Integer.MAX_VALUE;
        }
        bp[nums.length-1] = 0;
        
        for (int i=nums.length-2; i>=0; i--) {
            for (int j=1; j<=nums[i]; j++) {
                if (i+j<nums.length) {
                    if (bp[i+j] != Integer.MAX_VALUE) {
                        bp[i] = Math.min(bp[i], bp[i+j]+1);
                    }
                }
            }
        }
        return bp[0];
    }

    public int jump(int[] nums) {
        int jumps = 0, currentJumpEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // we continuously find the how far we can reach in the current jump
            farthest = Math.max(farthest, i + nums[i]);
            // if we have come to the end of the current jump,
            // we need to make another jump
            if (i == currentJumpEnd) {
                jumps++;
                currentJumpEnd = farthest;
            }
        }
        return jumps;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        //int[] nums = new int[]{2, 3, 1, 1, 4};
        int[] nums = new int[]{2,3,0,1,4};

        int result = new Solution().jump(nums);
        System.out.println(result);
    }

}
