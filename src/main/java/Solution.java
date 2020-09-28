import java.util.*;

/**
 * @author : Code Dragon
 * create at:  2020/7/20  8:56
 */
class Solution {
    public Node connect(Node root) {
        if (root == null) return root;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                Node temp = queue.poll();

                if (i < len - 1) {
                    temp.next = queue.peek();
                }

                if (temp.left != null)
                    queue.add(temp.left);
                if (temp.right != null)
                    queue.add(temp.right);
            }
        }
        return root;
    }
}

