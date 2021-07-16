package com.sandbox.threadlocal;

public class Sandbox {
    public static void main(String[] args) {
        ThreadLocalWithUserContext firstUser
                = new ThreadLocalWithUserContext(1);
        ThreadLocalWithUserContext secondUser
                = new ThreadLocalWithUserContext(2);
        Thread firstUserThread = new Thread(firstUser);
        firstUserThread.setName("firstUserThread");
        firstUserThread.start();
        Thread secondUserThread = new Thread(secondUser);
        secondUserThread.setName("secondUserThread");
        secondUserThread.start();

    }
}
