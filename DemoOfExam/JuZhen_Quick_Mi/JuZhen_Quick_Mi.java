package DemoOfExam.JuZhen_Quick_Mi;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * @ClassName JuZhen_Quick_Mi
 * @Description 矩阵快速幂
 *
 * 经典的斐波那契数列问题 f[n] = f[n-1] + f[n-2]
 *
 * @Auto 宗坚
 * @Date 2019/4/11 17:06
 * @Version 1.0
 */
public class JuZhen_Quick_Mi {
    static class Matrix {
        public int row;
        public int col;
        public int[][] m;

        public Matrix(int row, int col) {
            this.row = row;
            this.col = col;
            m = new int[row][col];
        }
    }

    static final int MOD = 10000;

    static Matrix mul(Matrix a, Matrix b) {
        Matrix c = new Matrix(a.row, b.col); //注意这里

        for (int i = 0; i < a.row; i++) {
            for (int j = 0; j < b.col; j++) {
                for (int k = 0; k < a.col; k++)
                    c.m[i][j] = (c.m[i][j] + a.m[i][k] * b.m[k][j]) % MOD;
            }
        }
        return c;
    }

    static Matrix pow(Matrix a, int k) {
        Matrix res = new Matrix(a.row, a.col); // 方阵
        for (int i = 0; i < a.row; i++)
            res.m[i][i] = 1;
        while (k > 0) {
            if ((k & 1) != 0)
                res = mul(res, a);
            a = mul(a, a);
            k >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedInputStream(System.in));

        while (in.hasNext()) {
            int n = in.nextInt();
            if (n == -1) break;
            if (n == 0) {
                System.out.println(0);
                continue;
            }

            Matrix matrix = new Matrix(2, 2);
            matrix.m[0][0] = matrix.m[0][1] = matrix.m[1][0] = 1;
            matrix.m[1][1] = 0;

            Matrix res = pow(matrix, n - 1);

            System.out.println(res.m[0][0] % MOD);
        }
    }
}
