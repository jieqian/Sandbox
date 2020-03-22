package com.sandbox.algorithm.fab;

import java.util.HashMap;
import java.util.Map;

public class Sandbox {
    public static Map<Integer,Integer> cache = new HashMap<>();
    public static int total = 8;

    public static void main(String []args){
        fab_v1(total);
        System.out.println();
        fab_v2(total);
    }

    public static int fab_v1(int n){
        if (cache.get(n) != null) {
            return cache.get(n);
        }

        if (n == 1){
            cache.put(n,n);
            System.out.print(" " + 0 + " ");
            return n;
        } else if (n == 0) {
            cache.put(n,n);
            System.out.print(" " + 1 + " ");
            return n;
        }

        int rv = fab_v1(n -1 ) + fab_v1(n - 2);
        cache.put(n,rv);
        if ( n < total ) {
            System.out.print(" " + rv + " ");
        }
        return rv;
    }

    public static void fab_v2(int n){
        int p = 1;
        int pp = 0;
        System.out.print(" 0 ");
        System.out.print(" 1 ");
        for(int i=3; i<=n; i++) {
            int current = p + pp;
            System.out.print(" " + current + " ");
            pp = p;
            p = current;
        }
    }
}
