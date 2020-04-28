package com.sandbox.algorithm.sqrt;

import java.text.NumberFormat;

public class Sandbox {
    public static void main(String[] args) {
        System.out.println(mySqrt(2));
        System.out.println(sqrt(2, 0.01d));
        System.out.println(mySqrt(5,4));
    }

    public static int mySqrt(int x) {
        if (x <= 0)
            return 0;
        if (x == 1)
            return 1;
        double result = 0;
        double left = 0, right = x;
        while (left <= right) {
            double mid = (left + right) / 2;
            double s = mid * mid;
            if (Math.abs(s - x) <= 0.01) {
                result = mid;
                break;
            }
            if (s > x)
                right = mid;
            else if (s < x)
                left = mid;
        }
        return (int) result;
    }

    public static float sqrt(int x, double epsilon) {
        if (x < 0) return -1;
        if (x == 0 || x == 1) return x;

        float left = 0, right = x;
        while (left <= right) {
            float mid = left + (right - left) / 2;
            if (Math.abs(mid - x / mid) < epsilon) return mid;
            if (mid < x / mid) left = mid;
            if (mid > x / mid) right = mid;
        }

        return Float.NEGATIVE_INFINITY;
    }

    public static double mySqrt(int x, int accuracy) {
        if (x <= 0)
            return 0;
        if (x == 1)
            return 1;
        double result = 0;
        double left = 0, right = x;
        while (left <= right) {
            double mid = (left + right) / 2;
            double s = mid * mid;
            if (Math.abs(s - x) <= Math.pow(0.1, accuracy)) {
                result = mid;
                break;
            }
            if (s > x)
                right = mid;
            else if (s < x)
                left = mid;
        }
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(accuracy);
        return Double.parseDouble(nf.format(result)) ;
    }
}
