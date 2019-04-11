package DemoOfExam.MaxLongSame;

/**
 * @ClassName max_long_same_string
 * @Description 最长公共子串问题
 * @Auto 宗坚
 * @Date 2019/4/11 16:08
 * @Version 1.0
 */
public class max_long_same_string {
    public int findLongest(String A, int n, String B, int m) {
        char[] s1 = A.toCharArray();
        char[] s2 = B.toCharArray();
        int[][] dp = new int[s1.length][s2.length];
        for (int i = 0; i < s1.length; i++) //注意和最长公共子序列有点不同
            dp[i][0] = s1[i] == s2[0] ? 1 : 0;
        for (int j = 0; j < s2.length; j++)
            dp[0][j] = s1[0] == s2[j] ? 1 : 0;
        int res = 0;
        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] == s2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;  //dp数组中的最大值，就是最大公共字串的长度
    }

    static int[][] getDp(char[] s1, char[] s2) {
        int[][] dp = new int[s1.length][s2.length];
        for (int i = 0; i < s1.length; i++) //注意和最长公共子序列有点不同
            dp[i][0] = s1[i] == s2[0] ? 1 : 0;
        for (int j = 0; j < s2.length; j++)
            dp[0][j] = s1[0] == s2[j] ? 1 : 0;
        int res = 0;
        for (int i = 1; i < s1.length; i++) {
            for (int j = 1; j < s2.length; j++) {
                if (s1[i] == s2[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        System.out.println(res);  //4
        return dp;  //dp数组中的最大值，就是最大公共字串的长度
    }

    /**
     * 根据dp表得到答案
     */
    static String getLongestSubstring(String s1, String s2, int[][] dp) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0)
            return "";
        int max = 0, end = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (dp[i][j] > max) {
                    max = dp[i][j];
                    end = i;
                }
            }
        }
        return s1.substring(end - max + 1, end + 1);
    }

    // 空间优化
    public int findLongest(String A, String B) {
        char[] s1 = A.toCharArray();
        char[] s2 = B.toCharArray();
        int row = 0, col = s2.length - 1; //从右上角开始
        int max = 0, end = 0;     //记录最大长度和结束位置
        while (row < s1.length) {
            int i = row, j = col;
            int ul = 0;
            while (i < s1.length && j < s2.length) {
                if (s1[i] == s2[j])
                    ul++;
                else
                    ul = 0;
                if (ul > max) {
                    max = ul;
                    end = i;
                }
                i++;
                j++;
            }
            if (col > 0) // 还没到最左边 -->　往左移动
                col--;
            else
                row++;  //到了最左  --> 往下移动
        }
        return max;
        //return sa.substring(end-max+1, end+1); // [end-max+1, end] 返回公共子串
    }
}
