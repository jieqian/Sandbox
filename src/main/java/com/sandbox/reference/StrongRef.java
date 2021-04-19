package com.sandbox.reference;

import java.lang.ref.PhantomReference;

public class StrongRef {
    public static void main(String[] args) {
        if (true) {
            byte[] placeHolder = new byte[64 * 1024 * 1024];
            System.out.println(placeHolder.length / 1024 / 1024);
//            placeHolder = null;
//            placeHolder = new byte[]{};
        }
        int replacer = 1;
        System.gc();
    }
}
