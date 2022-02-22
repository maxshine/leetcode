import java.lang.StringBuilder;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

// @solution-sync:begin
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // base cases
        if (head == null) return null;
        if (head.next == null) return head;

        // close the linked list into the ring
        ListNode old_tail = head;
        int n;
        for(n = 1; old_tail.next != null; n++)
            old_tail = old_tail.next;
        old_tail.next = head;

        // find new tail : (n - k % n - 1)th node
        // and new head : (n - k % n)th node
        ListNode new_tail = head;
        for (int i = 0; i < n - k % n - 1; i++)
            new_tail = new_tail.next;
        ListNode new_head = new_tail.next;

        // break the ring
        new_tail.next = null;

        return new_head;
    }
    public ListNode rotateRightMine(ListNode head, int k) {
        int cnt = 0;
        ListNode ptr = head;
        ListNode previous = null;
        while(ptr != null) {
            previous = ptr;
            ptr = ptr.next;
            cnt++;
        }
        if (head == null || (k % cnt) == 0) {
            return head;
        }
        ListNode tail = previous;
        int remainder = (cnt - k % cnt);
        ptr = head;
        while (remainder>0) {
            previous = ptr;
            ptr = ptr.next;
            remainder--;
        }
        if (tail != null) {
            tail.next = head;
        }
        if (previous != null) {
            previous.next = null;
        }
        head = ptr;
        return head;
    }
}
// @solution-sync:end

class Main {

    public static void main(String[] args) {
        ListNode head = parseListNode(new int[]{1, 2, 3, 4, 5});
        int k = 5;
//        ListNode head = parseListNode(new int[]{1});
//        int k = 1;
        ListNode result = new Solution().rotateRight(head, k);
        System.out.println(toString(result));
    }

    private static ListNode parseListNode(int[] values) {
        ListNode head = null;
        ListNode tail = null;
        for (int value : values) {
            ListNode node = new ListNode(value);
            if (head == null)
                head = node;
            else
                tail.next = node;
            tail = node;
        }
        return head;
    }

    private static String toString(ListNode h) {
        StringBuilder buf = new StringBuilder();
        buf.append("[");
        ListNode c = h;
        while (c != null) {
            buf.append(c.val);
            if (c.next != null)
                buf.append(",");
            c = c.next;
        }
        buf.append("]");
        return buf.toString();
    }

}
