package com.sandbox.threadlocal;

import java.util.UUID;

public class UserContext {

    private static ThreadLocal<User> threadLocalContext = ThreadLocal.withInitial(()->new User(UUID.randomUUID().toString()));

    public UserContext() {
    }

    public void setUser(String userName){
        threadLocalContext.set(new User(userName));
    }

    public User getUser(){
        return threadLocalContext.get();
    }

    @Override
    public String toString() {
        return "UserContext{" +
                "userNameSecret='" + threadLocalContext.get().getUserName() + '\'' +
                '}';
    }
}
