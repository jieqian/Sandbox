package com.sandbox.enumeration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by qianjie on 10/31/17.
 */
public enum ConsumeMode {
    CONCURRENTLY,
    ORDERLY;

    public static void main(String[] args) {
        String mode = "CONCURRENTLY";
        System.out.println(mode.equals(ConsumeMode.CONCURRENTLY.name()));
        List<String> list;
        list = Arrays.stream(ConsumeMode.values()).map(consumeMode -> consumeMode.name()).collect(Collectors.toList());
        System.out.println(list.size());
    }
}
