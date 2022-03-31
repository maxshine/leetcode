import java.util.ArrayList;
import java.util.List;
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
    boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stk = new Stack<>();
        TreeNode prev = null;

        while (!stk.empty() || root != null) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }
            root = stk.peek();
            stk.pop();

            // If next element in inorder traversal
            // is smaller than the previous one
            // that's not BST.
            if (prev != null && root.val <= prev.val) {
                return false;
            }
            prev = root;
            root = root.right;
        }
        return true;
    }
    
    TreeNode prev = null;
    boolean isValidBSTInorderRecusive(TreeNode root) {
        return inorder(root);
    }

    boolean inorder(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!inorder(root.left)) {
            return false;
        }
        if (prev != null && root.val <= prev.val) {
            return false;
        }
        prev = root;
        return inorder(root.right);
    }
    
    Stack<TreeNode> stk = new Stack<>(), lower_limits=new Stack<>(), upper_limits = new Stack<>();
    void update(TreeNode root, TreeNode low, TreeNode high) {
        stk.push(root);
        lower_limits.push(low);
        upper_limits.push(high);
    }
    boolean isValidBSTStack(TreeNode root) {
        TreeNode low = null;
        TreeNode high = null;
        update(root, low, high);

        while (!stk.empty()) {
            root = stk.peek();
            stk.pop();
            low = lower_limits.peek();
            lower_limits.pop();
            high = upper_limits.peek();
            upper_limits.pop();

            if (root == null) {
                continue;
            }

            TreeNode val_node = root;
            if (low != null && val_node.val <= low.val) {
                return false;
            }
            if (high != null && val_node.val >= high.val) {
                return false;
            }
            update(root.right, val_node, high);
            update(root.left, low, val_node);
        }
        return true;
    }

    boolean validateRecursive(TreeNode root, TreeNode low, TreeNode high) {
        // Empty trees are valid BSTs.
        if (root == null) {
            return true;
        }

        // The current node's value must be between low and high.
        if ((low != null && root.val <= low.val) ||
                (high != null && root.val >= high.val)) {
            return false;
        }

        // The left and right subtree must also be valid.
        return validateRecursive(root.right, root, high) &&
                validateRecursive(root.left, low, root);
    }

    public boolean isValidBSTRecursive(TreeNode root) {
        return validateRecursive(root, null, null);
    }
    public boolean isValidBSTMine(TreeNode root) {
        List<Integer> treeValues = new ArrayList<Integer>();
        traverse(root, treeValues);
        int prev = treeValues.get(0);
        for (int i=1; i<treeValues.size(); i++) {
            int current = treeValues.get(i);
            if (current<=prev) {
                return false;
            }
            prev = current;
        }
        return true;
    }

    private void traverse(TreeNode root, List<Integer> treeValues) {
        if ( root == null ){
            return;
        }
        traverse(root.left, treeValues);
        treeValues.add(root.val);
        traverse(root.right, treeValues);
    }
}