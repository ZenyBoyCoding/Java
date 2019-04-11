package DemoOfExam.MaxLongSame;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @ClassName max_long_same
 * @Description 最长公共子序列
 * @Auto 宗坚
 * @Date 2019/4/11 16:06
 * @Version 1.0
 */
public class max_long_same {
    /**
     * dp[i][j]代表的是 str[0..i]与str[0...j]的最长公共子序列
     */
    static int[][] getDp(char[] s1, char[] s2) {
        int n1 = s1.length, n2 = s2.length;
        int[][] dp = new int[n1][n2];
        dp[0][0] = s1[0] == s2[0] ? 1 : 0;
        for (int i = 1; i < n1; i++)  // 一旦dp[i][0]被设置成1,则dp[i~N-1][0]都为1
            dp[i][0] = Math.max(dp[i - 1][0], s1[i] == s2[0] ? 1 : 0);
        for (int j = 1; j < n2; j++)
            dp[0][j] = Math.max(dp[0][j - 1], s2[j] == s1[0] ? 1 : 0);

        for (int i = 1; i < n1; i++) {
            for (int j = 1; j < n2; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (s1[i] == s2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                }
            }
        }
        return dp;
    }

    static String getLCS(char[] s1, char[] s2, int[][] dp) {
        if(s1 == null || s1.length == 0 || s2 == null || s2.length == 0)
            return "";
        int i = s1.length - 1;
        int j = s2.length - 1;
        char[] res = new char[dp[i][j]]; //生成答案的数组
        int index = dp[i][j] - 1;
        while (index >= 0) {
            if (i > 0 && dp[i][j] == dp[i - 1][j]) {
                i--;
            } else if (j > 0 && dp[i][j] == dp[i][j - 1]) {
                j--;
            } else { // dp[i][j] = dp[i-1][j-1]+1
                res[index--] = s1[i];
                i--;
                j--;
            }
        }
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        char[] s1 = in.next().toCharArray();
        char[] s2 = in.next().toCharArray();

        int[][] dp = getDp(s1, s2);
//        System.out.println(dp[s1.length-1][s2.length-1]); //length of lcs
        System.out.println(getLCS(s1, s2, dp));
    }
}
