package DemoOfExam.zero_one_package;

import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName zero_one
 * @Description 01背包
 * @Auto 宗坚
 * @Date 2019/4/11 16:02
 * @Version 1.0
 */
public class zero_one {
    static int n, C;
    static int[] w, v;
    static int[][] dp;

    //记忆化
    static int rec(int p, int curW) {
        if (p == n)
            return 0;
        if (dp[p][curW] != -1) return dp[p][curW];
        if (curW + w[p] > C)
            return dp[p][curW] = rec(p + 1, curW);
        else
            return dp[p][curW] = Math.max(rec(p + 1, curW + w[p]) + v[p],
                    rec(p + 1, curW));
    }

    // 普通
    static int dp(){
        int[][] dp = new int[n+1][C+1];
        for(int i = n - 1; i >= 0; i--){
            for(int j = 0; j <= C; j++){
                dp[i][j] = j + w[i] > C ? dp[i+1][j] :
                        Math.max(dp[i+1][j], dp[i+1][j+w[i]]+v[i]);
            }
        }
        return dp[0][0];
    }

    // 二维滚动
    static int dp2(){
        int[][] dp = new int[2][C+1];
        for(int i = n - 1; i >= 0; i--){
            for(int j = 0; j <= C; j++){
                dp[i&1][j] = j + w[i] > C ? dp[(i+1)&1][j] :
                        Math.max(dp[(i+1)&1][j], dp[(i+1)&1][j+w[i]]+v[i]);
            }
        }
        return dp[0][0];
    }

    // 一维dp
    static int dp3(){
        int[] dp = new int[C + 1];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= C; j++) { // 注意顺序一定要这样
                dp[j] = j + w[i] > C ? dp[j] : Math.max(dp[j], dp[j + w[i]] + v[i]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            n = in.nextInt();
            C = in.nextInt();
            w = new int[n];
            v = new int[n];
            for (int i = 0; i < n; i++) v[i] = in.nextInt();
            for (int i = 0; i < n; i++) w[i] = in.nextInt();
            dp = new int[n][C + 1];
            for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
//            out.println(rec(0, 0));
//            out.println(dp());
//            out.println(dp2());
            out.println(dp3());
            out.flush();
        }
        out.close();
    }
}
