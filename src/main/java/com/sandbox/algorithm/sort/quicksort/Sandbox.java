package com.sandbox.algorithm.sort.quicksort;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qianjie on 5/4/18.
 */
public class Sandbox {
    public static void main(String[] args) {
        MyQuickSort sorter = new MyQuickSort();
        int[] input = {24,2,45,20,56,75,2,56,99,53,12};
        sorter.sort(input);
        for(int i:input){
            System.out.print(i);
            System.out.print(" ");
        }

//        Set<String> set = new HashSet<>();
//        set.add

    }
}
