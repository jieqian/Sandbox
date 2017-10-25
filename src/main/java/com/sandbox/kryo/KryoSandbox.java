package com.sandbox.kryo;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.sandbox.utils.pojo.Pojo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by qianjie on 10/25/17.
 */
public class KryoSandbox {
    public static void main(String[] args) {
        Kryo kryo = new Kryo();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Output output = new Output(bos);
        Pojo pojo = new Pojo("one",1);
        kryo.writeObject(output,pojo);
        output.close();

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        Input input = new Input(bis);
        Pojo copy = (Pojo) kryo.readObject(input,Pojo.class);
        System.out.println(copy);

        input.close();
    }
}
