package DemoOfExam.GCD_LCM;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @ClassName GCD_LCM
 * @Description 求最大公约数和最小公倍数
 * @Auto 宗坚
 * @Date 2019/4/11 16:58
 * @Version 1.0
 */
public class GCD_LCM {
    // 非递归gcd
    static int gcdIter(int a, int b){
        int r;
        while(b != 0){
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }

    static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static int lcm(int a, int b) {
        return a / gcd(a, b) * b;
    }

    static int ngcd(int arr[], int n) {
        if (n == 1) return arr[0];
        return gcd(arr[n - 1], ngcd(arr, n - 1));
    }

    static int nlcm(int arr[], int n) {
        if (n == 1) return arr[0];
        return lcm(arr[n - 1], nlcm(arr, n - 1));
    }

    public static void main(String[] args){

        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        for(int t = 0; t < T; t++){
            int n = in.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++) a[i] = in.nextInt();
            System.out.println(nlcm(a, n));
        }
    }
}
