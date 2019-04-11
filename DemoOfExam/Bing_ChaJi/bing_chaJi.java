package DemoOfExam.Bing_ChaJi;

import java.io.BufferedInputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @ClassName bing_chaJi
 * @Description 并查集
 *
 *
 * 基于rank的并查集
 * 题目大意 : 病毒传染，可以通过一些社团接触给出一些社团(0号人物是被感染的)问有多少人(0~n-1个人)被感染
 *
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:25
 * @Version 1.0
 */
public class bing_chaJi {
    static int[] f;
    static int[] sz; // size

    static int findRoot(int p) {
        while (p != f[p]) {
            f[p] = f[f[p]];
            p = f[p];
        }
        return p;
    }

    // 将元素个数少的集合合并到元素个数多的集合上
    static void union(int a, int b) {
        int aR = findRoot(a);
        int bR = findRoot(b);
        if (aR == bR) return;
        if (sz[aR] < sz[bR]) {
            f[aR] = f[bR];
            sz[bR] += sz[aR]; // 更新集合元素个数
        }else{
            f[bR] = f[aR];
            sz[aR] += sz[bR];
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        PrintStream out = System.out;
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if(n == 0 && m == 0) break;
            f = new int[n];
            sz = new int[n];
            for(int i = 0; i < n; i++) {
                f[i] = i;
                sz[i] = 1;
            }
            for(int i = 0; i < m; i++){
                int c = in.nextInt();
                int root = in.nextInt();
                for(int j = 0; j < c - 1; j++) {
                    int num = in.nextInt();
                    union(root, num);
                }
            }
            int res = 1; // 0已经感染
            for(int i = 1; i < n; i++) if(findRoot(0) == findRoot(i)) res++;
            out.println(res);
        }
    }
}
/**
 * @Author :Zeny
 * @Description :
 *
 * 基于size的并查集
 * 题目大意 : 病毒传染，可以通过一些社团接触给出一些社团(0号人物是被感染的)问有多少人(0~n-1个人)被感染
 *
 * @Since :16:28 2019/4/11
 */
class Second{
    static int[] f;
    static int[] sz; // size

    static int findRoot(int p) {
        while (p != f[p]) {
            f[p] = f[f[p]];
            p = f[p];
        }
        return p;
    }

    // 将元素个数少的集合合并到元素个数多的集合上
    static void union(int a, int b) {
        int aR = findRoot(a);
        int bR = findRoot(b);
        if (aR == bR) return;
        if (sz[aR] < sz[bR]) {
            f[aR] = f[bR];
            sz[bR] += sz[aR]; // 更新集合元素个数
        }else{
            f[bR] = f[aR];
            sz[aR] += sz[bR];
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        PrintStream out = System.out;
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            if(n == 0 && m == 0) break;
            f = new int[n];
            sz = new int[n];
            for(int i = 0; i < n; i++) {
                f[i] = i;
                sz[i] = 1;
            }
            for(int i = 0; i < m; i++){
                int c = in.nextInt();
                int root = in.nextInt();
                for(int j = 0; j < c - 1; j++) {
                    int num = in.nextInt();
                    union(root, num);
                }
            }
            int res = 1; // 0已经感染
            for(int i = 1; i < n; i++) if(findRoot(0) == findRoot(i)) res++;
            out.println(res);
        }
    }
}
