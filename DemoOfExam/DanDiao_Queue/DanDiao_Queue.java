package DemoOfExam.DanDiao_Queue;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @ClassName DanDiao_Queue
 * @Description 解决的问题: 用来求出在数组的某个区间范围内求出最大值
 *
 *单调队列: 用来求出在数组的某个区间范围内求出最大值
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:36
 * @Version 1.0
 */
public class DanDiao_Queue {
    static PrintStream out = System.out;

    //单调双向队列(窗口内最大值), 某一时刻窗口内的最大值是对头 arr[queue.peekFirst()]
    public ArrayList<Integer> maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k < 1 || nums.length < k)
            return null;
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();//保存的是下标
        for (int i = 0; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[queue.peekLast()] <= nums[i]) //要队尾满足条件
                queue.pollLast();
            queue.addLast(i); // 注意添加的是下标
            if (i - k == queue.peekFirst())
                queue.pollFirst();//向左弹出过期的数据
            if (i >= k - 1) // 达到了k个数，这个窗口内的最大值是队列的头部
                res.add(nums[queue.peekFirst()]);
        }
        return res;
    }

    public static void main(String[] args) {

        int[] arr = {1, 2, 7, 7, 8};
        out.println(new DanDiao_Queue().maxSlidingWindow(arr, 3)); // 7, 7, 8
    }
}
