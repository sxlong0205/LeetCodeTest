/**
 * @author Code Dragon
 */
public class ReverseList {

    /**
     * 递归翻转链表
     *
     * @param node
     * @return
     */
    private ListNode reverse(ListNode node) {
        if (node.next == null) {
            return node;
        }

        ListNode last = reverse(node.next);
        node.next.next = node;
        node.next = null;
        return last;
    }

    /**
     * 翻转链表前N个节点
     */
    ListNode successor = null;

    private ListNode reverseN(ListNode node, int n) {
        if (n == 1) {
            successor = node.next;
            return node;
        }

        ListNode last = reverseN(node.next, n - 1);
        node.next.next = node;
        node.next = successor;
        return last;
    }

    /**
     * 翻转链表的一部分
     *
     * @param node
     * @param m
     * @param n
     * @return
     */
    private ListNode reverseBetween(ListNode node, int m, int n) {
        if (m == 1) {
            return reverseN(node, n);
        }
        node.next = reverseBetween(node.next, m - 1, n - 1);
        return node;
    }

    /**
     * 迭代翻转链表
     *
     * @param node
     * @return
     */
    private ListNode iterReverse(ListNode node) {
        ListNode pre = null, curr = node, next;
        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 翻转(a,b)区间
     *
     * @param a
     * @param b
     * @return
     */
    private ListNode iterReverseBetween(ListNode a, ListNode b) {
        ListNode pre, curr, next;
        pre = null;
        curr = a;
        next = a;
        while (curr != b) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 翻转K个一组的链表
     *
     * @param head
     * @param k
     * @return
     */
    private ListNode revserKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                return head;
            }
            b = b.next;
        }
        ListNode newHead = iterReverseBetween(a, b);
        a.next = revserKGroup(b, k);
        return newHead;
    }
}
