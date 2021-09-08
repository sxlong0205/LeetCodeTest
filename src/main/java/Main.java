import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int k = 0; k < t; k++) {
            int n = in.nextInt();
            int m = in.nextInt();

            int totalSum = (1 + (m * n)) * (m * n) / 2;
            int rowIndex = -1, columIndex = -1;
            int rowDivd = 0, columDivd = 0;

            int halfSum = 0;
            if (totalSum % 2 == 0) {
                halfSum = totalSum / 2;
            } else {
                halfSum = totalSum / 2 + 1;
            }

            // 横向，利用前缀和思想
            int[] rowPreSum = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                rowPreSum[i] = rowPreSum[i - 1] + ((1 + m) * m / 2 + (i - 1) * m * m);
            }
            for (int i = 1; i <= n; i++) {
                if (rowPreSum[i] > halfSum) {
                    rowIndex = i;
                    rowDivd = totalSum - rowPreSum[i - 1] * 2;
                    break;
                }
            }

            // 纵向，利用前缀和思想
            int[] columPreSum = new int[m + 1];
            for (int i = 1; i <= m; i++) {
                columPreSum[i] = columPreSum[i - 1] + ((1 + 1 + m * (n - 1)) * n / 2 + n * (i - 1));
            }
            for (int i = 1; i <= m; i++) {
                if (columPreSum[i] > halfSum) {
                    columIndex = i;
                    columDivd = totalSum - columPreSum[i - 1] * 2;
                    break;
                }
            }

            if (columDivd >= rowDivd) {
                System.out.println("H " + rowIndex);
            } else {
                System.out.println("V " + columIndex);
            }
        }
    }
}