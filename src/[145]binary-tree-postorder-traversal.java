import java.util.LinkedList;
import java.util.List;
import java.util.Arrays;
import java.lang.StringBuilder;

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

    List<Integer> output = new LinkedList<>();
    LinkedList<TreeNode> stack = new LinkedList<>();
    // Mine
    public List<Integer> postorderTraversalMine(TreeNode root) {
        if (root == null) {
            return output;
        }
        postorderTraversalMine(root.left);
        postorderTraversalMine(root.right);
        output.add(root.val);
        return output;
    }
    //Iteration
    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return output;
        }
        TreeNode node = root;
        TreeNode lastVisit = node;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.add(node);
                node = node.left;
            }
            node = stack.peekLast();
            if (node.right == null || node.right == lastVisit) {
                output.add(node.val);
                stack.pollLast();
                lastVisit = node;
                node = null;
            } else {
                node = node.right;
            }
        }
        return output;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        TreeNode root = parseTreeNode(new Integer[]{1, null, 2, 3});

        List<Integer> result = new Solution().postorderTraversal(root);
        System.out.println(listToString(result));
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

    private static String listToString(Integer[] array) {
        return listToString(Arrays.asList(array));
    }

    private static String listToString(List<Integer> list) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0)
                buf.append(",");
            buf.append(list.get(i));
        }
        buf.append("]");
        return buf.toString();
    }

}
