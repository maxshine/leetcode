class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// @solution-sync:begin
class Solution {
    
    private int maxSum = Integer.MIN_VALUE;
    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftGains = Math.max(maxGain(node.left), 0);
        int rightGains = Math.max(maxGain(node.right), 0);
        int priceNewPath = node.val + leftGains + rightGains;
        maxSum = Math.max(maxSum, priceNewPath);
        return node.val + Math.max(leftGains, rightGains);
    }
    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }
}