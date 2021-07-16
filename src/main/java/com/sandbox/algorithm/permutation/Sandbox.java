package com.sandbox.algorithm.permutation;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static com.sandbox.algorithm.permutation.FullPermutation.arrage;
import static com.sandbox.algorithm.permutation.FullPermutationUnique.permuteUnique;

public class Sandbox {
    public static void main(String[] args) {
        int length = 3;
        int start = 0;
        int list[] = new int[length];
        for (int j = 0; j < length; j++)
            list[j] = j + 1;
        arrage(list, start, length);
        System.out.println("===============================================================================");
        int[] tmp = {9,2,1,1};
        List<List<Integer>> rv = permuteUnique(tmp);
        AtomicInteger count = new AtomicInteger();
        rv.forEach(listInteger -> {
            Integer[] a = listInteger.toArray(new Integer[listInteger.size()]);
            int hours = 10 * a[0] + a[1];
            int mins = 10 * a[2] + a[3];
            if (hours < 24 && mins < 60){
                count.getAndIncrement();
                System.out.println(a[0]+""+a[1]+":"+a[2]+""+a[3]);
            }
        });
        System.out.println(count.get());
    }
}
