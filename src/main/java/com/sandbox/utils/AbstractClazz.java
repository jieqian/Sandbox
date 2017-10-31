package com.sandbox.utils;

/**
 * Created by qianjie on 10/26/17.
 */
public abstract class AbstractClazz {

    private String str = "";

    public AbstractClazz(){
        str = display();
    }

    protected String display(){
        return "AbstractClazz";
    };

    public String getStr(){
        return str;
    }
}
