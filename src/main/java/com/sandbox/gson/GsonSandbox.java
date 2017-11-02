package com.sandbox.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sandbox.utils.pojo.Pojo;
import com.sandbox.utils.tuple.Tuple2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qianjie on 10/25/17.
 */
public class GsonSandbox {
    public static void main(String[] args) {
        Gson gson = Gsons.DEFAUTL.instance();
        Tuple2<String,String> t1 = new Tuple2<>("k1","v1");
        Tuple2<String,String> t2 = new Tuple2<>("k2","v2");
//
//        GsonSandbox gsonSandbox = new GsonSandbox();
//        gsonSandbox.test(gson,tuple2);
//
//        String jsonStr = gson.toJson(tuple2);
//        System.out.println(jsonStr);
//
//        Tuple2<String,String> deserializedTuple2 = gson.fromJson(jsonStr,new TypeToken<Tuple2<String,String>>(){}.getType());
//        System.out.println(deserializedTuple2._1());
//        System.out.println(deserializedTuple2._2());

        List<Tuple2<String,String>> list = new ArrayList<>();
        list.add(t1);
        list.add(t2);

        System.out.println(gson.toJson(list,new TypeToken<List<Tuple2<String,String>>>(){}.getType()));
    }

    public void test(Gson gson, Object object){
        System.out.println(gson.toJson(object));
    }
}
