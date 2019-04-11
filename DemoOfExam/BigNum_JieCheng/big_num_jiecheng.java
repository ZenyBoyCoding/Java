package DemoOfExam.BigNum_JieCheng;

/**
 * @ClassName big_num_jiecheng
 * @Description   大数阶乘
 *
 * 这个稍微特殊一点。我这里简单讲一下，举个例子结合代码就懂了。
 *
 * 比如算50的阶乘:
 *
 * 我们要先从1开始乘：1*2=2，将2存到a[0]中；
 * 接下来是用a[0]*3；2*3=6，将6储存在a[0]中；
 * 接下来是用a[0]*4；6*4=24，是两位数，那么24%10==4存到a[0]中，24/10==2存到a[1]中；
 * 接下来是用a[0]*5；a[1]*5+num(如果前一位相乘结果位数是两位数，那么num就等于十位上的那个数字；如果是一位数，num==0)；24*5=120，是三位数，那么120%10==0存到a[0]中，120/10%10==2存到a[1]中，120/100==1存到a[2]中；
 * 接下来是用a[0]*6、a[1]*6+num、a[2]*6+num、120*6=720，那么720%10==0存到a[0]中，720/10%10==2存到a[1]中，720/100==7存到a[2]中。。。
 *
 * @Auto 宗坚
 * @Date 2019/4/11 16:11
 * @Version 1.0
 */
public class big_num_jiecheng {
    //大数计算阶乘位数，可以自己在网上找一下博客
    //lg(N!)=[lg(N*(N-1)*(N-2)*......*3*2*1)]+1 = [lgN+lg(N-1)+lg(N-2)+......+lg3+lg2+lg1]+1;
    static int factorialDigit(int n) {
        double sum = 0;
        for (int i = 1; i <= n; i++)
            sum += Math.log10(i);
        return (int) sum + 1;
    }

    static String bigFactorial(int n) {
        int[] res = new int[100001];
        int digit = 1;
        res[0] = 1;
        for (int i = 2; i <= n; i++) {
            int carry = 0;
            for (int j = 0; j < digit; j++) {
                int temp = res[j] * i + carry; //每一位的运算结果
                res[j] = temp % 10;   //将最低位保留在原位置
                carry = temp / 10;   //计算进位, 等下这个进位会累加到j+1
            }
            while (carry != 0) {
                res[digit] = carry % 10;
                carry /= 10;
                digit++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = digit - 1; i >= 0; i--) sb.append( (char)(res[i] + '0'));
        return sb.toString();
    }

    public static void main(String[] args){
        System.out.println(bigFactorial(5));
    }
}
