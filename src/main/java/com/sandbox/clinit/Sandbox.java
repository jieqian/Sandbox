package com.sandbox.clinit;

public class Sandbox {
    public static void main(String[] args) {
        try{
            BadClass.doSomething();
//            BadClass bc = new BadClass();
//            bc.doOtherThings();
        }catch (Throwable e){
            e.printStackTrace();
        }

        BadClass.doSomething();
    }
}
