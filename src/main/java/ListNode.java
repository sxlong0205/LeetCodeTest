/**
 * @author : Code Dragon
 * create at:  2020/8/10  23:01
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }


    //添加结点
    public void add(int newdata) {
        ListNode newNode = new ListNode(newdata);    //创建一个结点
        if (this.next == null) {
            this.next = newNode;
        } else {
            this.next.add(newdata);
        }
    }

    // 打印链表
    public void print() {
        System.out.print(this.val);
        if (this.next != null) {
            System.out.print("-->");
            this.next.print();
        }
    }

    public static void main(String[] args) {
        int[] constructs = new int[]{1, 3, 2};
        ListNode listNode = new ListNode(constructs[0]);
        for (int i = 1; i < constructs.length; i++) {
            listNode.add(constructs[i]);
        }
        listNode.print();
    }
}
