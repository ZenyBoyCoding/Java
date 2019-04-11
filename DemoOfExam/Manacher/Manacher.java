package DemoOfExam.Manacher;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @ClassName Manacher
 * @Description Manacher算法
 *
 * 解决的是求最长回文子串的问题。
 *
 * 马拉车算法: 解决O(N)求最长回文串问题
 * 备注: 如果要求出最长回文，就记录一下取得最长回文的时候最长半径的位置即可
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:39
 * @Version 1.0
 */
public class Manacher {
    /**
     * 获取指定格式的字符串(中间和两边都带有#) 这样可以处理偶回文
     * 例如 : 如果是abc -->#a#b#c#
     * 如果是abcd -->#a#b#c#d#
     */
    static char[] manacherString(String str) {
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < res.length; i++)
            res[i] = ((i & 1) == 0) ? '#' : str.charAt(index++);
        return res;
    }

    static int manacher(String s) {
        if (s == null || s.length() == 0) return 0;
        char[] chs = manacherString(s);
        int[] r = new int[chs.length]; //记录每个位置的最长回文半径,注意是chs的长度
        int R = -1, C = -1;  //分别代表目前的最长回文右边界，和它的中心
        int max = Integer.MIN_VALUE;   //记录结果
        for (int i = 0; i < chs.length; i++) {
            r[i] = R > i ? Math.min(r[2 * C - i], R - i) : 1; //这句代码包含三种情况 第一种大情况,和二种中的(1)(2)情况
            while (i + r[i] < chs.length && i - r[i] >= 0) { //不越界 //注意这包括了四种情况，都要扩一下，为了节省代码
                if (chs[i + r[i]] == chs[i - r[i]]) { // 往前面扩一下
                    r[i]++;
                } else { //扩不动了
                    break;
                }
            }
            if (i + r[i] > R) { //更新最右边界和它的中心
                R = i + r[i];
                C = i;
            }
            max = Math.max(max, r[i]); //取最大的r[i] (r[i]记录的是每个位置的最长回文半径)
        }
        return max - 1; //求出来的是加了'#'的
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()) {
            String s = in.next();
            System.out.println(manacher(s));
        }
    }
}
