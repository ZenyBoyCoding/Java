package DemoOfExam.Ou_La_Hui_Lu;

import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @ClassName ou_la_hui_lu
 * @Description
 *
 * 欧拉回路：
 *
 * 1)、图G是连通的，不能有孤立点存在。
 * 对于无向图来说度数为奇数的点个数为0，对于有向图来说每个点的入度必须等于出度。
 * 欧拉路径：
 *
 * 1)、图G是连通的，无孤立点存在。
 *
 * 、分情况:
 * 对于无向图来说:度数为奇数的的点可以有2个或者0个，并且这两个奇点其中一个为起点另外一个为终点。
 *
 * 对于有向图来说:可以存在两个点，其入度不等于出度，其中一个入度比出度大1，为路径的起点；
 *
 * 另外一个出度比入度大1，为路径的终点。
 *
 * 判断连通可以用DFS或者并查集。
 *
 * 欧拉回路和路径 (1) 用dfs判连通
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:55
 * @Version 1.0
 */
public class ou_la_hui_lu {

    static ArrayList<Integer> G[];
    static int n, m;
    static boolean[] vis;

    static void dfs(int cur){
        if(vis[cur]) return;
        vis[cur] = true;
        for(int to : G[cur]){
            if(!vis[to])
                dfs(to);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        PrintWriter out = new PrintWriter(System.out);
        while(sc.hasNext()){
            n = sc.nextInt();
            if(n == 0) break;
            m = sc.nextInt();
            int[] in = new int[n+1];
            vis = new boolean[n+1];
            G = new ArrayList[n+1];
            for(int i = 0; i <= n; i++) G[i] = new ArrayList<>();
            for(int i = 0; i < m; i++){
                int from = sc.nextInt();
                int to = sc.nextInt();
                G[from].add(to);
                G[to].add(from);
                in[from]++;
                in[to]++;
            }
            dfs(1);
            boolean ok = true;
            for(int i = 1; i <= n; i++) if(in[i] % 2 != 0 || !vis[i]){
                ok = false;
                break;
            }
            out.println(ok ? "1" : "0");
            out.flush();
        }
        out.close();
    }
}
/**
 * @Author :Zeny
 * @Description :
 *
 * 使用并查集判断连通
 *
 * @Since :16:57 2019/4/11
 */
class Second{
    static int n, m;
    static int[] parent, rank;

    static int findRoot(int p){
        while(p != parent[p]){
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    static void union(int a, int b){
        int aR = findRoot(a);
        int bR = findRoot(b);
        if(aR == bR) return;
        if(rank[aR] < rank[bR]){
            parent[aR] = bR;
        }else if(rank[aR] > rank[bR]){
            parent[bR] = aR;
        }else {
            parent[aR] = bR;
            rank[bR]++;
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        PrintWriter out = new PrintWriter(System.out);
        while(sc.hasNext()){
            n = sc.nextInt();
            if(n == 0) break;
            m = sc.nextInt();
            int[] in = new int[n+1];
            parent = new int[n+1];
            rank = new int[n+1];
            for(int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
            for(int i = 0; i < m; i++){
                int from = sc.nextInt();
                int to = sc.nextInt();
                union(from, to);
                in[from]++;
                in[to]++;
            }
            int oneRoot = findRoot(1);
            boolean ok = in[1] % 2 == 0;
            for(int i = 2; i <= n; i++) if(in[i] % 2 != 0 || findRoot(i) != oneRoot){
                ok = false;
                break;
            }
            out.println(ok ? "1" : "0");
            out.flush();
        }
        out.close();
    }
}
