package com.sandbox.algorithm.binarysearch;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

public class Sandbox {
    public static Integer[] array = {1,3,6,5,2,9};

    public static void main(String[] args) {
        List<Integer> list = Lists.newArrayList(array);
        Collections.sort(list);
        int lenght = list.size();
        Integer[] sortedArray = new Integer[lenght];
        list.toArray(sortedArray);

        System.out.println(binarySearch(sortedArray,6));
    }

    public static int binarySearch(Integer[] array, int target){
        int left = 0;
        int right = array.length - 1;

        while (left <= right){
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] > target) {
                right = mid - 1;
            } else if (array[mid] < target) {
                left = mid + 1;
            }
        }

        return -1;
    }

}
