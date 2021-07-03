import java.util.HashMap;
import java.util.LinkedHashMap;

public class LRU {
    private HashMap<Integer, Node> map;
    private LinkedHashMap<Integer, Integer> cache;
    private int capacity;

    public LRU(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }
        // 将 key 变为最近使用
        makeRecently(key);
        return cache.get(key);
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            cache.put(key, value);
            makeRecently(key);
            return;
        }

        if (cache.size() >= capacity) {
            int oldKey = cache.keySet().iterator().next();
            cache.remove(oldKey);
        }
        cache.put(key, value);
    }

    private void makeRecently(int key) {
        int x = cache.get(key);
        cache.remove(key);
        cache.put(key, x);
    }
}
