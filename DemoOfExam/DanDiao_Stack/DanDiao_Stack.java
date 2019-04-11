package DemoOfExam.DanDiao_Stack;

import java.io.PrintStream;
import java.util.Stack;

/**
 * @ClassName DanDiao_Stack
 * @Description 解决的是: 快速寻找一个数组中每一个元素　左右两边离它arr[i]最近的比它大/小的数。
 *
 * 单调栈: 寻找一个数组中每一个元素　左右两边离它arr[i]最近的比它大的数
 * 栈底到栈顶: 由大到小 (也可以自定义从小到大)
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:33
 * @Version 1.0
 */
public class DanDiao_Stack {
    static PrintStream out = System.out;

    public static void main(String[] args) {

        int[] arr = {3, 4, 5, 1, 2};
        int n = arr.length;

        /**--------------找左边的第一个比arr[i]大的-------------------*/
        int[] LL = new int[n]; //LL[i]存的是左边第一个比arr[i]大的数的下标
        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && arr[i] > arr[stack.peek()]){
                int top = stack.pop();
                if(stack.isEmpty()){
                    LL[top] = -1; //左边没有比arr[i]大的数
                }else {
                    LL[top] = stack.peek();
                }
            }
            stack.push(i); //注意是下标入栈
        }

        // 如果栈不空 //处理剩下的
        while(!stack.isEmpty()){
            int top = stack.pop();
            if(stack.isEmpty()) LL[top] = -1;
            else LL[top] = stack.peek();
        }

        for(int i = 0; i < n; i++) out.print(LL[i] + " "); // -1 -1 -1 2 2
        out.println();

        /**--------------找右边的第一个比arr[i]大的-------------------*/

        int[] RR = new int[n];//RR[i]存的是右边边第一个比arr[i]大的数的下标
        stack = new Stack<>();

        // 反过来即可
        for(int i = n-1; i >= 0; i--){
            while(!stack.isEmpty() && arr[i] > arr[stack.peek()]){
                int top = stack.pop();
                if(stack.isEmpty()){
                    RR[top] = -1; //左边没有比arr[i]大的数
                }else {
                    RR[top] = stack.peek();
                }
            }
            stack.push(i); //注意是下标入栈
        }

        // 如果栈不空, 处理剩下的
        while(!stack.isEmpty()){
            int top = stack.pop();
            if(stack.isEmpty()) RR[top] = -1;
            else RR[top] = stack.peek();
        }

        for(int i = 0; i < n; i++) out.print(RR[i] + " "); // 输出 1 2 -1 4 -1
        out.println();
    }
}
