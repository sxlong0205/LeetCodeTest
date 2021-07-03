import java.util.HashMap;
import java.util.LinkedHashSet;

public class LFU {
    // key 到 val 的映射
    HashMap<Integer, Integer> keyToVal;
    // key 到 freq 的映射
    HashMap<Integer, Integer> keyToFreq;
    // freq 到 key 列表的映射
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    // 记录最小频次
    int minFreq;
    // 记录 LFU 缓存的最大容量
    int capacity;

    public LFU(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.minFreq = 0;
        this.capacity = capacity;
    }

    public void put(int key, int val) {
        if (capacity <= 0) {
            return;
        }

        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, val);
            increaseFreq(key);
            return;
        }

        // 容量已满需要淘汰一个 freq 最小的 key
        if (capacity <= keyToVal.size()) {
            removeMinFreqKey();
        }

        keyToVal.put(key, val);
        keyToFreq.put(key, 1);
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        this.minFreq = 1;
    }

    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        freqToKeys.get(freq).remove(key);
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }

    private void removeMinFreqKey() {
        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
        int deleteKey = keyList.iterator().next();
        keyList.remove(deleteKey);
        if (keyList.isEmpty()) {
            freqToKeys.remove(this.minFreq);
        }
        keyToVal.remove(deleteKey);
        keyToFreq.remove(deleteKey);
    }
}
