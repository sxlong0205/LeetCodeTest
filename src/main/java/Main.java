public class Main {
    public static boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root.val == subRoot.val) {
            return dfs(root, subRoot);
        } else {
            boolean left = isSubtree(root.left, subRoot);
            boolean right = isSubtree(root.right, subRoot);
            return left || right;
        }
    }

    private static boolean dfs(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) {
            return true;
        }

        boolean left = dfs(root.left, subRoot.left);
        boolean right = dfs(root.right, subRoot.right);
        return root.val == subRoot.val && left && right;
    }

    public static void main(String[] args) {
        TreeNode root = ConstructTree.constructTree(new Integer[]{1, 2, 3, 4, 5});
        TreeNode subRoot = ConstructTree.constructTree(new Integer[]{1, 2, 3});
        boolean result = isSubtree(root, subRoot);
        System.out.println(result);
    }
}