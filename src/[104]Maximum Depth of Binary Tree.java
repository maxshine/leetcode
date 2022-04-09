import java.util.HashMap;
import java.util.Stack;

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
    public int maxDepthMine(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepthMine(root.left), maxDepthMine(root.right));
    }

    // Iteration
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Stack<TreeNode> stack = new Stack<>();
        HashMap<TreeNode, Integer> pairs = new HashMap<>();
        stack.add(root);
        pairs.put(root, 1);
        int maxDepth = 0;
        while (!stack.isEmpty()) {
            TreeNode n = stack.pop();
            Integer nDepth = pairs.get(n);
            maxDepth = Math.max(maxDepth, nDepth);
            int nextDepth = nDepth + 1;
            if (n.left != null) {
                stack.add(n.left);
                pairs.put(n.left, nextDepth);
            }
            if (n.right != null) {
                stack.add(n.right);
                pairs.put(n.right, nextDepth);
            }
        }
        return maxDepth;
    }
}