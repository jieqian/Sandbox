package com.sandbox.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

/**
 * Created by qianjie on 3/22/17.
 */
public enum Gsons{
    DEFAUTL("default");
    private Gson gson;
    Gsons(String type){
        if (type.equals("default")){
            gson = new GsonBuilder().create();
        }
    }
    public Gson instance(){
        return this.gson;
    }
}
