import java.util.HashMap;
import java.util.LinkedHashMap;

public class LRU {
    class LRUCache {

        /**
         * 容量
         */
        int capacity;

        /**
         * 缓存
         */
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

        public LRUCache(int capacity) {
            this.capacity = capacity;
        }

        /**
         * 获取缓存中 value
         *
         * @param key
         * @return
         */
        public int get(int key) {
            // 如果没有包含 key 直接返回 -1
            if (!cache.containsKey(key)) {
                return -1;
            }
            // 将当前访问过的 key 放到最近的访问位置
            makeRecently(key);
            // 返回缓存中 key 对应的数据
            return cache.get(key);
        }

        /**
         * 加入新 key value 对
         *
         * @param key
         * @param value
         */
        public void put(int key, int value) {
            // 如果 cache 中包含当前 key，直接更新 key 即可，并将当前 key 对应的数据更新为最近访问
            if (cache.containsKey(key)) {
                cache.put(key, value);
                makeRecently(key);
                // 注意这里需要直接返回，不然和后面的 cache.put(key, value); 冲突
                return;
            }

            // 如果 cache 容量已满，删除 cache 中头节点位置，即最久未访问的元素，然后将当前 key value 加入 cache
            if (cache.size() >= capacity) {
                int val = cache.keySet().iterator().next();
                cache.remove(val);
            }
            cache.put(key, value);
        }

        /**
         * 将 key 对应的 value 更新为最近访问
         *
         * @param key
         */
        private void makeRecently(int key) {
            int val = cache.get(key);
            // 删除 cache 中的旧值
            cache.remove(key);
            // 将新值添加到队尾
            cache.put(key, val);
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
