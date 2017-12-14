package com.sandbox.clazzloader;

/**
 * Created by qianjie on 12/14/17.
 */
public class Sample {
    private Sample instance;

    public void setSample(Object instance) {
        this.instance = (Sample) instance;
    }
}
