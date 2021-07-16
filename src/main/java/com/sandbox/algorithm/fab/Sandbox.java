package com.sandbox.algorithm.fab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sandbox {
    public static Map<Integer,Integer> cache = new HashMap<>();
    public static int total = 40;
    public static List<Integer> fabList_v1 = new ArrayList<>();

    public static void main(String []args){
        int v0 = fab_v0(total);
        int v1 = fab_v1(total);
        System.out.println();
        int v2 = fab_v2(total);
        System.out.println();
        System.out.println("v0 => " + fab_v0(total-1));
//        System.out.println("v1 => " + fabList_v1.get(total-1));
//        System.out.println("v2 => " + v2);
    }

    public static int fab_v0(int n){
        if (n==1 || n==0) return n;
        return fab_v0(n-1) + fab_v0(n-2);
    }

    public static int fab_v1(int n){
        if (cache.get(n) != null) {
            return cache.get(n);
        }

        if (n == 1){
            cache.put(n,n);
            System.out.print(" " + 0 + " ");
            fabList_v1.add(0);
            return n;
        } else if (n == 0) {
            cache.put(n,n);
            System.out.print(" " + 1 + " ");
            fabList_v1.add(1);
            return n;
        }

        int rv = fab_v1(n -1 ) + fab_v1(n - 2);
        cache.put(n,rv);
        if ( n < total ) {
            fabList_v1.add(rv);
            System.out.print(" " + rv + " ");
        }
        return rv;
    }

    public static int fab_v2(int n){
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

        return p;
    }
}
