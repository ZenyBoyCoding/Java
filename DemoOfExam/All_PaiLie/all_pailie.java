package DemoOfExam.All_PaiLie;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @ClassName all_pailie
 * @Description 总共有四种，前两种是刘汝佳的书中的，后面是经典的和dfs的
 *
 * 枚举生成全排列。
 * 非去重全排列。
 * 去重全排列。
 *
 *
 * 输出:
 *
 * 1 2 3
 * 1 3 2
 * 2 1 3
 * 2 3 1
 * 3 1 2
 * 3 2 1
 * --------------
 * 1 1 1
 * --------------
 * 1 1
 * 1 1
 * ---------------
 * 1 3 2
 * 1 2 3
 * 3 1 2
 * 3 2 1
 * 2 1 3
 * 2 3 1
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:12
 * @Version 1.0
 */
public class all_pailie {
    static PrintStream out = System.out;

    // 1 ~ n 的排列
    static void permutation(int[] tmp, int cur, int n) {
        if (cur == n) {  // 边界
            for (int i = 0; i < n; i++)
                out.print(tmp[i] + " ");
            out.println();
        } else for (int i = 1; i <= n; i++) {  //尝试在arr[cur]中填充各种整数 (1~n)
            boolean flag = true;
            for (int j = 0; j < cur; j++)
                if (i == tmp[j]) {  // 如果i已经在arr[0]~arr[cur-1]中出现过，则不能选
                    flag = false;
                    break;
                }
            if (flag) {
                tmp[cur] = i;  //把i填充到当前位置
                permutation(tmp, cur + 1, n);
            }
        }
    }

    // 数组的去重全排列
    // tmp存放排列，arr是原数组
    static void permutation(int[] tmp, int[] arr, int cur, int n) {
        if (cur == n) {
            for (int i = 0; i < n; i++)
                out.print(tmp[i] + " ");
            out.println();
        } else for (int i = 0; i < n; i++) if (i == 0 || arr[i] != arr[i - 1]) {
            int c1 = 0, c2 = 0;
            for (int j = 0; j < n; j++)
                if (arr[j] == arr[i]) // 重复元素的个数
                    c1++;
            for (int j = 0; j < cur; j++)
                if (tmp[j] == arr[i]) // 前面已经排列的重复元素的个数
                    c2++;
            if (c2 < c1) {
                tmp[cur] = arr[i];
                permutation(tmp, arr, cur + 1, n);
            }
        }
    }

    //非去重　经典全排列
    static void permutation_2(int[] arr, int cur, int n){
        if(cur == n){
            for(int i = 0; i < n; i++) out.print(arr[i] + " ");
            out.println();
            return;
        }
        for(int i = cur; i < n; i++){
            swap(arr, i, cur);
            permutation_2(arr, cur + 1, n);
            swap(arr, i, cur);
        }
    }

    static void swap(int[] arr, int a, int b){
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }

    // 用一个used数组来求得全排列
    static void dfs(int[] arr, ArrayList<Integer> list, boolean[] used){
        if(list.size() == arr.length){
            for(int num :  list) out.print(num + " ");
            out.println();
            return;
        }
        for(int i = 0; i < arr.length; i++){
            //  if (used[i] || (i > 0 && !used[i - 1] && arr[i] == arr[i - 1])) continue; // 去重的写法，去重要先排序 Arrays.sort(arr);
            if(used[i]) continue;
            used[i] = true;
            list.add(arr[i]);

            dfs(arr, list, used);

            list.remove(list.size() - 1);
            used[i] = false;
        }
    }

    public static void main(String[] args) {
        int n = 5;
        permutation(new int[n], 0, n);

        out.println("--------------");

        int[] arr = {1, 1, 1};
        // 需要先排序 , 上面的排列只会输出 1,1,1因为我们去重了
        Arrays.sort(arr);
        permutation(new int[arr.length], arr, 0, arr.length);

        out.println("--------------");
        permutation_2(new int[]{1, 1}, 0, 2); // 输出两个{1, 1}

        out.println("---------------");

        dfs(new int[]{1, 3, 2}, new ArrayList<>(), new boolean[3]);
    }
}
