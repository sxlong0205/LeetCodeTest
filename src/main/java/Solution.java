import java.util.*;

/**
 * @author : Code Dragon
 * create at:  2020/7/20  8:56
 */
public class Solution {
    public int[] kWeakestRows(int[][] mat, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < mat.length; i++) {
            int temp = 0;
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] == 1) {
                    temp++;
                } else {
                    break;
                }
            }
            map.put(temp, i);
            list.add(temp);
        }

        int[] result = new int[k];
        Collections.sort(list, (o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            result[i] = map.get(list.get(i));
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ints = solution.kWeakestRows(new int[][]{
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 0},
                {1, 0, 0, 0, 0},
                {1, 1, 0, 0, 0},
                {1, 1, 1, 1, 1}
        }, 3);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}






