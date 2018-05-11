package com.sandbox.threadlocal;

public class ThreadLocalWithUserContext implements Runnable{

    private UserContext userContext;
    private final Integer userId;
    private UserRepository userRepository = new UserRepository();

    ThreadLocalWithUserContext(Integer userId) {
        this.userId = userId;
        this.userContext = new UserContext();
    }

    private void process(){
        System.out.println("Current thread "+ Thread.currentThread().getName() + " is processing....");
    }

    private String getUserName(){
        return userContext.getUser().getUserName();
    }

    @Override
    public void run() {
        String userName = userRepository.getUserNameForUserId(userId);
        userContext.setUser(userName);
        process();
        System.out.println("thread-" + Thread.currentThread().getName() + " userContext for given userId: " + userId + " is: " + getUserName());
    }
}
