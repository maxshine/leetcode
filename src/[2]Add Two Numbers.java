class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// @solution-sync:begin
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0, null);
        if (l1 == null && l2 == null) {
            return result;
        }
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        int carry = 0;
        int sum = 0;
        int a = 0;
        int b = 0;
        ListNode temp = result;
        while (l1 != null || l2 != null) {
            if (l1 == null) {
                a = 0;
            } else {
                a = l1.val;
                l1 = l1.next;
            }
            if (l2 == null) {
                b = 0;
            } else {
                b = l2.val;
                l2 = l2.next;
            }
            sum = a + b + carry;
            carry = sum / 10;
            temp.next = new ListNode(sum % 10);
            temp = temp.next;
        }
        if (carry != 0) {
            temp.next = new ListNode(1);
        }
        return result.next;
    }
}