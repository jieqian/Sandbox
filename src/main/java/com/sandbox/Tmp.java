package com.sandbox;

import java.util.ArrayList;
import java.util.List;

public class Tmp {

    public Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        int a = 1;
        int b = 1;
        int c = 1;
        int d = 1;

        if(!((a>2)&&(b>2)&&(c>2)&&(d>2))){
            int[] array = {a,b,c,d};
            System.out.println(solution(array));
        }


    }

    public static int solution(int[] a) {

        int rv = 0;
        // Choose different indices i, j, k, l as a permutation of 0, 1, 2, 3
        for (int i = 0; i < 4; ++i){
            for (int j = 0; j < 4; ++j){
                if (j != i){
                    for (int k = 0; k < 4; ++k){
                        if (k != i && k != j) {
                            int l = 6 - i - j - k;

                            // For each permutation of A[i], read out the time and
                            // record the largest legal time.
                            int hours = 10 * a[i] + a[j];
                            int mins = 10 * a[k] + a[l];
                            if (hours < 24 && mins < 60)
                                rv ++;
                        }
                    }
                }
            }

        }

        return rv;

    }
}
