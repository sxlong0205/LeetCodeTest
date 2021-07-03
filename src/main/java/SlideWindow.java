public class SlideWindow {
    public String minWindow(String s, String t) {
        int[] need = new int[128];
        int[] window = new int[128];

        char[] chars = t.toCharArray();
        // 将所有需要的字符存储起来
        for (char c : chars) {
            need[c]++;
        }

        int totalCount = 0;
        for (int i = 0; i < need.length; i++) {
            if (need[i] != 0) {
                totalCount++;
            }
        }

        int left = 0, right = 0;
        int valid = 0;
        int start = 0, len = Integer.MAX_VALUE;

        // right 指针没到边界时继续向右滑动
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            // 如果当前字符是被需要的
            if (need[c] > 0) {
                // 增加窗口中当前字符的计数
                window[c]++;
                // 如果窗口中当前字符的数量满足需要的数量，让 valid 加一
                if (window[c] == need[c]) {
                    valid++;
                }
            }


            // 缩小窗口条件
            while (valid == totalCount) {
                // 如果当前窗口长度比 len 还小，更新 len
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // 窗口左移
                char d = s.charAt(left);
                left++;
                // 如果当前字符是被需要的，更新窗口中的字符
                if (need[d] > 0) {
                    if (window[d] == need[d]) {
                        valid--;
                    }
                    window[d]--;
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
