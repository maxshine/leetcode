import java.util.HashMap;
import java.util.Map;

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
    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap = new HashMap<Integer, Integer>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        for (int i=0; i<inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }
        return arrayToBinaryTree(preorder, 0, preorder.length-1);
    }

    private TreeNode arrayToBinaryTree(int[] preorder, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[preorderIndex++]);
        if (start == end) {
            return root;
        }

        int rootInorderIdx = inorderIndexMap.get(root.val);
        root.left = arrayToBinaryTree(preorder, start, rootInorderIdx-1);
        root.right = arrayToBinaryTree(preorder, rootInorderIdx+1, end);
        return root;
    }
}