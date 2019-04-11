package DemoOfExam.KMP;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @ClassName kmp
 * @Description KMP: 模式串匹配问题
 * @Auto 宗坚
 * @Date 2019/4/11 16:38
 * @Version 1.0
 */
public class kmp {
    static int kmp(int[] s, int[] p) {
        if (s == null || p == null || s.length < p.length || p.length == 0)
            return -1;
        int[] next = getNext(p);
        int i1 = 0, i2 = 0;
        while (i1 < s.length && i2 < p.length) {
            if (s[i1] == p[i2]) {
                i1++;
                i2++;
            } else {
                if (next[i2] == -1) {
                    i1++;
                } else {
                    i2 = next[i2];
                }
            }
        }
        return i2 == p.length ? i1 - i2 : -1; // 返回i2在i1匹配到的第一个位置
    }

    /**
     * next数组含义:
     * next[i]的含义是在str[i]之前的字符串也就是: str[0...i)中，
     * 必须以str[i-1]结尾的后缀子串(不能包含str[0]) 和
     * 必须以str[0]开头的前缀子串(不能包含str[i-1])的最大匹配长度
     */
    static int[] getNext(int[] arr) {
        if (arr.length == 1) return new int[]{-1};
        int[] next = new int[arr.length + 1];
        next[0] = -1;
        next[1] = 0;
        int cn = 0;
        for (int i = 2; i <= arr.length; ) {
            if (arr[i - 1] == arr[cn]) {
                next[i++] = ++cn;
            } else {
                if (cn > 0) {
                    cn = next[cn];
                } else {
                    next[i++] = 0;
                }
            }
        }
        return next;
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(getNext(new int[]{1, 2, 3, 1, 2})));
        Scanner cin = new Scanner(new BufferedInputStream(System.in));
        int k = cin.nextInt();
        while (k-- > 0) {
            int n = cin.nextInt();
            int m = cin.nextInt();
            int[] s = new int[n];
            int[] p = new int[m]; //切记不能随便new int[n+1]因为后面用length代替了n
            for (int i = 0; i < n; i++) s[i] = cin.nextInt();
            for (int i = 0; i < m; i++) p[i] = cin.nextInt();
            int res = kmp(s, p);
            System.out.println(res == -1 ? -1 : res + 1);
        }
    }
}
