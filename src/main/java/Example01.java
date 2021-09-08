import java.util.Scanner;

/**
 * @author : Code Dragon
 * @create: 2021/8/18 20:35
 * @description :
 */
public class Example01 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int n = in.nextInt();
        // prices用于存放每种零食价格
        int[] prices = new int[n];
        // counts用于存放每种零食数量
        int[] counts = new int[n];
        // loves用于存放每种零食喜欢程度
        int[] loves = new int[n];

        // 记录每种零食信息
        for (int i = 0; i < n; i++) {
            prices[i] = in.nextInt();
            counts[i] = in.nextInt();
            loves[i] = in.nextInt();
        }

        int result = solve(prices, counts, loves, x);
        System.out.println(result);
    }


    private static int solve(int[] prices, int[] counts, int[] loves, int X) {
        // 用于存放喜好程度大小
        int[] dp = new int[X + 1];
        int n = prices.length;
        // 循环遍历每种零食
        for (int i = 0; i < n; i++) {
            int price = prices[i], count = counts[i], love = loves[i];
            for (int cnt = 0; cnt < count; cnt++) {
                for (int j = X; j >= price; j--) {
                    dp[j] = Math.max(dp[j], dp[j - price] + love);
                }
            }
        }
        return dp[X];
    }
}
