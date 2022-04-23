import java.util.LinkedList;

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
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int min_depth = Integer.MAX_VALUE;
        if (root.left != null) {
            min_depth = Math.min(minDepth1(root.left), min_depth);
        }
        if (root.right != null) {
            min_depth = Math.min(minDepth1(root.right), min_depth);
        }
        return min_depth + 1;
    }

    //DFS
    public int minDepthDFS(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> stack = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        else {
            stack.add(new Pair(root, 1));
        }

        int min_depth = Integer.MAX_VALUE;
        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.pollLast();
            root = current.getKey();
            int current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                min_depth = Math.min(min_depth, current_depth);
            }
            if (root.left != null) {
                stack.add(new Pair(root.left, current_depth + 1));
            }
            if (root.right != null) {
                stack.add(new Pair(root.right, current_depth + 1));
            }
        }
        return min_depth;
    }
    // BFS
    public int minDepth(TreeNode root) {
        LinkedList<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        if (root == null) {
            return 0;
        }
        else {
            queue.add(new Pair(root, 1));
        }

        int current_depth = 0;
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> current = queue.poll();
            root = current.getKey();
            current_depth = current.getValue();
            if ((root.left == null) && (root.right == null)) {
                break;
            }
            if (root.left != null) {
                queue.add(new Pair(root.left, current_depth + 1));
            }
            if (root.right != null) {
                queue.add(new Pair(root.right, current_depth + 1));
            }
        }
        return current_depth;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        // TreeNode root = parseTreeNode(new Integer[]{3, 9, 20, null, null, 15, 7});
        TreeNode root = parseTreeNode(new Integer[]{2,null,3,null,4,null,5,null,6});

        int result = new Solution().minDepth(root);
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
