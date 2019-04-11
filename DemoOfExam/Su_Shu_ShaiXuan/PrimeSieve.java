package DemoOfExam.Su_Shu_ShaiXuan;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @ClassName PrimeSieve
 * @Description 主要是埃式筛法
 * @Auto 宗坚
 * @Date 2019/4/11 16:59
 * @Version 1.0
 */
public class PrimeSieve {
    // 经典筛法，超时
    static ArrayList<Integer> primary(boolean[] is_prime, int MAX) {
        ArrayList<Integer> prime = new ArrayList<>();
        is_prime[0] = is_prime[1] = false; // 01 不是素数
        boolean flag;
        for (int i = 2; i <= MAX; i++) { //范围是1000 我筛选 0~2000内的素数
            flag = true;
            for (int j = 2; j * j <= i; j++) {// 根号i的时间复杂度
                if (i % j == 0) {
                    is_prime[i] = false;
                    flag = false;
                    break;
                }
            }
            if (flag) {
                prime.add(i);
                is_prime[i] = true;
            }
        }
        return prime;
    }


    //经典的埃式筛法
    static ArrayList<Integer> sieve(boolean[] is_prime, int MAX) {

        ArrayList<Integer> prime = new ArrayList<>();
        Arrays.fill(is_prime, true);

        is_prime[0] = is_prime[0] = false;

        for (int i = 2; i <= MAX; i++) {
            if (is_prime[i]) {
                prime.add(i);
                for (int j = 2 * i; j <= MAX; j += i)
                    is_prime[j] = false;
            }
        }

        return prime;
    }

    //优化筛法
    static ArrayList<Integer> sieve2(boolean[] is_prime, int MAX) {
        ArrayList<Integer> prime = new ArrayList<>();
        Arrays.fill(is_prime, true);
        is_prime[0] = is_prime[0] = false;
        for (int i = 2; i <= MAX; i++) {
            if (is_prime[i])
                prime.add(i);
            for (int j = 0; j < prime.size() && prime.get(j) <= MAX / i; j++) {
                is_prime[prime.get(j) * i] = false; //筛掉  (小于等于i的素数 * i)  构成的合数
                if (i % prime.get(j) == 0) //如果 i是 < i的素数的倍数 就不用筛了
                    break;
            }
        }

        return prime;
    }


    static boolean isPalindrome(int num) {
        int oldNum = num;
        int newNum = 0;
        //反过来计算
        while (num > 0) {
            newNum = newNum * 10 + num % 10;
            num /= 10;
        }
        return newNum == oldNum;
    }

    static final int maxn = 9989899; //题目中最大的回文素数

    public static void main(String[] args) {

        Scanner in = new Scanner(new BufferedInputStream(System.in));
        boolean[] is_prime = new boolean[maxn + 1];

        // 三种筛法对比
//        primary(is_prime,maxn); //超时
//        sieve(is_prime,maxn); // ok
        sieve2(is_prime, maxn); // ok  fast

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i <= maxn; i++) {
            if (is_prime[i] && isPalindrome(i))
                res.add(i);
        }
        while (in.hasNext()) {
            int a = in.nextInt();
            int b = in.nextInt();
            int num = 0;
            for (int i = 0; i < res.size(); i++) {
                num = res.get(i);
                if(num >= a && num <= b) System.out.println(num);
            }
            System.out.println();
        }
    }
}
