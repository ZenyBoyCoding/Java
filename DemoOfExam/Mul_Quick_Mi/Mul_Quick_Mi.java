package DemoOfExam.Mul_Quick_Mi;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @ClassName Mul_Quick_Mi
 * @Description 乘法快速幂
 *
 * 乘法和乘方快速幂
 *
 * @Auto 宗坚
 * @Date 2019/4/11 17:04
 * @Version 1.0
 */
public class Mul_Quick_Mi {
    //递归 计算 (a^n) % mod
    static long pow_mod(long a, long n, long mod) {
        if (n == 0)      // a^0 = 1
            return 1;
        // 先求一半的 --> 你先给我求出 a ^ (n/2) 的结果给我
        long halfRes = pow_mod(a, n >> 1, mod); // n >> 1 --> n/2

        long res = halfRes * halfRes % mod;

        if ((n & 1) != 0)       // odd num
            res = res * a % mod;
        return res;
    }

    //非递归 计算 (a^n) % mod
    static long pow_mod2(long a, long n, long mod) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) != 0) // 二进制最低位 是 1 --> (n&1) != 0  -->  乘上 x ^ (2^i)   (i从0开始)
                res = res * a % mod;
            a = a * a % mod;  // a = a^2
            n >>= 1;          // n -> n/2      往右边移一位
        }
        return res;
    }

    // 计算 (a * b) % mod
    static long mul_mod(long a, long b, long mod) {
        long res = 0;
        while (b > 0) {
            if ((b & 1) != 0)  // 二进制最低位是1 --> 加上 a的 2^i 倍, 快速幂是乘上a的2^i ）
                res = (res + a) % mod;
            a = (a << 1) % mod;    // a = a * 2    a随着b中二进制位数而扩大 每次 扩大两倍。
            b >>= 1;               // b -> b/2     右移  去掉最后一位 因为当前最后一位我们用完了，
        }
        return res;
    }

    //非递归 计算 (a^n) % mod   配合 mul
    static long pow_mod3(long a, long n, long mod) {
        long res = 1;
        while (n > 0) {
            if ((n & 1) != 0) // 二进制最低位 是 1 --> (n&1) != 0  -->  乘上 x ^ (2^i)   (i从0开始)
                res = mul_mod(res, a, mod) % mod;
            a = mul_mod(a, a, mod) % mod;  // a = a^2
            n >>= 1;          // n -> n/2      往右边移一位
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        int T = in.nextInt();
        while (T-- > 0) {
            int a = in.nextInt();
            int n = in.nextInt();
            int mod = in.nextInt();
//            System.out.println(pow_mod(a,n,mod));
//            System.out.println(pow_mod2(a,n,mod));
            System.out.println(pow_mod3(a, n, mod));
        }
    }
}
