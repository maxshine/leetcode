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

    // Mine
    public boolean isBalanced(TreeNode root) {
        if (root == null ) {
            return true;
        }
        int leftDepth = treeDepth(root.left);
        int rightDepth = treeDepth(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(treeDepth(root.left), treeDepth(root.right));
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        // TreeNode root = parseTreeNode(new Integer[]{3, 9, 20, null, null, 15, 7});
        TreeNode root = parseTreeNode(new Integer[]{1,2,2,3,null,null,3,4,null,null,4});
        boolean result = new Solution().isBalanced(root);
        System.out.println(result);
    }

    private static TreeNode parseTreeNode(Integer[] values) {
        TreeNode root = null;
        java.util.LinkedList<TreeNode> nodes = new java.util.LinkedList<TreeNode>();
        int i = 0;
        while (i < values.length) {
            if (i == 0) {
                root = new TreeNode(values[i]);
                i += 1;
                nodes.addLast(root);
                continue;
            }

            TreeNode parent = nodes.pop();
            if (values[i] != null) {
                TreeNode left = new TreeNode(values[i]);
                parent.left = left;
                nodes.addLast(left);
            }

            if (i + 1 < values.length && values[i + 1] != null) {
                TreeNode right = new TreeNode(values[i + 1]);
                parent.right = right;
                nodes.addLast(right);
            }

            i += 2;
        }
        return root;
    }

}
