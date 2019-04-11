package DemoOfExam.TuoPu_sort;

import java.io.BufferedInputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * @ClassName TuoPu_Sort
 * @Description 拓扑排序
 *
 * BFS拓扑排序
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:43
 * @Version 1.0
 */
public class TuoPu_Sort {
    static ArrayList<Integer> G[];
    static int[] in;
    static int n, m;
    static PrintStream out;

    static void topologySort() {
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) if (in[i] == 0) queue.add(i);
        boolean flag = true; // for output
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (flag) {
                out.print(cur);
                flag = false;
            } else
                out.print(" " + cur);
            for (int i = 0; i < G[cur].size(); i++) {
                int to = G[cur].get(i);
                if (--in[to] == 0)
                    queue.add(to);
            }
        }
        out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        out = System.out;
        while (sc.hasNext()) {
            n = sc.nextInt();
            m = sc.nextInt();
            if (n == 0 && m == 0)
                break;
            in = new int[n + 1];
            G = new ArrayList[n + 1];
            for (int i = 0; i <= n; i++)
                G[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                G[from].add(to);
                in[to]++;
            }
            topologySort();
        }
    }
}
/**
 * @Author :Zeny
 * @Description : DFS拓扑排序
 * @Since :16:48 2019/4/11
 */
class Second{
    static ArrayList<Integer> G[];
    static int[] vis;
    static int n, m;
    static int[] res;
    static int p;

    static boolean dfs(int cur) {
        vis[cur] = 2; // now is visiting
        for (int to : G[cur]) {
            if (vis[to] == 2 || (vis[to] == 0 && !dfs(to))) // exist directed cycle
                return false;
        }
        vis[cur] = 1; // now is visited
        res[p--] = cur;
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        PrintWriter out = new PrintWriter(System.out);
        while (sc.hasNext()) {
            n = sc.nextInt();
            m = sc.nextInt();
            if (n == 0 && m == 0)
                break;
            G = new ArrayList[n + 1];
            vis = new int[n + 1];
            for (int i = 0; i <= n; i++)
                G[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int from = sc.nextInt();
                int to = sc.nextInt();
                G[from].add(to);
            }
            p = n - 1;  // back to front
            res = new int[n + 1];
            boolean ok = true;
            for (int i = 1; i <= n; i++) {
                if (vis[i] == 0)
                    dfs(i);
            }
            for (int i = 0; i < n - 1; i++)
                out.print(res[i] + " ");
            out.println(res[n - 1]);
            out.flush();
        }
        out.close();
    }
}
