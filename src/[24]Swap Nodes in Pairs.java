class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// @solution-sync:begin
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode current = dummy;
        while(head != null && head.next != null) {
            ListNode first = head;
            ListNode second = head.next;
            current.next = second;
            first.next = second.next;
            second.next = first;
            current = first;
            head = first.next;

        }
        return dummy.next;
    }
}