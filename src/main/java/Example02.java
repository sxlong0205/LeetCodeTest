import java.util.Scanner;

/**
 * @author : Code Dragon
 * @create: 2021/8/18 20:35
 * @description :
 */
public class Example02 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int m = in.nextInt();
        int[] nums = new int[m];

        // 存储每种商品价格
        for (int i = 0; i < m; i++) {
            nums[i] = in.nextInt();
        }

        int result = solve(nums, x);
        System.out.println(result);
    }

    private static int solve(int[] prices, int X) {
        int[] dp = new int[X + 1];
        dp[0] = 1;
        for (int price : prices) {
            for (int j = X; j >= price; j--) {
                dp[j] += dp[j - price];
            }
        }
        return dp[X];
    }
}
