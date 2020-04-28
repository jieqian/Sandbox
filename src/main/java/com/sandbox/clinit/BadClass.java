package com.sandbox.clinit;

public class BadClass {
    private static int a =100;
    static {
        System.out.println("before class init");
        int b = 3/0;
        System.out.println("after class init");
    }

    public static void doSomething(){
        System.out.println("do something");
    }

    public void doOtherThings(){
        System.out.println("do other things");
    }
}
