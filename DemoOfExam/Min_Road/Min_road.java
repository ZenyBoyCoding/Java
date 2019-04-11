package DemoOfExam.Min_Road;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @ClassName Min_road
 * @Description 这里只给出最经典的Dijstra
 *
 * 最短路
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:53
 * @Version 1.0
 */
public class Min_road {
    static int n;
    static int m;
    static boolean[] vis;
    static ArrayList<Edge> G[];

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

    static int[] dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] dis = new int[n];
        for (int i = 0; i < n; i++) dis[i] = Integer.MAX_VALUE; //初始标记（不是-1(因为是求最小的)）
        dis[start] = 0;
//        G.vis[start] = true;  //第一个访问 start, 不能将start标记为true
        pq.add(new Edge(start, 0)); //将第一条边加入 pq, 自环边
        while (!pq.isEmpty()) {
            Edge curEdge = pq.poll();
            int to = curEdge.to;
            if (vis[to])
                continue;
            vis[to] = true;
            for (int i = 0; i < G[to].size(); i++) { //更新相邻的边
                int nxtNode = G[to].get(i).to;
                int nxtW = G[to].get(i).w;
                if (!vis[nxtNode] && dis[nxtNode] > dis[to] + nxtW) {
                    dis[nxtNode] = dis[to] + nxtW;
                    pq.add(new Edge(nxtNode, dis[nxtNode])); //将这个新的dis[nxtNode]加入优先队列，没准它是下一个(很小)
                }
            }
        }
        return dis;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        while (in.hasNext()) {
            n = in.nextInt();
            m = in.nextInt();
            G = new ArrayList[n]; // 0~n-1
            vis = new boolean[n];
            for (int i = 0; i < n; i++) {
                G[i] = new ArrayList<>();
                vis[i] = false;
            }
            for (int i = 0; i < m; i++) {
                int from = in.nextInt();
                int to = in.nextInt();
                int w = in.nextInt();
                G[from].add(new Edge(to, w));
                G[to].add(new Edge(from, w));
            }
            int s = in.nextInt();
            int e = in.nextInt();
            int[] dis = dijkstra(s);
            System.out.println(dis[e] == Integer.MAX_VALUE ? -1 : dis[e]);
        }
    }
}
