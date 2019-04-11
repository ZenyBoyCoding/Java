package DemoOfExam.Tree_Array;

/**
 * @ClassName tree_array
 * @Description 树状数组
 * @Auto 宗坚
 * @Date 2019/4/11 16:29
 * @Version 1.0
 */
public class tree_array {
    class NumArray {

        private int[] sums;// 树状数组中求和的数组
        private int[] data;//真实存放数据的数组
        private int n;

        private int lowbit(int x) {return x & (-x);}

        private int query(int i){
            int s = 0;
            while(i > 0){//树状数组中索引是1~n
                s += sums[i];
                i -= lowbit(i);
            }
            return s;
        }

        // fenWick update
        private void renewal(int i, int delta){// delta是增量，不是新值
            while(i <= n){//树状数组中索引是1~n
                sums[i] += delta;
                i += lowbit(i);
            }
        }

        public NumArray(int[] nums) {
            n = nums.length;
            sums = new int[n+1];
            data = new int[n];
            for(int i = 0; i < n; i++) {
                data[i] = nums[i];
                renewal(i+1, nums[i]);
            }
        }

        public void update(int i, int val) {
            renewal(i+1, val - data[i]);
            data[i] = val;
        }

        public int sumRange(int i, int j) {
            return query(j+1) - query(i);
        }
    }

    public static void main(String[] args){

    }
}
