package DemoOfExam.Min_ShengCheng_Tree;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @ClassName Min_ShengCheng_Tree
 * @Description 最小生成树 Kruskal
 * @Auto 宗坚
 * @Date 2019/4/11 16:50
 * @Version 1.0
 */
public class Min_ShengCheng_Tree {
    static int n;
    static int m;
    static ArrayList<Edge> edges;

    static class Edge implements Comparable<Edge> {
        public int from;
        public int to;
        public int w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }

    static class UF {

        int[] parent;
        int[] rank;

        public UF(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public boolean isSameSet(int x, int y) {
            return find(x) == find(y);
        }

        public int find(int v) {
            while (parent[v] != v) {
                parent[v] = parent[parent[v]];  // 路径压缩优化
                v = parent[v];
            }
            return v;
        }

        public void union(int a, int b) {
            int aR = find(a);
            int bR = find(b);
            if (aR == bR)
                return;
            if (rank[aR] < rank[bR]) { // a更矮,所以挂到b更好
                parent[aR] = bR;
            } else if (rank[aR] > rank[bR]) {
                parent[bR] = aR;
            } else {
                parent[aR] = bR;
                rank[bR]++;
            }
        }
    }

    static int kruskal() {
        Collections.sort(edges);  // 对边集排序
        UF uf = new UF(n);
        int res = 0;
        int count = 0;
        for (int i = 0; i < edges.size(); i++) {
            int from = edges.get(i).from;
            int to = edges.get(i).to;
            int w = edges.get(i).w;
            if (!uf.isSameSet(from, to)) { //两个顶点不属于同一个集合
                res += w;
                count++;
                if (count == n - 1)
                    break;
                uf.union(from, to);
            }

        }
        return count == n - 1 ? res : -1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()) {
            m = in.nextInt(); // 先输入道路条数
            n = in.nextInt();
            if (m == 0)
                break;
            edges = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int from = in.nextInt();
                int to = in.nextInt();
                int w = in.nextInt();
                edges.add(new Edge(from, to, w));
                edges.add(new Edge(to, from, w));
            }
            int res = kruskal();
            System.out.println(res == -1 ? "?" : res);
        }
    }
}

/**
 * @Author :Zeny
 * @Description : 最小生成树 Prim
 * @Since :16:52 2019/4/11
 */
class Second{
    static int n, m;
    static boolean[] vis;
    static ArrayList<Edge>[] G;

    static class Edge implements Comparable<Edge> {
        public int to;
        public int w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return w - o.w;
        }
    }

    private static int prim(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < G[start].size(); i++)
            pq.add(G[start].get(i));
        int count = 0;
        int res = 0;
        vis[start] = true; // 起始节点已经在集合中
        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            int to = curEdge.to;
            if (!vis[to]) {
                vis[to] = true;
                count++;
                res += curEdge.w;
                if (count == n - 1)
                    break;
                for (int i = 0; i < G[to].size(); i++) {
                    int nxtNode = G[to].get(i).to;
                    if (!vis[nxtNode]) // to -> nxtNode 没有加入过
                        pq.add(G[to].get(i)); // 将to-> nxtNode的边加入优先队列
                }
            }
        }
        if (count != n - 1)
            return -1;
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()) {
            m = in.nextInt(); // 先输入道路条数
            n = in.nextInt();
            if (m == 0)
                break;
            G = new ArrayList[n + 1];  // 1~n
            vis = new boolean[n + 1];
            for (int i = 0; i <= n; i++)
                G[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int from = in.nextInt();
                int to = in.nextInt();
                int w = in.nextInt();
                G[from].add(new Edge(to, w));
                G[to].add(new Edge(from, w));
            }
            int res = prim(1);
            System.out.println(res == -1 ? "?" : res);
        }
    }
}
