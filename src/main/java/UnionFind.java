public class UnionFind {
    int count = 0;
    int[] parent;
    int[] size;

    /**
     * 初始化节点父节点，节点所以对应树的大小
     * @param count
     */
    public UnionFind(int count) {
        this.count = count;
        parent = new int[count];
        size = new int[count];

        for (int i = 0; i < count; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // 合并树
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }

        // 小树接到大树下面
        if (size[rootP] > size[rootQ]) {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        } else {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        count--;
    }

    // 判断两个节点之间是否联通
    public boolean connected(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        return rootP == rootQ;
    }

    // 查找当前节点的父节点
    private int find(int p) {
        while (parent[p] != p) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    // 返回剩余集合数量
    public int count() {
        return count;
    }
}
