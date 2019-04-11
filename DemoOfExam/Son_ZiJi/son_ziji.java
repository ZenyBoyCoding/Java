package DemoOfExam.Son_ZiJi;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName son_ziji
 * @Description TODO
 *
 *  n = 3；则要枚举0 - 7 对应的是有7个子集，每个子集去找有哪些元素print_subset中的 1<< i ，也就是对应的那个位置是有元素的，例如1的二进制是0001也就是代表0位置有元素，0010是2，代表第一个位置是1，0100代表第2个位置上有元素，相应的1000 = 8对应第3个位置上有元素。 总结来说也就是对应1<< i对应 i上是1(从0开始)，其余位置是0。
 *
 * 输出:
 *
 * 0
 * 0 1
 * 0 1 2
 * 0 2
 * 1
 * 1 2
 * 2
 * ---------------
 * 1 2 3
 * 1 2
 * 1 3
 * 1
 * 2 3
 * 2
 * 3
 *
 * ---------------
 *
 * 0
 * 1
 * 0 1
 * 2
 * 0 2
 * 1 2
 * 0 1 2
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:15
 * @Version 1.0
 */
public class son_ziji {
    static PrintStream out = System.out;

    //打印0~n-1的所有子集
    //按照递增顺序就行构造子集 防止子集的重复
    static void print_subset(int[] arr, int cur, int n){
        for(int i = 0; i < cur; i++)
            out.print(arr[i] + " ");
        out.println();
        int s = cur != 0 ? arr[cur-1] + 1 : 0;  //确定当前元素的最小可能值
        for(int i = s; i < n; i++){
            arr[cur] = i;
            print_subset(arr, cur+1, n);
        }
    }

    // 1~n 的所有子集：位向量法
    static void print_subset(int cur, boolean[] bits, int n) {
        if (cur == n+1) {
            for (int i = 1; i < cur; i++)
                if (bits[i])
                    out.print(i + " ");
            out.println();
            return;
        }
        bits[cur] = true;
        print_subset(cur + 1, bits, n);
        bits[cur] = false;
        print_subset(cur + 1, bits, n);
    }

    // 0 ~ n-1的所有子集：二进制法枚举0 ~ n-1的所有子集
    static void print_subset(int n){
        for(int mask = 0; mask < (1 << n); mask++){
            for(int i = 0; i < n; i++)
                if( ((mask >> i) & 1) == 1)  //和下面一样
//                if( ((1 << i) & mask) != 0)
                    out.print(i + " ");
            out.println();
        }
    }

    public static void main(String[] args){
        int n = 3;
        // 0~n-1的子集
        print_subset(new int[n], 0, n);
        out.println("---------------");

        // 1 ~ n 的子集
        print_subset(1, new boolean[n+1], n);
        out.println("---------------");

        // 1~n的子集
        print_subset(n);
    }


    /*------------------------------------------------*/

    /*

     */

}

/**
 * @Author :Zeny
 * @Description :
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *     说明：解集不能包含重复的子集。
 *     输入: nums = [1,2,3]
 *     输出:
 *     [
 *          [3],
 *          [1],
 *          [2],
 *          [1,2,3],
 *          [1,3],
 *          [2,3],
 *          [1,2],
 *          []
 *     ]
 *
 * @Since :16:22 2019/4/11
 */
class Solution {

    private boolean[] bit;
    private List<List<Integer>> res;

    public List<List<Integer>> subsets(int[] nums) {
        res = new ArrayList<>();
        bit = new boolean[nums.length];
        dfs(0, nums);
        return res;
    }

    //用一个bit数组记录
    private void dfs(int cur, int[] arr) {
        if (cur == arr.length) {
            List<Integer> tmp = new ArrayList<>();
            for (int i = 0; i < cur; i++) if (bit[i]) tmp.add(arr[i]);
            res.add(new ArrayList<>(tmp));
            return;
        }
        bit[cur] = true;
        dfs(cur + 1, arr);
        bit[cur] = false;
        dfs(cur + 1, arr);
    }

    public static void main(String[] args){
        System.out.println(new Solution().subsets(new int[]{1, 2, 3}));
    }
}
/**
 * @Author :Zeny
 * @Description :  子集   第二种使用二进制枚举:
 * @Since :16:22 2019/4/11
 */
class Solutiona {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for(int mask = 0; mask < (1 << nums.length); mask++){
            List<Integer> tmp = new ArrayList<>();
            for(int i = 0; i < nums.length; i++) if( ( (mask >> i) & 1) == 1) tmp.add(nums[i]);
            res.add(tmp);
        }
        return res;
    }
}

