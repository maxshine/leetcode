class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// @solution-sync:begin
class Solution {
    public ListNode deleteDuplicatesMine(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode newHead = new ListNode(0, null);
        ListNode newCurrent = newHead;
        ListNode current = head;
        boolean isDistinct = true;
        int prevValue = head.val;

        while (current != null) {
            current = current.next;
            if (current != null && current.val == prevValue) {
                isDistinct = false;
                continue;
            }
            if (isDistinct) {
                newCurrent.next = new ListNode(prevValue, null);
                newCurrent = newCurrent.next;
            }
            if (current != null) {
                prevValue = current.val;
                isDistinct = true;
            }
        }
        return newHead.next;
    }
    public ListNode deleteDuplicates(ListNode head) {
        // sentinel
        ListNode sentinel = new ListNode(0, head);

        // predecessor = the last node 
        // before the sublist of duplicates
        ListNode pred = sentinel;

        while (head != null) {
            // if it's a beginning of duplicates sublist 
            // skip all duplicates
            if (head.next != null && head.val == head.next.val) {
                // move till the end of duplicates sublist
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // skip all duplicates
                pred.next = head.next;
                // otherwise, move predecessor
            } else {
                pred = pred.next;
            }

            // move forward
            head = head.next;
        }
        return sentinel.next;
    }

    // sentinel
    ListNode sentinel = new ListNode(0, head);

    // predecessor = the last node 
    // before the sublist of duplicates
    ListNode pred = sentinel;
        
        while (head != null) {
        // if it's a beginning of duplicates sublist 
        // skip all duplicates
        if (head.next != null && head.val == head.next.val) {
            // move till the end of duplicates sublist
            while (head.next != null && head.val == head.next.val) {
                head = head.next;
            }
            // skip all duplicates
            pred.next = head.next;
            // otherwise, move predecessor
        } else {
            pred = pred.next;
        }

        // move forward
        head = head.next;
    }  
        return sentinel.next;
}
}