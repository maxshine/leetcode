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

class Pair<K, V> {
    K key;
    V value;
    Pair(K a, V b) {
        this.key = a;
        this.value = b;
    }
    public K getKey() {
        return this.key;
    }
    public V getValue() {
        return this.value;
    }
}

// @solution-sync:begin
class Solution {
    private TreeNode flattenTreeRecursive(TreeNode node) {

        // Handle the null scenario
        if (node == null) {
            return null;
        }

        // For a leaf node, we simply return the
        // node as is.
        if (node.left == null && node.right == null) {
            return node;
        }

        //Recursively flatten the left subtree
        TreeNode leftTail = this.flattenTreeRecursive(node.left);

        // Recursively flatten the right subtree
        TreeNode rightTail = this.flattenTreeRecursive(node.right);

        // If there was a left subtree, we shuffle the connections
        // around so that there is nothing on the left side
        // anymore.
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        // We need to return the "rightmost" node after we are
        // done wiring the new connections.
        return rightTail == null ? leftTail : rightTail;
    }

    public void flatten(TreeNode root) {

        this.flattenTreeRecursive(root);
    }

    public void flattenIteration(TreeNode root) {

        // Handle the null scenario
        if (root == null) {
            return;
        }

        int START = 1, END = 2;

        TreeNode tailNode = null;
        Stack<Pair<TreeNode, Integer>> stack = new Stack<Pair<TreeNode, Integer>>();
        stack.push(new Pair<TreeNode, Integer>(root, START));

        while (!stack.isEmpty()) {

            Pair<TreeNode, Integer> nodeData = stack.pop();
            TreeNode currentNode = nodeData.getKey();
            int recursionState = nodeData.getValue();

            // We reached a leaf node. Record this as a tail
            // node and move on.
            if (currentNode.left == null && currentNode.right == null) {
                tailNode = currentNode;
                continue;
            }

            // If the node is in the START state, it means we still
            // haven't processed it's left child yet.
            if (recursionState == START) {

                // If the current node has a left child, we add it
                // to the stack AFTER adding the current node again
                // to the stack with the END recursion state.
                if (currentNode.left != null) {

                    stack.push(new Pair<TreeNode, Integer>(currentNode, END));
                    stack.push(new Pair<TreeNode, Integer>(currentNode.left, START));

                } else if (currentNode.right != null) {

                    // In case the current node didn't have a left child
                    // we will add it's right child
                    stack.push(new Pair<TreeNode, Integer>(currentNode.right, START));
                }

            } else {

                // If the current node is in the END recursion state,
                // that means we did process one of it's children. Left
                // if it existed, else right.
                TreeNode rightNode = currentNode.right;

                // If there was a left child, there must have been a leaf
                // node and so, we would have set the tailNode
                if (tailNode != null) {

                    // Establish the proper connections.
                    tailNode.right = currentNode.right;
                    currentNode.right = currentNode.left;
                    currentNode.left = null;
                    rightNode = tailNode.right;

                }

                if (rightNode != null) {
                    stack.push(new Pair<TreeNode, Integer>(rightNode, START));
                }
            }
        }
    }

    public void flattenIterationOone(TreeNode root) {

        // Handle the null scenario
        if (root == null) {
            return;
        }

        TreeNode node = root;

        while (node != null) {

            // If the node has a left child
            if (node.left != null) {

                // Find the rightmost node
                TreeNode rightmost = node.left;
                while (rightmost.right != null) {
                    rightmost = rightmost.right;
                }

                // rewire the connections
                rightmost.right = node.right;
                node.right = node.left;
                node.left = null;
            }

            // move on to the right side of the tree
            node = node.right;
        }
    }
}