class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// @solution-sync:begin
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        int qty = lists.length;
        int interval = 1;
        while(interval < qty) {
            for (int i=0; i<qty-interval; i +=interval*2) {
                lists[i] = mergeTwoLists(lists[i], lists[i+interval]);
            }
            interval = interval * 2;
        }
        return qty>0?lists[0]:null;
    }
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode head = new ListNode();
        ListNode curr = head;
        ListNode temp = lists[0];
        for (ListNode l : lists) {
            if (l != null) {
                temp = l;
                break;
            }
        }
        while(temp!=null) {
            int idx = 0;
            temp = lists[0];
            for(int i=0; i<lists.length; i++) {
                if (lists[i] != null && temp == null) {
                    temp = lists[i];
                    idx = i;
                } else if (lists[i] != null && lists[i].val<temp.val) {
                    temp = lists[i];
                    idx = i;
                }
            }
            curr.next = temp;
            curr = curr.next;
            if (temp != null) {
                lists[idx] = lists[idx].next;
            }
        }
        return head.next;
    }
    public ListNode mergeKLists1(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        ListNode result = lists[0];
        for (int i=1; i<lists.length; i++) {
            result = mergeTwoLists(result, lists[i]);
        }
        return result;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        else if (l2 == null) {
            return l1;
        }
        else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }
        else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}