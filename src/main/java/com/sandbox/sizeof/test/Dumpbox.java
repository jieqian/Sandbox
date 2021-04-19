package com.sandbox.sizeof.test;

public class Dumpbox implements Runnable{

    public User qian = new User("qian".toCharArray(),38);

    public static void main(String[] args) {
        Dumpbox dumpbox = new Dumpbox();
        new Thread(dumpbox).start();
    }

    @Override
    public void run() {
        while (true){
            try {
                System.out.println("10 sec");
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
