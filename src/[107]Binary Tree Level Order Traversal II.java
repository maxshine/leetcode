import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
    
    // BFS
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levels = new ArrayList<List<Integer>>();
        if (root == null) return levels;

        ArrayDeque<TreeNode> nextLevel = new ArrayDeque() {{ offer(root); }};
        ArrayDeque<TreeNode> currLevel = new ArrayDeque();

        while (!nextLevel.isEmpty()) {
            currLevel = nextLevel.clone();
            nextLevel.clear();
            levels.add(new ArrayList<Integer>());

            for (TreeNode node : currLevel) {
                // append the current node value
                levels.get(levels.size() - 1).add(node.val);

                // process child nodes for the next level
                if (node.left != null)
                    nextLevel.offer(node.left);
                if (node.right != null)
                    nextLevel.offer(node.right);
            }
        }

        Collections.reverse(levels);
        return levels;
    }
    
    // DFS
    List<List<Integer>> levels = new ArrayList<List<Integer>>();

    public void helper(TreeNode node, int level) {
        // start the current level
        if (levels.size() == level)
            levels.add(new ArrayList<Integer>());

        // append the current node value
        levels.get(level).add(node.val);

        // process child nodes for the next level
        if (node.left != null)
            helper(node.left, level + 1);
        if (node.right != null)
            helper(node.right, level + 1);
    }

    public List<List<Integer>> levelOrderBottomDFS(TreeNode root) {
        if (root == null) return levels;
        helper(root, 0);
        Collections.reverse(levels);
        return levels;
    }
}