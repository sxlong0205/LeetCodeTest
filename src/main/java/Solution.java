import java.util.LinkedList;

/**
 * @author : Code Dragon
 * create at:  2020/7/20  8:56
 */
class Solution {
    public int[] reversePrint(ListNode head) {
        LinkedList<Integer> result = new LinkedList<>();
        while (head.next != null) {
            result.addFirst(head.val);
            head = head.next;
        }
        int[] res = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            res[i] = result.get(i);
        }
        return res;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

    }
}