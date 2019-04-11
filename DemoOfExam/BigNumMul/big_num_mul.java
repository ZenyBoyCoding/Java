package DemoOfExam.BigNumMul;

/**
 * @ClassName big_num_mul
 * @Description 大数乘法
 * @Auto 宗坚
 * @Date 2019/4/11 16:10
 * @Version 1.0
 */
public class big_num_mul {
    // 大数乘法
    static String mul(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        int n1 = s1.length, n2 = s2.length;
        int[] a = new int[n1];
        int[] b = new int[n2];
        int[] c = new int[n1 + n2];
        for(int i = 0; i < n1; i++) a[i] = s1[n1 - i - 1] - '0';
        for(int i = 0; i < n2; i++) b[i] = s2[n2 - i - 1] - '0';
        for(int i = 0; i < n1; i++){
            for(int j = 0; j < n2; j++){
                c[i+j] += a[i] * b[j];
            }
        }
        for(int i = 0; i < n1 + n2 - 1; i++){
            if(c[i] >= 10){
                c[i+1] += c[i]/10;
                c[i] %= 10;
            }
        }
        int i;
        for(i = n1 + n2 - 1; i >= 0; i--) if(c[i] != 0) break;
        StringBuilder sb = new StringBuilder();
        for(; i >= 0; i--) sb.append( (char)(c[i] + '0'));
        return sb.toString();
    }
}
