package DemoOfExam.WeiYi_FenJie;

import java.io.BufferedInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @ClassName WeiYi_FenJie
 * @Description
 *
 * 解决的问题: 任何一个大于1的自然数 N，如果N不为质数，那么N可以唯一分解成有限个质数的乘积。
 *
 * @Auto 宗坚
 * @Date 2019/4/11 17:01
 * @Version 1.0
 */
public class WeiYi_FenJie {
    static ArrayList<Integer> sieve(int MAX) {
        ArrayList<Integer> prime = new ArrayList<>();
        boolean[] is_prime = new boolean[MAX + 1];

        Arrays.fill(is_prime, true);
        is_prime[0] = is_prime[1] = false;

        for (int i = 2; i <= MAX; i++) {
            if (is_prime[i]) {
                prime.add(i);

                for (int j = 2 * i; j <= MAX; j += i) {
                    is_prime[j] = false;
                }
            }
        }
        return prime;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));
        PrintWriter out = new PrintWriter(System.out);
        int T = in.nextInt();
        for (int t = 0; t < T; t++) {
            int n = in.nextInt();
            ArrayList<Integer> prime = sieve(n);
            ArrayList<Integer> res = new ArrayList<>();
            //筛选
            for (int i = 0; i < prime.size(); i++) {
                int p = prime.get(i);
                while (n % p == 0) {
                    res.add(p);
                    n /= p;
                }
            }
            out.print(res.get(0));
            for(int i = 1; i < res.size(); i++) out.print("*" + res.get(i));
            out.println();
            out.flush();
        }
        out.close();
    }
}

/**
 * @Author :Zeny
 * @Description : 约数枚举和整数分解的代码
 * @Since :17:03 2019/4/11
 */
class Second{
    //约数枚举
    static ArrayList<Integer> divisor(int n) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0)
                res.add(i);
            if (i != n / i)
                res.add(n / i);
        }
        return res;
    }

    //整数分解
    static HashMap<Integer, Integer> prime_factor(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                if (map.containsKey(i)) {
                    map.put(i, map.get(i) + 1);
                } else {
                    map.put(i, 1);
                }
                n /= i;
            }
        }
        if (n != 1) map.put(n, 1);
        return map;
    }

    public static void main(String[] args) {
        System.out.println(divisor(12));

        System.out.println("----测试分解素因子(唯一分解定理)-----");

        HashMap<Integer, Integer> map = prime_factor(12);
        for (Integer num : map.keySet())
            System.out.println(num + " " + map.get(num));
    }
}
