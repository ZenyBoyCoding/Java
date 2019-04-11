package DemoOfExam.MaxLongIncrement;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @ClassName max_long_increment
 * @Description 最长公共子序列
 * @Auto 宗坚
 * @Date 2019/4/11 16:04
 * @Version 1.0
 */
public class max_long_increment {
    // O(N^2)
    public int lengthOfLIS(int[] nums){
        if(nums == null || nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int res = 1;
        for(int i = 0; i < nums.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // O(N^2)
    static int[] getDp(int[] nums){
        if(nums == null || nums.length == 0) return new int[]{};
        int[] dp = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            dp[i] = 1;
            for(int j = 0; j < i; j++){
                if(nums[j] < nums[i])
                    dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        return dp;
    }

    static int[] getLIS(int[] arr, int[] dp){
        int maxLen = 0, end = 0;
        for(int i = 0; i < dp.length; i++) if(dp[i] > maxLen){
            maxLen = dp[i];
            end = i;
        }
        int[] lis = new int[maxLen];
        lis[--maxLen] = arr[end];
        for(int i = end - 1; i >= 0; i--){
            if(dp[i] == dp[end] - 1 && arr[i] < arr[end]){
                lis[--maxLen] = arr[i];
                end = i;
            }
        }
        return lis;
    }

    // O(N * logN)
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int[] dp = new int[nums.length];
        int[] ends = new int[nums.length + 1];
        dp[0] = 1;
        ends[1] = nums[0];
        int right = 1;  // [1~right]为有效区　ends数组是有序的(升序), right是右边界
        int L, mid, R;
        for (int i = 1; i < nums.length; i++) {
            L = 1;
            R = right;
            // 找到第一个>=arr[i]的，返回结果是 L
            while (L <= R) {
                mid = L + (R - L) / 2;
                if (ends[mid] >= nums[i])
                    R = mid - 1;
                else
                    L = mid + 1;
            }
            // 说明以arr[i]以arr[i]结尾的最长递增子序列=ends区有效长度+1
            if (L > right) { //没有找到arr[i]是最长的 (因为下标从1开始，所以判断是>right),
                dp[i] = right + 1;
                ends[right + 1] = nums[i]; // 扩大ends数组
                right += 1;  //扩大有效区
            } else {  // 找到了arr[l] > arr[i], 更新end[l] = arr[i] ,表示l长度的最长子序列结尾可以更新为arr[i]
                dp[i] = right; // dp[i]还是没有加长
                ends[L] = nums[i];
            }
        }
        return right;
    }


    public static void main(String[] args){
        PrintWriter out = new PrintWriter(System.out);
        int[] arr = {10,9,2,5,3,7,101,18};
        out.println(Arrays.toString(getLIS(arr, getDp(arr))));

        out.close();
    }
}
