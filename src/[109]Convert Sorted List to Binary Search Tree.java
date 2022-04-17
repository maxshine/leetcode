import java.util.ArrayList;
import java.util.List;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
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
    public TreeNode sortedListToBSTMine(ListNode head) {
        int cnt = 0;
        ListNode current = head;
        while (current != null) {
            cnt++;
            current = current.next;
        }
        int[] sortArray = new int[cnt];
        int i = 0;
        while (head != null) {
            sortArray[i++] = head.val;
            head = head.next;
        }
        return helper(sortArray, 0, sortArray.length-1);
    }

    private TreeNode helper(int[] sortArray, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start+end+1)/2;
        TreeNode root = new TreeNode(sortArray[mid]);
        root.left = helper(sortArray, start, mid-1);
        root.right = helper(sortArray, mid+1, end);
        return root;
    }
    // Recursion
    private ListNode findMiddleElement(ListNode head) {

        // The pointer used to disconnect the left half from the mid node.
        ListNode prevPtr = null;
        ListNode slowPtr = head;
        ListNode fastPtr = head;

        // Iterate until fastPr doesn't reach the end of the linked list.
        while (fastPtr != null && fastPtr.next != null) {
            prevPtr = slowPtr;
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
        }

        // Handling the case when slowPtr was equal to head.
        if (prevPtr != null) {
            prevPtr.next = null;
        }

        return slowPtr;
    }

    public TreeNode sortedListToBSTRecursion(ListNode head) {

        // If the head doesn't exist, then the linked list is empty
        if (head == null) {
            return null;
        }

        // Find the middle element for the list.
        ListNode mid = this.findMiddleElement(head);

        // The mid becomes the root of the BST.
        TreeNode node = new TreeNode(mid.val);

        // Base case when there is just one element in the linked list
        if (head == mid) {
            return node;
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.
        node.left = this.sortedListToBST(head);
        node.right = this.sortedListToBST(mid.next);
        return node;
    }

    // Conversion
    private List<Integer> values = new ArrayList<Integer>();;

    private void mapListToValues(ListNode head) {
        while (head != null) {
            this.values.add(head.val);
            head = head.next;
        }
    }

    private TreeNode convertListToBSTConversion(int left, int right) {
        // Invalid case
        if (left > right) {
            return null;
        }

        // Middle element forms the root.
        int mid = (left + right + 1) / 2;
        TreeNode node = new TreeNode(this.values.get(mid));

        // Base case for when there is only one element left in the array
        if (left == right) {
            return node;
        }

        // Recursively form BST on the two halves
        node.left = convertListToBSTConversion(left, mid - 1);
        node.right = convertListToBSTConversion(mid + 1, right);
        return node;
    }

    public TreeNode sortedListToBSTConversion(ListNode head) {

        // Form an array out of the given linked list and then
        // use the array to form the BST.
        this.mapListToValues(head);

        // Convert the array to
        return convertListToBSTConversion(0, this.values.size() - 1);
    }

    // Inorder
    private ListNode head;

    private int findSize(ListNode head) {
        ListNode ptr = head;
        int c = 0;
        while (ptr != null) {
            ptr = ptr.next;
            c += 1;
        }
        return c;
    }

    private TreeNode convertListToBST(int l, int r) {
        // Invalid case
        if (l > r) {
            return null;
        }

        int mid = (l + r) / 2;

        // First step of simulated inorder traversal. Recursively form
        // the left half
        TreeNode left = this.convertListToBST(l, mid - 1);

        // Once left half is traversed, process the current node
        TreeNode node = new TreeNode(this.head.val);
        node.left = left;

        // Maintain the invariance mentioned in the algorithm
        this.head = this.head.next;

        // Recurse on the right hand side and form BST out of them
        node.right = this.convertListToBST(mid + 1, r);
        return node;
    }

    public TreeNode sortedListToBST(ListNode head) {
        // Get the size of the linked list first
        int size = this.findSize(head);

        this.head = head;

        // Form the BST now that we know the size
        return convertListToBST(0, size - 1);
    }
}