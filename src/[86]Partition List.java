class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// @solution-sync:begin
class Solution {
    public ListNode partitionMine(ListNode head, int x) {
        ListNode lessHead = new ListNode();
        ListNode anotherHead = new ListNode();
        
        ListNode lessCurrent = lessHead;
        ListNode anotherCurrent = anotherHead;
        
        while (head != null) {
            ListNode temp = head;
            head = head.next;
            if (temp.val<x) {
                lessCurrent.next = temp;
                lessCurrent = lessCurrent.next;
            } else {
                anotherCurrent.next = temp;
                anotherCurrent = anotherCurrent.next;
            }
            temp.next = null;
        }
        lessCurrent.next = anotherHead.next;
        return lessHead.next;
    }

    public ListNode partition(ListNode head, int x) {

        // before and after are the two pointers used to create the two list
        // before_head and after_head are used to save the heads of the two lists.
        // All of these are initialized with the dummy nodes created.
        ListNode before_head = new ListNode(0);
        ListNode before = before_head;
        ListNode after_head = new ListNode(0);
        ListNode after = after_head;

        while (head != null) {

            // If the original list node is lesser than the given x,
            // assign it to the before list.
            if (head.val < x) {
                before.next = head;
                before = before.next;
            } else {
                // If the original list node is greater or equal to the given x,
                // assign it to the after list.
                after.next = head;
                after = after.next;
            }

            // move ahead in the original list
            head = head.next;
        }

        // Last node of "after" list would also be ending node of the reformed list
        after.next = null;

        // Once all the nodes are correctly assigned to the two lists,
        // combine them to form a single list which would be returned.
        before.next = after_head.next;

        return before_head.next;
    }
}