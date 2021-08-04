import java.util.Random;
import java.util.Stack;

/**
 * @author : Code Dragon
 * @create: 2021/8/4 09:31
 * @description :
 */
public class SkipList {
    /**
     * 头节点
     */
    SkipNode headNode;

    /**
     * 当前跳表索引层数
     */
    int highLevel;

    /**
     * 用于产生随机数
     */
    Random random;

    /**
     * 最大层数
     */
    final int MAX_LEVEL = 32;

    /**
     * 初始化方法
     */
    SkipList() {
        random = new Random();
        headNode = new SkipNode(Integer.MIN_VALUE, null);
        highLevel = 0;
    }

    /**
     * 查找操作
     *
     * @param key
     * @return
     */
    public SkipNode search(int key) {
        SkipNode team = headNode;
        while (team != null) {
            // 如果当前值就是要找的值，直接返回
            if (team.key == key) {
                return team;
                // 如果当前位置右边为空，或者右边的值大于要寻找的值，则向下搜索
            } else if (team.right == null || team.right.key > key) {
                team = team.down;
                // 如果当前位置右边不为空，且右边值小于要寻找的值，则向右搜索
            } else {
                team = team.right;
            }
        }
        // 如果找不到，返回空
        return null;
    }

    /**
     * 删除操作
     *
     * @param key
     */
    public void delete(int key) {
        SkipNode team = headNode;
        while (team != null) {
            // 如果当前位置右边为空或者右边的值大于要搜索的值，那么向下搜索
            if (team.right == null || team.right.key > key) {
                team = team.down;
                // 找到需要删除的值后，删除节点并向下搜索看下层是否存在相同值的节点
            } else if (team.right.key == key) {
                team.right = team.right.right;
                team = team.down;
                // 如果当前位置右边的值小于需要删除的值，那么向右搜索
            } else if (team.right.key < key) {
                team = team.right;
            }
        }
    }

    /**
     * 插入操作
     *
     * 插入操作的基本思想是在最底层肯定要插入元素，然后判断是否需要向上层延伸
     * 这里向上延伸用一个随机数来判断，如果随机数大于0.5则向上
     * 同时，要找到上层的节点，需要用到Stack结构来存储每一层头节点
     *
     * @param node
     */
    public void add(SkipNode node) {
        int key = node.key;
        SkipNode findNode = search(key);
        // 判断当前跳表中是否已经存在key，如果存在，直接更新value即可
        if (findNode != null) {
            findNode.value = node.value;
            return;
        }

        /**
         * 存储向下的节点，这些节点可能在右侧插入节点
         */
        Stack<SkipNode> stack = new Stack<>();

        /**
         * 查找待插入节点
         */
        SkipNode team = headNode;
        while (team != null) {
            if (team.right == null || team.right.key > key) {
                // 记录曾经向下的节点
                stack.add(team);
                team = team.down;
            } else {
                team = team.right;
            }
        }

        /**
         * 当前层数，从第一层添加
         */
        int level = 1;

        /**
         * 前驱节点
         */
        SkipNode downNode = null;

        while (!stack.isEmpty()) {
            // 抛出待插入的左侧节点
            team = stack.pop();
            // 节点需要重新创建
            SkipNode nodeTeam = new SkipNode(node.key, node.value);
            // 处理垂直方向
            nodeTeam.down = downNode;
            // 标记新的节点下次使用
            downNode = nodeTeam;
            // 右侧为null，说明插入在末尾
            if (team.right == null) {
                team.right = nodeTeam;
            } else {
                // 右侧还有节点，插入在两者之间
                nodeTeam.right = team.right;
                team.right = nodeTeam;
            }

            // 考虑是否需要向上
            if (level > MAX_LEVEL) {
                break;
            }
            double num = random.nextDouble();
            if (num > 0.5) {
                break;
            }
            level++;

            // 比当前最大高度高但是依然在允许范围内，需要改变head节点
            if (level > highLevel) {
                highLevel = level;
                // 需要创建一个新的节点
                SkipNode highHeadNode = new SkipNode(Integer.MIN_VALUE, null);
                highHeadNode.down = headNode;
                // 改变head
                headNode = highHeadNode;
                // 下次抛出head
                stack.add(headNode);
            }
        }
    }

    /**
     * 打印跳表
     */
    public void printList() {
        SkipNode teamNode = headNode;
        int index = 1;
        SkipNode last = teamNode;
        // 找到跳表最底层头结点
        while (last.down != null) {
            last = last.down;
        }
        while (teamNode != null) {
            SkipNode enumNode = teamNode.right;
            SkipNode enumLast = last.right;
            System.out.printf("%-8s", "head->");
            while (enumLast != null && enumNode != null) {
                if (enumLast.key == enumNode.key) {
                    System.out.printf("%-5s", enumLast.key + "->");
                    enumLast = enumLast.right;
                    enumNode = enumNode.right;
                } else {
                    enumLast = enumLast.right;
                    System.out.printf("%-5s", "");
                }
            }
            teamNode = teamNode.down;
            index++;
            System.out.println();
        }
    }
}

class SkipNode<T> {
    int key;
    T value;

    /**
     * 向右、向下两个方向的节点
     */
    SkipNode right, down;

    public SkipNode(int key, T value) {
        this.key = key;
        this.value = value;
    }
}