import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFU {
    /**
     * 容量
     */
    int capacity;

    /**
     * 当前最小出现频次
     */
    int minFreq;

    /**
     * key 对 value 的集合
     */
    HashMap<Integer, Integer> keyToValue;

    /**
     * key 对出现频率的集合
     */
    HashMap<Integer, Integer> keyToFreq;

    /**
     * 相同频率对应的 key 集合
     */
    HashMap<Integer, LinkedHashSet<Integer>> freqToKey;

    /**
     * 初始化
     *
     * @param capacity
     */
    public LFU(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        keyToValue = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKey = new HashMap<>();
    }

    /**
     * 获取 key 对应的 value
     *
     * @param key
     * @return
     */
    public int get(int key) {
        // 若 keyToValue 不包含 key，直接返回 -1
        if (!keyToValue.containsKey(key)) {
            return -1;
        }
        // 增加 key 对应的 freq
        increaseFreq(key);
        // 返回结果
        return keyToValue.get(key);
    }

    /**
     * 增加 key 对应的 freq
     *
     * @param key
     */
    private void increaseFreq(int key) {
        // 获取当前 key 对应的 freq
        int freq = keyToFreq.get(key);
        // 更新 key、freq 集合
        keyToFreq.put(key, freq + 1);
        // 从 freqToKey 中删除当前 freq 对应的 key
        freqToKey.get(freq).remove(key);
        // 更新 key 对应出现的 freq
        freqToKey.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKey.get(freq + 1).add(key);
        // 如果删除掉 freq 后，freqToKey 对应的 value 为空，那么直接删除 freqToKey 中该频率对应的数据
        if (freqToKey.get(freq).isEmpty()) {
            freqToKey.remove(freq);
            // 如果删除的 freq 恰好等于 minFreq，这里只需要让 minFreq 加一即可
            // 因为上面在更新集合的时候，key 出现的 freq 加了一，故这里 minFreq 加一即为最小出现频次
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }

    /**
     * 新增 key、value
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (this.capacity == 0) {
            return;
        }

        if (keyToValue.containsKey(key)) {
            // 修改 key 对应的 value
            keyToValue.put(key, value);
            // 增加 key 对应的 freq
            increaseFreq(key);
            return;
        }

        // 如果 keyToValue 容量已满
        if (keyToValue.size() >= this.capacity) {
            // 删除出现频次最少的 key，如果有多个 key 的 freq 相同，则删除最早添加的那个 key
            removeMinFreqKey();
        }

        // 添加 key、value 键值对
        keyToValue.put(key, value);
        // 对应的 freq 为 1
        keyToFreq.put(key, 1);
        // 将当前 key 加入 freqToKey 中，即频率为 1 的 key 有哪些
        freqToKey.putIfAbsent(1, new LinkedHashSet<>());
        freqToKey.get(1).add(key);
        // 将 minFreq 设置为 1
        this.minFreq = 1;
    }

    /**
     * 删除当前出现频率最小的 Key
     */
    private void removeMinFreqKey() {
        // 获取 minFreq 对应的 key 集合
        LinkedHashSet<Integer> set = freqToKey.get(this.minFreq);
        // 删除最早加入的 key
        int deleteKey = set.iterator().next();
        set.remove(deleteKey);
        // 如果 minFreq 对应的 key 集合为空，可以直接从 freqToKey 中删除对应的键值对
        if (set.isEmpty()) {
            freqToKey.remove(this.minFreq);
        }
        // 从 keyToValue 中删除对应的 key
        keyToValue.remove(deleteKey);
        // 从 keyToFreq 中删除对应的 key
        keyToFreq.remove(deleteKey);
    }
}
