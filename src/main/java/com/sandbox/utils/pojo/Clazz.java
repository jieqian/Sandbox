package com.sandbox.utils.pojo;

import com.sandbox.utils.AbstractClazz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qianjie on 10/26/17.
 */
public class Clazz extends AbstractClazz {
    @Override
    public String display() {
        return "Clazz....";
    }


    public void batch(String ... strings){
        Arrays.stream(strings).forEach(str->{
            System.out.println(str);
        });
    }


    public static void main(String[] args) {
       Clazz clazz = new Clazz();
       List<String> list = new ArrayList<>();
       list.add("one");
       list.add("two");
       list.add("three");
       String[] array = new String[list.size()];
       clazz.batch(list.toArray(array));
    }
}
