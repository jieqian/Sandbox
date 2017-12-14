package com.sandbox.enumeration;

/**
 * Created by qianjie on 10/31/17.
 */
public enum ConsumeMode {
    CONCURRENTLY,
    ORDERLY;

    public static void main(String[] args) {
        String mode = "CONCURRENTLY";
        System.out.println(mode.equals(ConsumeMode.CONCURRENTLY.name()));
    }
}
