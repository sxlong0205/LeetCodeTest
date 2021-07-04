import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BackTrack {

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> temp = new LinkedList<>();
        dfs(nums, temp);
        return result;
    }

    private void dfs(int[] nums, LinkedList<Integer> temp) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (temp.contains(nums[i])) {
                continue;
            }

            temp.add(nums[i]);
            dfs(nums, temp);
            temp.removeLast();
        }
    }

}
