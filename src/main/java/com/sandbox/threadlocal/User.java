package com.sandbox.threadlocal;


import lombok.Data;

@Data
public class User {
    private String userName;

    public User(String userName){
        this.userName = userName;
    }
}
