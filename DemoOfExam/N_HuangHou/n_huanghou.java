package DemoOfExam.N_HuangHou;

import java.io.PrintStream;

/**
 * @ClassName n_huanghou
 * @Description N皇后问题也是经典的回溯问题
 * @Auto 宗坚
 * @Date 2019/4/11 16:23
 * @Version 1.0
 */
public class n_huanghou {
    static PrintStream out = System.out;

    static int count;

    // 第一种解法
    static void dfs(int r, int n, int[] cols) {  // 当前是r行
        if (r == n) {
            count++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (cols[i] == j)
                        out.print("0 ");
                    else
                        out.print(". ");
                }
                out.println();
            }
            out.println("-------------------");
            return;
        }
        for (int c = 0; c < n; c++) { // 考察的是每一列
            cols[r] = c;            // 尝试将 r行的皇后放在第c列
            boolean ok = true;
            for (int i = 0; i < r; i++) { //检查是否和已经放置的冲突
                //检查列,"副对角线","主对角线"
                if (cols[r] == cols[i] || r - i == cols[r] - cols[i] || r - i == cols[i] - cols[r]) {
                    ok = false;
                    break;
                }
            }
            if (ok) dfs(r + 1, n, cols);
        }
    }

    // 第二种解法: 使用cs三个数组记录，这里只是统计数目，没有保留解
    static void dfs(int r, boolean[] cs, boolean[] d1, boolean[] d2, int n) {  // 当前是r行
        if (r == n) {
            count++;
            return;
        }
        for (int c = 0; c < n; c++) { //考察的是每一列
            int id1 = r + c;   //主对角线
            int id2 = r - c + n - 1; // 副对角线
            if (cs[c] || d1[id1] || d2[id2]) continue;
            cs[c] = d1[id1] = d2[id2] = true;

            dfs(r + 1, cs, d1, d2, n);

            cs[c] = d1[id1] = d2[id2] = false;
        }
    }


    // 第二种解法的升级: 上一个版本的升级，使用cols数组 保留解
    static void dfs(int r, boolean[][] vis, int[] cols, int n) {  //逐行放置皇后
        if (r == n) {
            count++;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (cols[i] == j)
                        out.print("0 ");
                    else
                        out.print(". ");
                }
                out.println();
            }
            out.println("--------------");
            return;
        }
        for (int c = 0; c < n; c++) {   //尝试在 cur行的 各 列 放置皇后
            if (vis[0][c] || vis[1][r + c] || vis[2][r - c + n - 1]) continue;//判断当前尝试的皇后的列、主对角线
            vis[0][c] = vis[1][r + c] = vis[2][r - c + n - 1] = true;
            cols[r] = c;     //r行的列是 c
            dfs(r + 1, vis, cols, n);
            vis[0][c] = vis[1][r + c] = vis[2][r - c + n - 1] = false;//切记！一定要改回来
        }
    }

    public static void main(String[] args) {

        int n = 8; // 8皇后

        count = 0;
        dfs(0, n, new int[n]); // 8皇后
        out.println(count);
        out.println("=====================");

        count = 0;
        dfs(0, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], n);
        out.println(count);
        out.println("=====================");

        count = 0;
        dfs(0, new boolean[3][2*n-1], new int[n], n);
        out.println(count);
    }
}
