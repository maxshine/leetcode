import java.util.HashMap;

// @solution-sync:begin
/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public HashMap<Node, Node> visitedHash = new HashMap<Node, Node>();

    // Mine hashmap
    public Node copyRandomListMine(Node head) {
        Node newHead = null;
        Node current = newHead;
        Node oldCurrent = head;
        HashMap<Node, Node> map = new HashMap<Node, Node>();

        while (oldCurrent != null) {
            Node newNode = new Node(oldCurrent.val);
            if (current == null) {
                newHead = newNode;
            } else {
                current.next = newNode;
            }
            current = newNode;
            map.put(oldCurrent, newNode);
            oldCurrent = oldCurrent.next;
        }
        current = newHead;
        oldCurrent = head;
        while (oldCurrent != null) {
            Node newNode = map.get(oldCurrent);
            if (oldCurrent.random != null) {
                map.get(oldCurrent).random = map.get(oldCurrent.random);
            }
            oldCurrent = oldCurrent.next;
        }
        return newHead;
    }


    // Recursive
    public Node copyRandomListRecursive(Node head) {

        if (head == null) {
            return null;
        }

        // If we have already processed the current node, then we simply return the cloned version of
        // it.
        if (this.visitedHash.containsKey(head)) {
            return this.visitedHash.get(head);
        }

        // Create a new node with the value same as old node. (i.e. copy the node)
        Node node = new Node(head.val, null, null);

        // Save this value in the hash map. This is needed since there might be
        // loops during traversal due to randomness of random pointers and this would help us avoid
        // them.
        this.visitedHash.put(head, node);

        // Recursively copy the remaining linked list starting once from the next pointer and then from
        // the random pointer.
        // Thus we have two independent recursive calls.
        // Finally we update the next and random pointers for the new node created.
        node.next = this.copyRandomListRecursive(head.next);
        node.random = this.copyRandomListRecursive(head.random);

        return node;
    }

    // 3
    public Node getClonedNode(Node node) {
        // If the node exists then
        if (node != null) {
            // Check if the node is in the visited dictionary
            if (this.visitedHash.containsKey(node)) {
                // If its in the visited dictionary then return the new node reference from the dictionary
                return this.visitedHash.get(node);
            } else {
                // Otherwise create a new node, add to the dictionary and return it
                this.visitedHash.put(node, new Node(node.val, null, null));
                return this.visitedHash.get(node);
            }
        }
        return null;
    }

    public Node copyRandomListMap(Node head) {

        if (head == null) {
            return null;
        }

        Node oldNode = head;

        // Creating the new head node.
        Node newNode = new Node(oldNode.val);
        this.visitedHash.put(oldNode, newNode);

        // Iterate on the linked list until all nodes are cloned.
        while (oldNode != null) {
            // Get the clones of the nodes referenced by random and next pointers.
            newNode.random = this.getClonedNode(oldNode.random);
            newNode.next = this.getClonedNode(oldNode.next);

            // Move one step ahead in the linked list.
            oldNode = oldNode.next;
            newNode = newNode.next;
        }
        return this.visitedHash.get(head);
    }

    // 4
    public Node copyRandomList(Node head) {

        if (head == null) {
            return null;
        }

        // Creating a new weaved list of original and copied nodes.
        Node ptr = head;
        while (ptr != null) {

            // Cloned node
            Node newNode = new Node(ptr.val);

            // Inserting the cloned node just next to the original node.
            // If A->B->C is the original linked list,
            // Linked list after weaving cloned nodes would be A->A'->B->B'->C->C'
            newNode.next = ptr.next;
            ptr.next = newNode;
            ptr = newNode.next;
        }

        ptr = head;

        // Now link the random pointers of the new nodes created.
        // Iterate the newly created list and use the original nodes' random pointers,
        // to assign references to random pointers for cloned nodes.
        while (ptr != null) {
            ptr.next.random = (ptr.random != null) ? ptr.random.next : null;
            ptr = ptr.next.next;
        }

        // Unweave the linked list to get back the original linked list and the cloned list.
        // i.e. A->A'->B->B'->C->C' would be broken to A->B->C and A'->B'->C'
        Node ptr_old_list = head; // A->B->C
        Node ptr_new_list = head.next; // A'->B'->C'
        Node head_old = head.next;
        while (ptr_old_list != null) {
            ptr_old_list.next = ptr_old_list.next.next;
            ptr_new_list.next = (ptr_new_list.next != null) ? ptr_new_list.next.next : null;
            ptr_old_list = ptr_old_list.next;
            ptr_new_list = ptr_new_list.next;
        }
        return head_old;
    }

}

// @solution-sync:end

class Main {

    public static void main(String[] args) {

    }

}
