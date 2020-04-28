package com.sandbox.algorithm.pow;

public class Sandbox {
    public static void main(String[] args) {
        System.out.println(myPow(2,10));
    }

    public static double myPow(double x, int n) {
        long N = n;
        if (N < 0){
            x = 1 / x;
            N = -N;
        }
       return powIteration(x, N);
    }

    public static double powIteration(double x, long N) {
        double ans = 1;
        //遍历每一位
        while (N > 0) {
            //最后一位是 1，加到累乘结果里
            if ((N & 1) == 1) {  //相当于 if (N % 2) == 1 {
                ans = ans * x;
            }
            //更新 x
            x = x * x;
            //n 右移一位
            N = N >> 1;
        }
        return ans;
    }
}
