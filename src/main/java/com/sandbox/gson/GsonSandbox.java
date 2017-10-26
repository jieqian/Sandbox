package com.sandbox.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sandbox.utils.pojo.Pojo;
import com.sandbox.utils.tuple.Tuple2;

/**
 * Created by qianjie on 10/25/17.
 */
public class GsonSandbox {
    public static void main(String[] args) {
        Gson gson = Gsons.DEFAUTL.instance();
        Tuple2<String,String> tuple2 = new Tuple2<>("k","v");

        GsonSandbox gsonSandbox = new GsonSandbox();
        gsonSandbox.test(gson,tuple2);

        String jsonStr = gson.toJson(tuple2);
        System.out.println(jsonStr);

        Tuple2<String,String> deserializedTuple2 = gson.fromJson(jsonStr,new TypeToken<Tuple2<String,String>>(){}.getType());
        System.out.println(deserializedTuple2._1());
        System.out.println(deserializedTuple2._2());
    }

    public void test(Gson gson, Object object){
        System.out.println(gson.toJson(object));
    }
}
